# Activity Lifecycle Logger

Mobile Systems | Project 2  
Bialystok University of Technology, Faculty of Computer Science

## Authors

- Gonçalo Ferreira, Student ID: 97574
- Pedro Cortesão, Student ID: 97575

## Project overview

A simple Android application that demonstrates how an Activity changes state. Every lifecycle callback is recorded with its name and local date and time, both on screen and in Android Logcat.

The screen uses a diagnostic-style layout with a live state indicator, a scrollable event log and a **CLEAR LOG** control.

## Features

- Tracks all seven callbacks: `onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onStop()`, `onRestart()` and `onDestroy()`.
- Shows the current recorded state and the event history, with the newest events first.
- Writes the same events to Logcat using the tag `LifecycleLogger`.
- Keeps the event history through Activity recreation caused by screen rotation, using a `ViewModel`.
- Allows vertical scrolling of the full page in portrait and landscape.
- Clears the on-screen history using **CLEAR LOG**. This does not delete Logcat output.

## Technologies

- Kotlin
- Jetpack Compose
- AndroidX ViewModel
- Android Studio and Gradle

## Main source files

| File | Purpose |
| --- | --- |
| `app/src/main/java/com/ms/activitylifecyclelogger/MainActivity.kt` | Activity callbacks, Compose entry point and event forwarding |
| `app/src/main/java/com/ms/activitylifecyclelogger/LifecycleHistoryViewModel.kt` | Observable event history, current state and Logcat logging |
| `app/src/main/java/com/ms/activitylifecyclelogger/LifecycleScreen.kt` | User interface, event list and CLEAR LOG control |

## Run the project

1. Clone the repository or download its source code.
2. Open the project folder in Android Studio.
3. Allow Gradle to sync.
4. Select a compatible Android emulator or a physical Android device.
5. Run the `app` configuration.
6. Open **Logcat** and filter by `tag:LifecycleLogger` to inspect the callbacks.

The project uses Gradle 8.9 and was successfully built using JDK 21.
The project was tested on an Android API 35 emulator.

## Implementation notes

- Lifecycle events are recorded with timestamps, with the newest entries displayed first.
- The event history is kept in a ViewModel during screen rotation.
- The history is stored in memory and is not permanently saved.
- The CLEAR LOG option clears the event list without deleting previous Logcat messages.
- The onDestroy() callback is not guaranteed when Android terminates the application process.
