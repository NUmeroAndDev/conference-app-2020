package io.github.droidkaigi.confsched2020.compose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle

@Composable
fun TopScreen() {
    AppTheme {
        Surface(color = (+MaterialTheme.colors()).background) {
            HelloWorldScreen()
        }
    }
}

@Composable
fun HelloWorldScreen() {
    AppBarLayout(
        appBar = {
            Toolbar(
                title = "DroidKaigi"
            )
        },
        content = {
            Text(
                text = "Hello world",
                style = TextStyle(
                    color = (+MaterialTheme.colors()).onBackground
                )
            )
        }
    )
}