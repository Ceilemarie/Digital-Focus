package com.example.digitalfocus.data

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class DataRepositoryTest {

    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataRepository = DataRepository(context)
        // Clear shared preferences before each test
        context.getSharedPreferences("DigitalFocusPrefs", Context.MODE_PRIVATE).edit().clear().commit()
    }

    @Test
    fun `getScrollCount returns default value`() {
        assertThat(dataRepository.getScrollCount()).isEqualTo(0)
    }

    @Test
    fun `setScrollCount updates value`() {
        dataRepository.setScrollCount(5)
        assertThat(dataRepository.getScrollCount()).isEqualTo(5)
    }

    @Test
    fun `isBlocked returns default value`() {
        assertThat(dataRepository.isBlocked()).isFalse()
    }

    @Test
    fun `setBlocked updates value`() {
        dataRepository.setBlocked(true)
        assertThat(dataRepository.isBlocked()).isTrue()
    }
}