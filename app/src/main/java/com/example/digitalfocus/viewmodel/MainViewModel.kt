package com.example.digitalfocus.viewmodel

import android.app.Application
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import com.example.digitalfocus.data.DataRepository
import com.example.digitalfocus.service.FocusAccessibilityService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepository = DataRepository(application)
    val scrollCount = dataRepository.scrollCount

    private val _isServiceEnabled = MutableStateFlow(false)
    val isServiceEnabled: StateFlow<Boolean> = _isServiceEnabled

    fun checkServiceStatus() {
        val context = getApplication<Application>()
        val serviceId = "${context.packageName}/${FocusAccessibilityService::class.java.name}"
        try {
            val enabledServices = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )
            _isServiceEnabled.value = enabledServices?.contains(serviceId, ignoreCase = false) ?: false
        } catch (e: Exception) {
            _isServiceEnabled.value = false
        }
    }
}
