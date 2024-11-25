package com.yanetto.flashit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple,
    secondary = DarkPurple,
    tertiary = Grey,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    error = Error
)

private val LightColorScheme = lightColorScheme(
    primary = Purple,
    secondary = LightPurple,
    tertiary = Grey,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    error = Error
)

@Composable
fun FlashItTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}