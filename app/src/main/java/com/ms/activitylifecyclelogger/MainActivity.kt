package com.ms.activitylifecyclelogger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ms.activitylifecyclelogger.ui.theme.ActivityLifecycleLoggerTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    companion object {
        private const val LOG_TAG = "LifecycleLogger"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvent("onCreate()")
        enableEdgeToEdge()

        // Temporary example data. Real events will be shown in a later commit.
        val sampleEvents = listOf(
            LifecycleEvent("onResume()", "2026-11-01 19:08:42"),
            LifecycleEvent("onStart()", "2026-11-01 19:08:42"),
            LifecycleEvent("onCreate()", "2026-11-01 19:08:41")
        )

        setContent {
            ActivityLifecycleLoggerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LifecycleScreen(
                        currentState = "RESUMED",
                        events = sampleEvents,
                        isDemo = true,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logLifecycleEvent("onStart()")
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvent("onResume()")
    }

    override fun onPause() {
        logLifecycleEvent("onPause()")
        super.onPause()
    }

    override fun onStop() {
        logLifecycleEvent("onStop()")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycleEvent("onRestart()")
    }

    override fun onDestroy() {
        logLifecycleEvent("onDestroy()")
        super.onDestroy()
    }

    private fun logLifecycleEvent(event: String) {
        val timestamp = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).format(Date())

        Log.d(LOG_TAG, "$timestamp - $event")
    }
}
