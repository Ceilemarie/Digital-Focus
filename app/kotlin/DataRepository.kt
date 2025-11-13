package com.example.digitalfocus.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("digital_focus_prefs", Context.MODE_PRIVATE)

    private val _scrollCount = MutableStateFlow(prefs.getInt(SCROLL_COUNT_KEY, 0))
    val scrollCount = _scrollCount.asStateFlow()

    private val _isBlocked = MutableStateFlow(prefs.getBoolean(IS_BLOCKED_KEY, false))
    val isBlocked = _isBlocked.asStateFlow()

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                SCROLL_COUNT_KEY -> _scrollCount.value = getScrollCount()
                IS_BLOCKED_KEY -> _isBlocked.value = getIsBlocked()
            }
        }

    init {
        // When the app starts, listen for any changes to the SharedPreferences file.
        // This makes sure the data is consistent even if other parts of the app
        // modify the same preferences.
        prefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    fun getScrollCount(): Int {
        return prefs.getInt(SCROLL_COUNT_KEY, 0)
    }

    fun setScrollCount(count: Int) {
        prefs.edit().putInt(SCROLL_COUNT_KEY, count).apply()
    }

    fun incrementScrollCount(): Int {
        val newCount = getScrollCount() + 1
        setScrollCount(newCount)
        return newCount
    }

    fun resetScrollCount() {
        setScrollCount(0)
    }

    fun getIsBlocked(): Boolean {
        return prefs.getBoolean(IS_BLOCKED_KEY, false)
    }

    fun setBlocked(isBlocked: Boolean) {
        prefs.edit().putBoolean(IS_BLOCKED_KEY, isBlocked).apply()
    }

    companion object {
        const val SCROLL_COUNT_KEY = "scroll_count"
        const val IS_BLOCKED_KEY = "is_blocked"
    }
}
