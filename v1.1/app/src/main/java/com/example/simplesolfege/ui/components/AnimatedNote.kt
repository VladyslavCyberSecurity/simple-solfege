package com.example.simplesolfege.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.simplesolfege.logic.Note
import com.example.simplesolfege.logic.noteY

@Composable
fun AnimatedNote(
    note: Note,
    highlight: Boolean?,
    modifier: Modifier = Modifier
) {
    val color = when (highlight) {
        true -> Color(0xFF2ECC71)
        false -> Color(0xFFE74C3C)
        else -> Color(0xFF3D7DFF)
    }

    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(400)) +
                slideInVertically(animationSpec = tween(400)) { it / 2 },
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Canvas(modifier = modifier.fillMaxSize()) {

            val y = size.height * (1f - noteY(note))

            // Кружечок
            drawCircle(
                color = color,
                radius = 14f,
                center = Offset(size.width / 2, y)
            )
        }
    }
}
