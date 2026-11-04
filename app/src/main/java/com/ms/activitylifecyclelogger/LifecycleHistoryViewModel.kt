package com.ms.activitylifecyclelogger

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Keeps the event history when the Activity is recreated after rotation.
class LifecycleHistoryViewModel : ViewModel() {

    companion object {
        private const val LOG_TAG = "LifecycleLogger"
    }

    val events = mutableStateListOf<LifecycleEvent>()

    var currentState by mutableStateOf("INITIALIZED")
        private set

    fun recordEvent(event: String, state: String) {
        val timestamp = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).format(Date())

        Log.d(LOG_TAG, "$timestamp - $event")
        currentState = state
        events.add(0, LifecycleEvent(event, timestamp))
    }

    // Clears the on-screen list. Existing Logcat messages are not deleted.
    fun clearHistory() {
        events.clear()
    }
}
