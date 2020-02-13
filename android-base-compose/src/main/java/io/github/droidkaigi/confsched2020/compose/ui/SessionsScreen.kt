package io.github.droidkaigi.confsched2020.compose.ui

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.unit.dp
import io.github.droidkaigi.confsched2020.compose.AppBarLayout
import io.github.droidkaigi.confsched2020.compose.R
import io.github.droidkaigi.confsched2020.compose.Toolbar
import io.github.droidkaigi.confsched2020.compose.VectorImageButton
import io.github.droidkaigi.confsched2020.compose.observe
import io.github.droidkaigi.confsched2020.data.api.ApiComponent
import io.github.droidkaigi.confsched2020.data.db.DbComponent
import io.github.droidkaigi.confsched2020.data.firestore.FirestoreComponent
import io.github.droidkaigi.confsched2020.data.repository.DaggerRepositoryComponent
import io.github.droidkaigi.confsched2020.model.Session
import io.github.droidkaigi.confsched2020.model.SessionContents
import io.github.droidkaigi.confsched2020.model.repository.SessionRepository
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import timber.log.debug

class ViewModel(sessionRepository: SessionRepository) {

    val sessionsLoadStateLiveData: LiveData<SessionContents> = liveData {
        emitSource(
            sessionRepository.sessionContents()
                .asLiveData()
        )
        try {
            sessionRepository.refresh()
        } catch (e: Exception) {
            // We can show sessions with cache
            Timber.debug(e) { "Fail sessionRepository.refresh()" }
        }
    }
}

@Composable
fun SessionsScreen(openDrawer: () -> Unit) {
    // FIXME 一時的なrepositoryの生成
    val context = ambient(ContextAmbient)
    val apiComponent = ApiComponent.factory().create(context)
    val dbComponent = DbComponent.factory().create(context, Dispatchers.IO, "droidkaigi.db")
    val repository = DaggerRepositoryComponent.factory().create(
        context = context,
        droidKaigiApi = apiComponent.DroidKaigiApi(),
        googleFormApi = apiComponent.GoogleFormApi(),
        sessionDatabase = dbComponent.sessionDatabase(),
        sponsorDatabase = dbComponent.sponsorDatabase(),
        announcementDatabase = dbComponent.announcementDatabase(),
        staffDatabase = dbComponent.staffDatabase(),
        contributorDatabase = dbComponent.contributorDatabase(),
        firestore = FirestoreComponent.factory().create(Dispatchers.IO).firestore()
    ).sessionRepository()

    val vm = ViewModel(repository)
    val liveData = remember { vm.sessionsLoadStateLiveData }
    val sessionContents = observe(liveData)

    AppBarLayout(
        appBar = {
            Toolbar(
                title = "DroidKaigi",
                navigationIcon = {
                    VectorImageButton(
                        id = R.drawable.ic_menu_black_24dp,
                        tint = MaterialTheme.colors().onPrimary
                    ) {
                        openDrawer()
                    }
                }
            )
        },
        content = {
            SessionList(sessionContents?.sessions ?: emptyList())
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
            SessionItemContent(
                session
            )
        }
    }
}

@Composable
fun SessionItemContent(
    session: Session
) {
    Row {
        val materialColor = MaterialTheme.colors()
        val typography = MaterialTheme.typography()
        Container(
            alignment = Alignment.TopCenter,
            width = 72.dp,
            children = {
                Padding(top = 16.dp) {
                    Text(
                        text = session.startTimeText,
                        style = typography.subtitle1.copy(
                            color = materialColor.onBackground.copy(
                                alpha = 0.38F
                            )
                        )
                    )
                }
            }
        )
        Column(modifier = LayoutFlexible(1f)) {
            Padding(padding = 16.dp) {
                Column {
                    Text(
                        text = session.room.name.ja,
                        style = typography.caption.copy(
                            color = materialColor.onBackground.copy(
                                alpha = 0.38F
                            )
                        )
                    )
                    Spacer(modifier = LayoutHeight(8.dp) + LayoutWidth.Fill)
                    SessionTitleText(
                        title = session.title.ja
                    )
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
    val materialColor = MaterialTheme.colors()
    val typography = MaterialTheme.typography()
    val isDarkTheme = isSystemInDarkTheme()
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
