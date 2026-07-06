package com.nehem.pabweek3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nehem.pabweek3.ui.screens.GenerateQrScreen
import com.nehem.pabweek3.ui.screens.HomeScreen
import com.nehem.pabweek3.ui.screens.LoginScreen
import com.nehem.pabweek3.ui.screens.ProfileScreen
import com.nehem.pabweek3.ui.theme.PabWeek3Theme

/**
 * Tugas Praktikum PAB (Week 4)
 * Menampilkan seluruh screen fitur SCAS (Login, Home, Generate QR, Profile)
 * menggunakan Jetpack Compose + Material 3.
 *
 * CATATAN: Activity ini TIDAK dijadikan launcher, jadi tidak mengubah
 * tampilan MainActivity (Week 3) saat aplikasi di-run seperti biasa.
 */
class ComposeDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PabWeek3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ComposeDemoApp()
                }
            }
        }
    }
}

private enum class Screen(val label: String) {
    Login("Login"),
    Home("Home"),
    GenerateQr("QR Code"),
    Profile("Profile")
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ComposeDemoApp() {
    var currentScreen by remember { mutableStateOf(Screen.Login) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentScreen == Screen.Login,
                    onClick = { currentScreen = Screen.Login },
                    icon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    label = { Text(Screen.Login.label) }
                )
                NavigationBarItem(
                    selected = currentScreen == Screen.Home,
                    onClick = { currentScreen = Screen.Home },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text(Screen.Home.label) }
                )
                NavigationBarItem(
                    selected = currentScreen == Screen.GenerateQr,
                    onClick = { currentScreen = Screen.GenerateQr },
                    icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
                    label = { Text(Screen.GenerateQr.label) }
                )
                NavigationBarItem(
                    selected = currentScreen == Screen.Profile,
                    onClick = { currentScreen = Screen.Profile },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                    label = { Text(Screen.Profile.label) }
                )
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            when (currentScreen) {
                Screen.Login -> LoginScreen(onLoginClick = { _, _ -> currentScreen = Screen.Home })
                Screen.Home -> HomeScreen()
                Screen.GenerateQr -> GenerateQrScreen()
                Screen.Profile -> ProfileScreen()
            }
        }
    }
}
