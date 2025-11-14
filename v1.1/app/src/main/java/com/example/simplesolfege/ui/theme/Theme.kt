package com.example.simplesolfege.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Colors = lightColorScheme(
    primary = BluePrimary,
    background = BackgroundDark,
    onBackground = TextDark
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Colors,
        typography = Typography,
        content = content
    )
}
