package com.example.simplesolfege.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteStaff(notePosition: Float) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val spacing = size.height / 6f

        for (i in 1..5) {
            drawLine(
                color = Color(0xFFCCCCCC),
                start = androidx.compose.ui.geometry.Offset(0f, spacing * i),
                end = androidx.compose.ui.geometry.Offset(size.width, spacing * i),
                strokeWidth = 3f
            )
        }

        drawOval(
            color = Color(0xFF4FA3FF),
            topLeft = androidx.compose.ui.geometry.Offset(
                x = size.width / 2 - 12,
                y = size.height * (1f - notePosition) - 12
            ),
            size = androidx.compose.ui.geometry.Size(24f, 18f)
        )
    }
}
