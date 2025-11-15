package com.example.simplesolfege.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.logic.Note

@Composable
fun NewNoteStaff(
    note: Note?,
    highlight: Boolean?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
    ) {

        // Лінії
        Canvas(modifier = Modifier.fillMaxSize()) {

            val spacing = size.height / 6f

            repeat(5) { i ->
                val y = spacing * (i + 1)

                drawLine(
                    color = Color(0x22FFFFFF),
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = 2.5f
                )
            }
        }

        // Нота
        if (note != null)
            AnimatedNote(
                note = note,
                highlight = highlight,
                modifier = Modifier.fillMaxSize()
            )
    }
}
