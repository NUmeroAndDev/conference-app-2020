package io.github.droidkaigi.confsched2020.compose.status

import androidx.compose.Model

@Model
object ScreenStatus {
    var currentScreen: Screen = Screen.SessionList
}

fun navigateTo(destination:Screen) {
    ScreenStatus.currentScreen = destination
}

sealed class Screen {
    object SessionList : Screen()
    object SessionDetail : Screen()
    object Speaker : Screen()
    object About : Screen()
    object Announce : Screen()
    object Map : Screen()
    object Sponsor : Screen()
    object Contributor : Screen()
    object Settings : Screen()
}