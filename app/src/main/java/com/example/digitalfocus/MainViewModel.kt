package com.example.digitalfocus

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalfocus.data.DataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataRepository = DataRepository(application)

    private val _scrollCount = MutableStateFlow(0)
    val scrollCount: StateFlow<Int> = _scrollCount.asStateFlow()

    init {
        viewModelScope.launch {
            // This is a simple way to update the count. For a real app, you'd want a more robust
            // mechanism to observe changes from the service, like a broadcast receiver or a flow.
            kotlinx.coroutines.delay(500) //  Update every 500ms
            _scrollCount.value = dataRepository.getScrollCount()
        }
    }
}