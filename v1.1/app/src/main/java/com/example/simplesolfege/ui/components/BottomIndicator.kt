package com.example.simplesolfege.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomIndicator(active: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(6) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        if (index == active)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f),
                        CircleShape
                    )
            )
        }
    }
}
