package com.ms.activitylifecyclelogger

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// The UI receives events from its caller. Real callbacks will be added later.
data class LifecycleEvent(
    val name: String,
    val timestamp: String
)

@Composable
fun LifecycleScreen(
    currentState: String,
    events: List<LifecycleEvent>,
    modifier: Modifier = Modifier,
    isDemo: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Text(
            text = "Activity Lifecycle",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Mobile Systems - Project 2",
            fontSize = 16.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = if (isDemo) "CURRENT STATE (EXAMPLE)" else "CURRENT STATE",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = currentState,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray)
                .padding(16.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = if (isDemo) "EVENT HISTORY (EXAMPLE)" else "EVENT HISTORY",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(1.dp, Color.LightGray)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(events) { event ->
                Column {
                    Text(
                        text = event.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = event.timestamp,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LifecycleScreenPreview() {
    LifecycleScreen(
        currentState = "RESUMED",
        events = listOf(
            LifecycleEvent("onResume()", "2026-11-01 19:08:42"),
            LifecycleEvent("onStart()", "2026-11-01 19:08:42"),
            LifecycleEvent("onCreate()", "2026-11-01 19:08:41")
        ),
        isDemo = true
    )
}
