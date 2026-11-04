package com.ms.activitylifecyclelogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ms.activitylifecyclelogger.ui.theme.ActivityLifecycleLoggerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Temporary example data. Actual lifecycle logging will be added later.
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
}
