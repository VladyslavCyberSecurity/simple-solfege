package com.example.simplesolfege.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.example.simplesolfege.logic.Note
import com.example.simplesolfege.ui.theme.KeyWhite
import com.example.simplesolfege.ui.theme.CardDark

@Composable
fun PianoKeys(onNoteClick: (Note) -> Unit) {

    val whiteKeys = listOf(
        Note.C, Note.D, Note.E, Note.F, Note.G, Note.A, Note.B
    )

    val blackKeys = listOf(
        Note.C_SHARP, Note.D_SHARP,
        null,
        Note.F_SHARP, Note.G_SHARP, Note.A_SHARP
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp),
        shape = RoundedCornerShape(32.dp),
        color = CardDark,
        shadowElevation = 14.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 22.dp)
        ) {

            // БІЛІ КЛАВІШІ
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                whiteKeys.forEach { note ->
                    PianoWhiteKey(note) { onNoteClick(it) }
                }
            }

            // ЧОРНІ КЛАВІШІ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, top = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                blackKeys.forEach { note ->
                    if (note == null) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else {
                        PianoBlackKey(note) { onNoteClick(it) }
                    }
                }
            }
        }
    }
}

@Composable
fun PianoWhiteKey(
    note: Note,
    onPress: (Note) -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val h by animateDpAsState(if (pressed) 142.dp else 150.dp)
    val shadow by animateDpAsState(if (pressed) 2.dp else 6.dp)

    Box(
        modifier = Modifier
            .width(48.dp)
            .height(h)
            .shadow(shadow, RoundedCornerShape(12.dp))
            .background(KeyWhite, RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                pressed = true
                onPress(note)
                pressed = false
            }
    )
}

@Composable
fun PianoBlackKey(
    note: Note,
    onPress: (Note) -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val h by animateDpAsState(if (pressed) 94.dp else 104.dp)
    val shadow by animateDpAsState(if (pressed) 6.dp else 14.dp)

    Box(
        modifier = Modifier
            .width(32.dp)
            .height(h)
            .shadow(shadow, RoundedCornerShape(8.dp))
            .background(Color.Black, RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                pressed = true
                onPress(note)
                pressed = false
            }
    )
}
