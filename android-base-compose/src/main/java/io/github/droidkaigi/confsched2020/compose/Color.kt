package io.github.droidkaigi.confsched2020.compose

import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette

val lightThemeColor = ColorPalette(
    primary = Color("#6200EE"),
    primaryVariant = Color("#3700B3"),
    secondary = Color("#03DAD6"),
    secondaryVariant = Color("#018786"),
    background = Color("#FFFFFF"),
    surface = Color("#FFFFFF"),
    error = Color("#B00020"),
    onPrimary = Color("#FFFFFF"),
    onSecondary = Color("#000000"),
    onBackground = Color("#000000"),
    onSurface = Color("#000000"),
    onError = Color("#FFFFFF")
)

val darkThemeColor = ColorPalette(
    primary = Color("#BB86FC"),
    primaryVariant = Color("#3700B3"),
    secondary = Color("#03DAD6"),
    secondaryVariant = Color("#03DAD6"),
    background = Color("#121212"),
    surface = Color("#121212"),
    error = Color("#CF6679"),
    onPrimary = Color("#000000"),
    onSecondary = Color("#000000"),
    onBackground = Color("#FFFFFF"),
    onSurface = Color("#FFFFFF"),
    onError = Color("#000000")
)

fun Color(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}