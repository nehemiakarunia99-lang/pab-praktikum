package com.example.catapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrangeDark,
    onPrimary = OnPrimaryOrangeDark,
    primaryContainer = PrimaryContainerOrangeDark,
    onPrimaryContainer = OnPrimaryContainerOrangeDark,
    secondary = SecondaryTealDark,
    onSecondary = OnSecondaryTealDark,
    secondaryContainer = SecondaryContainerTealDark,
    onSecondaryContainer = OnSecondaryContainerTealDark,
    tertiary = TertiaryPinkDark,
    onTertiary = OnTertiaryPinkDark,
    tertiaryContainer = TertiaryContainerPinkDark,
    onTertiaryContainer = OnTertiaryContainerPinkDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,
    onPrimary = OnPrimaryOrange,
    primaryContainer = PrimaryContainerOrange,
    onPrimaryContainer = OnPrimaryContainerOrange,
    secondary = SecondaryTeal,
    onSecondary = OnSecondaryTeal,
    secondaryContainer = SecondaryContainerTeal,
    onSecondaryContainer = OnSecondaryContainerTeal,
    tertiary = TertiaryPink,
    onTertiary = OnTertiaryPink,
    tertiaryContainer = TertiaryContainerPink,
    onTertiaryContainer = OnTertiaryContainerPink
)

@Composable
fun CatappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
