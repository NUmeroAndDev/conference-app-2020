package io.github.droidkaigi.confsched2020.compose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.material.MaterialTheme

@Composable
fun AppTheme(
    children: () -> Unit
) {
    val isDarkTheme = +isSystemInDarkTheme()
    MaterialTheme(
        colors = if (isDarkTheme) darkThemeColor else lightThemeColor
    ) {
        children()
    }
}