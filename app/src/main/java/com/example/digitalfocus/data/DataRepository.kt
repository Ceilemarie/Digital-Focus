package com.example.digitalfocus.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataRepository(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("DigitalFocusPrefs", Context.MODE_PRIVATE)

    private val _scrollCount = MutableStateFlow(sharedPreferences.getInt("scroll_count", 0))
    val scrollCount: StateFlow<Int> = _scrollCount

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == "scroll_count") {
            _scrollCount.value = sharedPreferences.getInt("scroll_count", 0)
        }
    }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    fun getScrollCount(): Int {
        return sharedPreferences.getInt("scroll_count", 0)
    }

    fun setScrollCount(count: Int) {
        sharedPreferences.edit().putInt("scroll_count", count).apply()
    }

    fun isBlocked(): Boolean {
        return sharedPreferences.getBoolean("is_blocked", false)
    }

    fun setBlocked(isBlocked: Boolean) {
        sharedPreferences.edit().putBoolean("is_blocked", isBlocked).apply()
    }
}
