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

// Holds UI state while Android recreates the Activity, for example on rotation.
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

        // Use the same event details in Logcat and on the screen.
        Log.d(LOG_TAG, "$timestamp - $event")
        currentState = state
        events.add(0, LifecycleEvent(event, timestamp))
    }
}
