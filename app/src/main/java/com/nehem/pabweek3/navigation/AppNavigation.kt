package com.nehem.pabweek3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.nehem.pabweek3.ui.screens.CourseDetailScreen
import com.nehem.pabweek3.ui.screens.GenerateQrScreen
import com.nehem.pabweek3.ui.screens.HomeScreen
import com.nehem.pabweek3.ui.screens.LoginScreen
import com.nehem.pabweek3.ui.screens.ProfileScreen
import com.nehem.pabweek3.ui.screens.TodoScreen

/**
 * Root Composable navigasi aplikasi (Week 5 - Navigation 3).
 * Back stack dibuat di sini, lalu disediakan ke seluruh screen
 * melalui CompositionLocalProvider (LocalBackStack).
 */
@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(Route.Login)

    CompositionLocalProvider(LocalBackStack provides backStack) {
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<Route.Login> { LoginScreen() }
                entry<Route.Home> { HomeScreen() }
                entry<Route.GenerateQr> { GenerateQrScreen() }
                entry<Route.Profile> { ProfileScreen() }
                entry<Route.Todo> { TodoScreen() }
                entry<Route.CourseDetail> { route ->
                    CourseDetailScreen(
                        courseId = route.courseId,
                        courseName = route.courseName
                    )
                }
            }
        )
    }
}
