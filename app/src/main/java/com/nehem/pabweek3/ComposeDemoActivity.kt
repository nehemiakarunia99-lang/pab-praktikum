package com.nehem.pabweek3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nehem.pabweek3.navigation.AppNavigation
import com.nehem.pabweek3.ui.theme.PabWeek3Theme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Tugas Praktikum PAB (Week 4 & 5)
 * Week 4: seluruh screen dibuat menggunakan Jetpack Compose + Material 3.
 * Week 5: seluruh screen dihubungkan menggunakan Jetpack Navigation 3
 * (state-driven navigation dengan NavDisplay & LocalBackStack).
 *
 * CATATAN: Activity ini TIDAK dijadikan launcher, jadi tidak mengubah
 * tampilan MainActivity (Week 3) saat aplikasi di-run seperti biasa.
 */

@AndroidEntryPoint
class ComposeDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PabWeek3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}
