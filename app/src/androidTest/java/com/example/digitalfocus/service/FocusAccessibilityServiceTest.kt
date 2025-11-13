package com.example.digitalfocus.service

import android.content.Context
import android.view.accessibility.AccessibilityEvent
import androidx.test.platform.app.InstrumentationRegistry
import com.example.digitalfocus.data.DataRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FocusAccessibilityServiceTest {

    private lateinit var service: FocusAccessibilityService
    private lateinit var dataRepository: DataRepository
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        dataRepository = DataRepository(context)
        service = FocusAccessibilityService().apply {
            // The DataRepository is still needed for the blocking logic
            this.dataRepository = dataRepository
        }
    }

    @Test
    fun `onAccessibilityEvent increments scroll count for YouTube`() {
        // Arrange: Ensure the test counter is reset before the test.
        service.scrollCountForTest = 0
        val event = AccessibilityEvent().apply {
            eventType = AccessibilityEvent.TYPE_VIEW_SCROLLED
            packageName = "com.google.android.youtube"
        }

        // Act
        service.onAccessibilityEvent(event)

        // Assert: Check the dedicated in-memory test counter.
        assertThat(service.scrollCountForTest).isEqualTo(1)
    }
}