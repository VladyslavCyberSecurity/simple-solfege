package com.example.simplesolfege.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplesolfege.logic.Note
import com.example.simplesolfege.ui.theme.KeyWhite
import com.example.simplesolfege.ui.theme.SurfaceNavy


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
            .height(190.dp),
        shape = RoundedCornerShape(24.dp),
        color = SurfaceNavy,
        shadowElevation = 12.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {

            // БІЛІ КЛАВІШІ
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                whiteKeys.forEach { note ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .width(46.dp)
                                .height(150.dp)
                                .shadow(4.dp, RoundedCornerShape(10.dp))
                                .background(KeyWhite, RoundedCornerShape(10.dp))
                                .clickable { onNoteClick(note) }
                        )
                        Text(
                            text = note.label,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                    }
                }
            }

            // ЧОРНІ КЛАВІШІ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                blackKeys.forEach { note ->
                    if (note == null) {
                        Spacer(modifier = Modifier.width(46.dp))
                    } else {
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(100.dp)
                                .shadow(10.dp, RoundedCornerShape(6.dp))
                                .background(Color.Black, RoundedCornerShape(6.dp))
                                .clickable { onNoteClick(note) }
                        )
                    }
                }
            }
        }
    }
}
