package io.github.droidkaigi.confsched2020.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import io.github.droidkaigi.confsched2020.compose.ui.TopScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopScreen()
        }
    }
}
