package com.ms.activitylifecyclelogger

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Each event keeps the callback name and the time at which it occurred.
data class LifecycleEvent(
    val name: String,
    val timestamp: String
)

private val Paper = Color(0xFFF6F4EE)
private val Ink = Color(0xFF222522)
private val Muted = Color(0xFF6D716C)
private val Accent = Color(0xFFC64B31)
private val Rule = Color(0xFFC8C9C2)

@Composable
fun LifecycleScreen(
    currentState: String,
    events: List<LifecycleEvent>,
    modifier: Modifier = Modifier,
    isDemo: Boolean = false,
    onClearHistory: () -> Unit = {}
) {
    // One scrollable list contains both the header and the event history.
    // It works in portrait and landscape without changing the layout.
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Paper),
        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 26.dp,
            bottom = 24.dp
        )
    ) {
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MOBILE SYSTEMS / 02",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        letterSpacing = 1.sp,
                        color = Muted
                    )
                    Text(
                        text = "ANDROID LAB",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = Muted
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = "Activity",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Light,
                    lineHeight = 43.sp,
                    color = Ink
                )
                Text(
                    text = "lifecycle.",
                    fontSize = 43.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 45.sp,
                    color = Ink
                )
                Spacer(modifier = Modifier.height(23.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Ink)
                        .padding(18.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .width(8.dp)
                                .height(8.dp)
                                .background(Accent)
                        )
                        Spacer(modifier = Modifier.width(9.dp))
                        Text(
                            text = if (isDemo) "ACTIVITY STATE / DEMO" else "ACTIVITY STATE / LIVE",
                            color = Color(0xFFD1D4CA),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            letterSpacing = 0.8.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(11.dp))
                    Text(
                        text = currentState,
                        color = Color.White,
                        fontSize = 29.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "EVENT LOG",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.1.sp,
                            color = Ink
                        )
                        Text(
                            text = "${events.size} recorded / newest first",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                            color = Muted
                        )
                    }
                    Text(
                        text = "CLEAR LOG",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Accent,
                        modifier = Modifier
                            .clickable(enabled = events.isNotEmpty()) { onClearHistory() }
                            .padding(vertical = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Ink)
                )
            }
        }

        // The newest entries appear first. LazyColumn only composes visible rows.
        itemsIndexed(events) { index, event ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = (index + 1).toString().padStart(2, '0'),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    color = Accent,
                    modifier = Modifier.width(38.dp)
                )
                Column {
                    Text(
                        text = event.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Ink
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = event.timestamp,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Muted
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Rule)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LifecycleScreenPreview() {
    LifecycleScreen(
        currentState = "RESUMED",
        events = listOf(
            LifecycleEvent("onResume()", "2026-11-04 22:34:46"),
            LifecycleEvent("onStart()", "2026-11-04 22:34:46"),
            LifecycleEvent("onCreate()", "2026-11-04 22:34:46"),
            LifecycleEvent("onDestroy()", "2026-11-04 22:34:45")
        ),
        isDemo = true
    )
}
