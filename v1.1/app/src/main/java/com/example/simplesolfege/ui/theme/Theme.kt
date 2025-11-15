package com.example.simplesolfege.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColors = darkColorScheme(
    primary = AccentBlue,
    background = BackgroundDark,
    surface = CardDark,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = Typography,
        content = content
    )
}
