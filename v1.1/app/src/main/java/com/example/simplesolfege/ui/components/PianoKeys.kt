package com.example.simplesolfege.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.logic.Note
import com.example.simplesolfege.ui.theme.KeyWhite
import com.example.simplesolfege.ui.theme.CardDark

@Composable
fun PianoKeys(
    expected: Note?,
    wasCorrect: Boolean?,
    onNoteClick: (Note) -> Unit
) {
    val whiteKeys = listOf(
        Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.B
    )
    val blackKeys = listOf(
        Note.C_SHARP, Note.D_SHARP, null, Note.F_SHARP, Note.G_SHARP, Note.A_SHARP, null
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CardDark)
            .padding(16.dp)
    ) {

        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            // WHITE KEYS
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                whiteKeys.forEach { note -> WhiteKey(note, expected, wasCorrect, onNoteClick) }
            }

            // BLACK KEYS (POSITIONED ABOVE)
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ) {
                blackKeys.forEach { note ->
                    if (note != null) BlackKey(note, expected, wasCorrect, onNoteClick)
                    else Spacer(modifier = Modifier.width(30.dp))
                }
            }
        }
    }
}

@Composable
private fun WhiteKey(
    note: Note,
    expected: Note?,
    wasCorrect: Boolean?,
    onClick: (Note) -> Unit
) {
    val baseColor = KeyWhite

    val highlightColor =
        when {
            wasCorrect == true && note == expected -> Color(0xFF2ECC71)
            wasCorrect == false && note == expected -> Color(0xFFE74C3C)
            else -> baseColor
        }

    val animatedColor by animateColorAsState(highlightColor)
    val pressAnim by animateDpAsState(if (note == expected && wasCorrect != null) 4.dp else 0.dp)

    Box(
        modifier = Modifier
            .width(45.dp)
            .fillMaxHeight()
            .padding(horizontal = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(animatedColor)
            .offset(y = pressAnim)
            .clickable { onClick(note) }
    )
}

@Composable
private fun BlackKey(
    note: Note,
    expected: Note?,
    wasCorrect: Boolean?,
    onClick: (Note) -> Unit
) {
    val highlightColor =
        when {
            wasCorrect == true && note == expected -> Color(0xFF2ECC71)
            wasCorrect == false && note == expected -> Color(0xFFE74C3C)
            else -> Color.Black
        }

    val animatedColor by animateColorAsState(highlightColor)
    val pressAnim by animateDpAsState(if (note == expected && wasCorrect != null) 3.dp else 0.dp)

    Box(
        modifier = Modifier
            .width(32.dp)
            .height(110.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(animatedColor)
            .offset(y = pressAnim)
            .clickable { onClick(note) }
    )
}
