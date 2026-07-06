package com.nehem.pabweek3.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

/**
 * CompositionLocal yang menyimpan back stack navigasi (state-driven navigation).
 * Dengan ini, setiap screen bisa mengakses & memodifikasi back stack
 * (backStack.add / backStack.removeLastOrNull) tanpa perlu mengoper
 * parameter callback secara manual dari root ke setiap Composable.
 */
val LocalBackStack = compositionLocalOf<NavBackStack<NavKey>> {
    error("LocalBackStack belum disediakan. Bungkus dengan CompositionLocalProvider di AppNavigation.")
}
