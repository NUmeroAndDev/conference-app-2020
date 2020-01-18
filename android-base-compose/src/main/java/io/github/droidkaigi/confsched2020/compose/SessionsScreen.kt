package io.github.droidkaigi.confsched2020.compose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.withOpacity
import io.github.droidkaigi.confsched2020.model.MockModel
import io.github.droidkaigi.confsched2020.model.Session

@Composable
fun SessionsScreen(openDrawer: () -> Unit) {
    AppBarLayout(
        appBar = {
            Toolbar(
                title = "DroidKaigi",
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
            SessionList(MockModel.createMockSessionList())
        }
    )
}

@Composable
fun SessionList(sessionList: List<Session>) {
    VerticalScroller {
        Column {
            sessionList.forEach {
                SessionItem(it)
            }
        }
    }
}

@Composable
fun SessionItem(
    session: Session,
    onClick: (() -> Unit)? = null
) {
    Ripple(bounded = true) {
        Clickable(onClick = onClick) {
            SessionItemContent(session)
        }
    }
}

@Composable
fun SessionItemContent(
    session: Session
) {
    Row {
        val materialColor = +MaterialTheme.colors()
        val typography = +MaterialTheme.typography()
        Container(
            alignment = Alignment.TopCenter,
            width = 72.dp,
            children = {
                Padding(top = 16.dp) {
                    Text(
                        text = session.startTimeText,
                        style = typography.subtitle1.withOpacity(0.38F)
                    )
                }
            }
        )
        Column(modifier = Flexible(1f)) {
            Padding(padding = 16.dp) {
                Column {
                    Text(
                        text = session.room.name.ja,
                        style = typography.caption.withOpacity(0.38F)
                    )
                    HeightSpacer(height = 8.dp)
                    SessionTitleText(title = session.title.ja)
                }
            }
        }
        Padding(padding = 16.dp) {
            VectorImageButton(
                id = R.drawable.ic_bookmark_border_black_24dp,
                tint = materialColor.secondary,
                onClick = {
                    // TODO Toggle favorite
                }
            )
        }
    }
}

@Composable
fun SessionTitleText(title: String) {
    val materialColor = +MaterialTheme.colors()
    val typography = +MaterialTheme.typography()
    val isDarkTheme = +isSystemInDarkTheme()
    Text(
        text = title,
        style = typography.h6.copy(
            color = if (isDarkTheme) {
                materialColor.onSurface
            } else {
                materialColor.primary
            }
        )
    )
}