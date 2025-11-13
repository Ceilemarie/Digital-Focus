package com.example.digitalfocus.service

import android.accessibilityservice.AccessibilityService
import android.content.Context 
import android.graphics.PixelFormat
import android.view.accessibility.AccessibilityNodeInfo
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.example.digitalfocus.data.DataRepository
import com.example.digitalfocus.view.BlockingView

class FocusAccessibilityService : AccessibilityService() {

    private val TAG = "FocusAccessibilityService"
    internal var dataRepository: DataRepository? = null
    internal var scrollCountForTest = 0

    private lateinit var windowManager: WindowManager
    private var overlayView: ComposeView? = null
    private var lifecycleOwner: WindowLifecycleOwner? = null

    private val scrollHandler = Handler(Looper.getMainLooper())
    private var isDebouncing = false

    private val blockedAppPackages = setOf(
        "com.google.android.youtube",
        "com.instagram.android",
        "com.facebook.katana",
        "com.zhiliaoapp.musically", // TikTok
        "com.ss.android.ugc.trill"   // TikTok
    )

    private val blockedBrowserUrls = setOf(
        "youtube.com",
        "tiktok.com",
        "facebook.com",
        "instagram.com"
    )

    override fun onServiceConnected() {
        super.onServiceConnected()
        if (dataRepository == null) {
            dataRepository = DataRepository(this)
        }
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        dataRepository?.setBlocked(false)
        Log.d(TAG, "onServiceConnected - The service is running.")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null || event.scrollDeltaY == 0 || isDebouncing) {
            return
        }

        val repo = dataRepository ?: return
        if (repo.isBlocked()) {
            return
        }

        val packageName = event.packageName?.toString() ?: ""
        val isEventFromBlockedApp = when {
            packageName in blockedAppPackages -> true
            packageName == "com.android.chrome" -> isBlockedUrlInBrowser(rootInActiveWindow)
            else -> false
        }

        if (isEventFromBlockedApp) {
            handleBlockedAppScroll(repo)
        }
    }

    private fun isBlockedUrlInBrowser(rootNode: AccessibilityNodeInfo?): Boolean {
        if (rootNode == null) return false

        // This is the most fragile part of the implementation.
        // The View ID "url_bar" is specific to Chrome and could be different in past or future versions.
        val urlBarNodes = rootNode.findAccessibilityNodeInfosByViewId("com.android.chrome:id/url_bar")
        if (urlBarNodes.isNotEmpty() && urlBarNodes[0].text != null) {
            val url = urlBarNodes[0].text.toString()
            Log.d(TAG, "Chrome URL found via ID: $url")
            return blockedBrowserUrls.any { url.contains(it, ignoreCase = true) }
        } else {
            // If the ID lookup fails, log a warning. This helps debug issues on older devices.
            Log.w(TAG, "Could not find Chrome URL bar by ID. Browser blocking may not work on this device/Chrome version.")
        }
        return false
    }

    private fun handleBlockedAppScroll(repo: DataRepository) {
        isDebouncing = true
        scrollHandler.postDelayed({ isDebouncing = false }, 300)

        val currentCount = repo.getScrollCount()
        val newCount = currentCount + 1
        repo.setScrollCount(newCount)
        scrollCountForTest++

        Log.d(TAG, "Scroll detected on a blocked app. New count: $newCount")

        if (newCount >= 5) {
            repo.setScrollCount(0)
            scrollCountForTest = 0
            showOverlay()
        }
    }

    private fun showOverlay() {
        val owner = WindowLifecycleOwner()
        lifecycleOwner = owner
        dataRepository?.setBlocked(true)

        overlayView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(owner)
            setViewTreeViewModelStoreOwner(owner)
            setViewTreeSavedStateRegistryOwner(owner)
            setContent { BlockingView() }
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.CENTER

        windowManager.addView(overlayView, params)
        owner.resume()

        Handler(Looper.getMainLooper()).postDelayed({ hideOverlay() }, 5000)
    }

    private fun hideOverlay() {
        overlayView?.let {
            windowManager.removeView(it)
            overlayView = null
            dataRepository?.setBlocked(false)
            lifecycleOwner?.destroy()
            lifecycleOwner = null
        }
    }

    override fun onInterrupt() {
        Log.d(TAG, "onInterrupt")
    }
}