package io.github.droidkaigi.confsched2020.compose.ui

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.SimpleImage
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.Expanded
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Gravity
import androidx.ui.layout.MinHeight
import androidx.ui.layout.Row
import androidx.ui.layout.WidthSpacer
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.DrawerState
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ModalDrawerLayout
import androidx.ui.material.TextButtonStyle
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.res.stringResource
import io.github.droidkaigi.confsched2020.compose.AppTheme
import io.github.droidkaigi.confsched2020.compose.R
import io.github.droidkaigi.confsched2020.compose.VectorImage
import io.github.droidkaigi.confsched2020.compose.status.DrawerItem
import io.github.droidkaigi.confsched2020.compose.status.DrawerStatus
import io.github.droidkaigi.confsched2020.compose.status.Screen
import io.github.droidkaigi.confsched2020.compose.status.ScreenStatus

@Composable
fun TopScreen() {
    var state by +state { DrawerState.Closed }
    AppTheme {
        ModalDrawerLayout(
            drawerState = state,
            onStateChange = {
                if (state != it) {
                    state = it
                }
            },
            drawerContent = {
                TopDrawerContent {
                    it.navigate()
                    state = DrawerState.Closed
                }
            },
            bodyContent = {
                TopContent {
                    state = DrawerState.Opened
                }
            }
        )
    }
}

@Composable
fun TopDrawerContent(
    closeDrawerAndNavigate: (DrawerItem) -> Unit
) {
    Column(modifier = Expanded) {
        TopDrawerHeader()
        DrawerDivider()
        DrawerItem(
            item = DrawerItem.Session,
            action = closeDrawerAndNavigate
        )
        DrawerDivider()
        DrawerItem(
            item = DrawerItem.About,
            action = closeDrawerAndNavigate
        )
        DrawerItem(
            item = DrawerItem.Announce,
            action = closeDrawerAndNavigate
        )
        DrawerItem(
            item = DrawerItem.Map,
            action = closeDrawerAndNavigate
        )
        DrawerDivider()
        DrawerItem(
            item = DrawerItem.Sponsor,
            action = closeDrawerAndNavigate
        )
        DrawerItem(
            item = DrawerItem.Contributor,
            action = closeDrawerAndNavigate
        )
        DrawerItem(
            item = DrawerItem.Settings,
            action = closeDrawerAndNavigate
        )
    }
}

@Composable
fun TopDrawerHeader() {
    // FIXME not same design spec
    Container(
        modifier = ExpandedWidth,
        padding = EdgeInsets(
            top = 24.dp,
            bottom = 24.dp
        )
    ) {
        val image = +imageResource(R.drawable.logo)
        SimpleImage(image = image)
    }
}

@Composable
fun TopContent(openDrawer: () -> Unit) {
    Crossfade(ScreenStatus.currentScreen) { screen ->
        Surface(color = (+MaterialTheme.colors()).background) {
            when (screen) {
                is Screen.SessionList -> SessionsScreen(
                    openDrawer
                )
                is Screen.SessionDetail -> EmptyScreen(
                    openDrawer
                )
                is Screen.Speaker -> EmptyScreen(
                    openDrawer
                )
                is Screen.About -> EmptyScreen(
                    openDrawer
                )
                is Screen.Announce -> EmptyScreen(
                    openDrawer
                )
                is Screen.Map -> EmptyScreen(
                    openDrawer
                )
                is Screen.Sponsor -> EmptyScreen(
                    openDrawer
                )
                is Screen.Contributor -> EmptyScreen(
                    openDrawer
                )
                is Screen.Settings -> EmptyScreen(
                    openDrawer
                )
            }
        }
    }
}

@Composable
fun DrawerItem(
    item: DrawerItem,
    action: (DrawerItem) -> Unit
) {
    val isSelected = DrawerStatus.selectedDrawerItem == item
    DrawerButton(
        icon = item.icon,
        label = +stringResource(item.label),
        isSelected = isSelected
    ) {
        if (isSelected) return@DrawerButton
        DrawerStatus.selectedDrawerItem = item
        action(item)
    }
}

@Composable
private fun DrawerButton(
    modifier: Modifier = ExpandedWidth,
    @DrawableRes icon: Int,
    label: String,
    isSelected: Boolean,
    action: () -> Unit
) {
    val colors = +MaterialTheme.colors()
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.38f)
    }
    val typography = +MaterialTheme.typography()
    Button(
        modifier = modifier,
        onClick = action,
        style = TextButtonStyle().copy(
            shape = RectangleShape,
            rippleColor = colors.onSurface
        )
    ) {
        Container(
            padding = EdgeInsets(left = 16.dp, right = 16.dp),
            alignment = Alignment.CenterLeft
        ) {
            Row(
                modifier = MinHeight(48.dp) wraps ExpandedWidth
            ) {
                VectorImage(
                    modifier = Gravity.Center,
                    id = icon,
                    tint = textIconColor
                )
                WidthSpacer(16.dp)
                Text(
                    modifier = Gravity.Center,
                    text = label,
                    style = typography.subtitle2.copy(
                        color = textIconColor
                    )
                )
            }
        }
    }
}

@Composable
fun DrawerDivider() {
    val materialColor = +MaterialTheme.colors()
    Divider(
        color = materialColor.onSurface.copy(
            alpha = 0.12F
        )
    )
}