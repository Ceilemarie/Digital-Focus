import android.content.Context
import android.content.SharedPreferences

class DataRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("digital_focus_prefs", Context.MODE_PRIVATE)

    companion object {
        const val SCROLL_COUNT_KEY = "scroll_count"
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
}