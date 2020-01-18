package io.github.droidkaigi.confsched2020.compose

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
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
    closeDrawer: () -> Unit
) {
    Column(modifier = Expanded) {

        TopDrawerHeader()

        DrawerDivider()

        DrawerButton(
            modifier = ExpandedWidth,
            icon = R.drawable.ic_event_outline_black_24dp,
            label = +stringResource(R.string.session_label),
            isSelected = true
        ) {
            closeDrawer()
        }

        DrawerDivider()

        DrawerButton(
            icon = R.drawable.ic_android_black_24dp,
            label = +stringResource(R.string.about_label),
            isSelected = false
        ) {
            closeDrawer()
        }
        DrawerButton(
            icon = R.drawable.ic_info_outline_black_24dp,
            label = +stringResource(R.string.announce_label),
            isSelected = false
        ) {
            closeDrawer()
        }
        DrawerButton(
            icon = R.drawable.ic_place_black_24dp,
            label = +stringResource(R.string.map_label),
            isSelected = false
        ) {
            closeDrawer()
        }

        DrawerDivider()

        DrawerButton(
            icon = R.drawable.ic_business_black_24dp,
            label = +stringResource(R.string.sponsor_label),
            isSelected = false
        ) {
            closeDrawer()
        }
        DrawerButton(
            icon = R.drawable.ic_people_outline_black_24dp,
            label = +stringResource(R.string.contributor_label),
            isSelected = false
        ) {
            closeDrawer()
        }
        DrawerButton(
            icon = R.drawable.ic_settings_black_24dp,
            label = +stringResource(R.string.setting_label),
            isSelected = false
        ) {
            closeDrawer()
        }
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
    Surface(color = (+MaterialTheme.colors()).background) {
        SessionsScreen(openDrawer)
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