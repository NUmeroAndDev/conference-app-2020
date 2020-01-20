package io.github.droidkaigi.confsched2020.compose.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import io.github.droidkaigi.confsched2020.compose.AppBarLayout
import io.github.droidkaigi.confsched2020.compose.R
import io.github.droidkaigi.confsched2020.compose.Toolbar
import io.github.droidkaigi.confsched2020.compose.VectorImageButton

@Composable
fun EmptyScreen(openDrawer: () -> Unit) {
    AppBarLayout(
        appBar = {
            Toolbar(
                title = "EmptyScreen",
                navigationIcon = {
                    VectorImageButton(
                        id = R.drawable.ic_menu_black_24dp,
                        tint = (+MaterialTheme.colors()).onPrimary
                    ) {
                        openDrawer()
                    }
                }
            )
        },
        content = {
            Column {
                Text("Not implemented screen")
            }
        }
    )
}