package com.example.simplesolfege.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.ui.theme.LineColor
import com.example.simplesolfege.ui.theme.AccentBlue

@Composable
fun NoteStaff(notePosition: Float) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        val spacing = size.height / 6f

        // 5 ліній
        for (i in 1..5) {
            drawLine(
                color = LineColor,
                start = androidx.compose.ui.geometry.Offset(0f, spacing * i),
                end = androidx.compose.ui.geometry.Offset(size.width, spacing * i),
                strokeWidth = 2f
            )
        }

        // вертикальна "нота"
        drawRect(
            color = AccentBlue,
            topLeft = androidx.compose.ui.geometry.Offset(
                x = size.width / 2 - 3f,
                y = size.height * (1f - notePosition) - 30f
            ),
            size = androidx.compose.ui.geometry.Size(6f, 60f)
        )
    }
}
