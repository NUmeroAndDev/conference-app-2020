package io.github.droidkaigi.confsched2020.compose

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.WithDensity
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Size
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.vectorResource

@Composable
fun AppBarLayout(
    appBar: @Composable() () -> Unit,
    content: @Composable() () -> Unit
) {
    FlexColumn {
        inflexible { appBar() }
        expanded(1.0f) { content() }
    }
}

// Without action toolbar
@Composable
fun Toolbar(
    title: String? = null,
    isShowArrowBack: Boolean = false,
    onBackPressed: (() -> Unit)? = null
) {
    val navigationIcon: @Composable() (() -> Unit)? = if (isShowArrowBack) {
        {
            VectorImageButton(
                id = R.drawable.ic_arrow_back_black_24dp,
                tint = (+MaterialTheme.colors()).onPrimary
            ) {
                onBackPressed?.invoke()
            }
        }
    } else null
    TopAppBar(
        title = {
            if (title != null) {
                Text(title)
            }
        },
        navigationIcon = navigationIcon
    )
}

@Composable
fun VectorImageButton(
    @DrawableRes id: Int,
    tint: Color = Color.Transparent,
    onClick: () -> Unit
) {
    Ripple(bounded = false) {
        Clickable(onClick = onClick) {
            VectorImage(
                id = id,
                tint = tint
            )
        }
    }
}

@Composable
fun VectorImage(modifier: Modifier = Modifier.None, @DrawableRes id: Int, tint: Color) {
    val vector = +vectorResource(id)
    WithDensity {
        Container(
            modifier = modifier wraps Size(vector.defaultWidth.toDp(), vector.defaultHeight.toDp())
        ) {
            DrawVector(vector, tint)
        }
    }
}