package com.example.digitalfocus.service

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.graphics.PixelFormat
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

    // Debouncing logic to prevent counting a single user scroll multiple times
    private val scrollHandler = Handler(Looper.getMainLooper())
    private var isDebouncing = false

    override fun onServiceConnected() {
        super.onServiceConnected()
        if (dataRepository == null) {
            dataRepository = DataRepository(this)
        }
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // Failsafe: Ensure the service always starts in a non-blocked state.
        dataRepository?.setBlocked(false)

        Log.d(TAG, "onServiceConnected - The service is running.")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null || event.scrollDeltaY == 0) {
            return
        }

        // If we are currently in the debounce period, ignore this event.
        if (isDebouncing) {
            return
        }

        val repo = dataRepository ?: return

        if (repo.isBlocked()) {
            return
        }

        // Start debouncing to group rapid-fire scroll events into one.
        isDebouncing = true
        scrollHandler.postDelayed({ isDebouncing = false }, 300) // 300ms debounce window

        val currentCount = repo.getScrollCount()
        val newCount = currentCount + 1
        repo.setScrollCount(newCount)
        scrollCountForTest++

        Log.d(TAG, "Scroll detected on YouTube. New count: $newCount")

        if (newCount >= 5) {
            repo.setScrollCount(0)
            scrollCountForTest = 0
            showOverlay()
        }
    }

    private fun showOverlay() {
        // Create and manage a custom lifecycle for the overlay
        val owner = WindowLifecycleOwner()
        lifecycleOwner = owner

        dataRepository?.setBlocked(true)
        overlayView = ComposeView(this).apply {
            // Attach the custom lifecycle owner
            setViewTreeLifecycleOwner(owner)
            setViewTreeViewModelStoreOwner(owner)
            setViewTreeSavedStateRegistryOwner(owner)
            setContent {
                BlockingView()
            }
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

        Handler(Looper.getMainLooper()).postDelayed({
            hideOverlay()
        }, 5000)
    }

    private fun hideOverlay() {
        overlayView?.let {
            windowManager.removeView(it)
            overlayView = null
            dataRepository?.setBlocked(false)

            // Destroy the custom lifecycle owner
            lifecycleOwner?.destroy()
            lifecycleOwner = null
        }
    }

    override fun onInterrupt() {
        Log.d(TAG, "onInterrupt")
    }
}