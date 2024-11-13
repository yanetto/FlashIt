package com.yanetto.flashit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.yanetto.flashit.R

private val themeFontFamily = FontFamily(
    Font(R.font.inter)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 96.sp
    ),
    displayMedium = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 88.sp
    ),
    displaySmall = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 80.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 72.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 64.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 56.sp
    ),
    titleLarge = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 48.sp
    ),
    titleMedium = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 40.sp
    ),
    titleSmall = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 32.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 12.sp
    ),
    labelMedium = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 10.sp
    ),
    labelSmall = TextStyle(
        fontFamily = themeFontFamily,
        fontSize = 8.sp
    )
)