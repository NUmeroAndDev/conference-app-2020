package io.github.droidkaigi.confsched2020.compose.status

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.Model
import io.github.droidkaigi.confsched2020.compose.R

@Model
object DrawerStatus {
    var selectedDrawerItem: DrawerItem = DrawerItem.Session
}

enum class DrawerItem(
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    Session(
        R.drawable.ic_event_outline_black_24dp,
        R.string.session_label
    ),
    About(
        R.drawable.ic_android_black_24dp,
        R.string.about_label
    ),
    Announce(
        R.drawable.ic_info_outline_black_24dp,
        R.string.announce_label
    ),
    Map(
        R.drawable.ic_place_black_24dp,
        R.string.map_label
    ),
    Sponsor(
        R.drawable.ic_business_black_24dp,
        R.string.sponsor_label
    ),
    Contributor(
        R.drawable.ic_people_outline_black_24dp,
        R.string.contributor_label
    ),
    Settings(
        R.drawable.ic_settings_black_24dp,
        R.string.setting_label
    );

    fun navigate() {
        val destination = when(this) {
            Session -> Screen.SessionList
            About -> Screen.About
            Announce -> Screen.Announce
            Map -> Screen.Map
            Sponsor -> Screen.Sponsor
            Contributor -> Screen.Contributor
            Settings -> Screen.Settings
        }
        navigateTo(destination = destination)
    }
}