package com.example.simplesolfege.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Offset
import com.example.simplesolfege.logic.Note
import com.example.simplesolfege.logic.noteY

@Composable
fun MultiNoteStaff(
    notes: List<Note>,
    highlight: Boolean?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {

            val spacing = size.height / 6f

            // 5 staff lines
            repeat(5) { i ->
                val y = spacing * (i + 1)

                this.drawLine(                      // <— НІЯКИХ ІМПОРТІВ, ПРЯМА ФУНКЦІЯ
                    color = Color(0x22FFFFFF),
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = 3f
                )
            }

            // Notes
            notes.forEach { note ->
                val y = size.height * (1f - noteY(note))

                val color = when (highlight) {
                    true -> Color(0xFF2ECC71)
                    false -> Color(0xFFE74C3C)
                    else -> Color(0xFF3D7DFF)
                }

                this.drawLine(                      // <— ТЕ Ж САМЕ, БЕЗ ІМПОРТІВ
                    color = color,
                    start = Offset(size.width / 2f, y - 20),
                    end = Offset(size.width / 2f, y + 20),
                    strokeWidth = 6f
                )
            }
        }
    }
}
