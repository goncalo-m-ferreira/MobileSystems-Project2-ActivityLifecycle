package com.ms.activitylifecyclelogger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ms.activitylifecyclelogger.ui.theme.ActivityLifecycleLoggerTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    companion object {
        private const val LOG_TAG = "LifecycleLogger"
    }

    // Observable values: the Compose UI updates when a callback changes them.
    private val lifecycleEvents = mutableStateListOf<LifecycleEvent>()
    private var currentState by mutableStateOf("INITIALIZED")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvent("onCreate()", "CREATED")
        enableEdgeToEdge()

        setContent {
            ActivityLifecycleLoggerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LifecycleScreen(
                        currentState = currentState,
                        events = lifecycleEvents,
                        isDemo = false,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycleEvent("onStart()", "STARTED")
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvent("onResume()", "RESUMED")
    }

    override fun onPause() {
        logLifecycleEvent("onPause()", "PAUSED")
        super.onPause()
    }

    override fun onStop() {
        logLifecycleEvent("onStop()", "STOPPED")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycleEvent("onRestart()", "RESTARTED")
    }

    override fun onDestroy() {
        logLifecycleEvent("onDestroy()", "DESTROYED")
        super.onDestroy()
    }

    private fun logLifecycleEvent(event: String, state: String) {
        val timestamp = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).format(Date())

        // Keep the Logcat output required by the assignment.
        Log.d(LOG_TAG, "$timestamp - $event")

        // Insert the newest event at the top of the on-screen history.
        currentState = state
        lifecycleEvents.add(0, LifecycleEvent(event, timestamp))
    }
}
