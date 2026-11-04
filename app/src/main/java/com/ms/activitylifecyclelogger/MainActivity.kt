package com.ms.activitylifecyclelogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.ms.activitylifecyclelogger.ui.theme.ActivityLifecycleLoggerTheme

class MainActivity : ComponentActivity() {

    private lateinit var lifecycleHistory: LifecycleHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The same ViewModel is reused after an Activity recreation.
        lifecycleHistory = ViewModelProvider(this)[LifecycleHistoryViewModel::class.java]
        lifecycleHistory.recordEvent("onCreate()", "CREATED")

        enableEdgeToEdge()
        setContent {
            ActivityLifecycleLoggerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LifecycleScreen(
                        currentState = lifecycleHistory.currentState,
                        events = lifecycleHistory.events,
                        isDemo = false,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleHistory.recordEvent("onStart()", "STARTED")
    }

    override fun onResume() {
        super.onResume()
        lifecycleHistory.recordEvent("onResume()", "RESUMED")
    }

    override fun onPause() {
        lifecycleHistory.recordEvent("onPause()", "PAUSED")
        super.onPause()
    }

    override fun onStop() {
        lifecycleHistory.recordEvent("onStop()", "STOPPED")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        lifecycleHistory.recordEvent("onRestart()", "RESTARTED")
    }

    override fun onDestroy() {
        // During rotation, the ViewModel survives this Activity instance.
        lifecycleHistory.recordEvent("onDestroy()", "DESTROYED")
        super.onDestroy()
    }
}
