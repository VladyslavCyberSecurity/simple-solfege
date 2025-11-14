package com.example.simplesolfege.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplesolfege.logic.Note

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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            whiteKeys.forEach { note ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .width(56.dp)
                            .height(200.dp)
                            .shadow(4.dp, RoundedCornerShape(6.dp))
                            .background(Color.White, RoundedCornerShape(6.dp))
                            .clickable { onNoteClick(note) }
                    )
                    Text(
                        text = note.label,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            blackKeys.forEach { note ->
                if (note == null) {
                    Spacer(modifier = Modifier.width(56.dp))
                } else {
                    Box(
                        modifier = Modifier
                            .width(34.dp)
                            .height(130.dp)
                            .shadow(10.dp, RoundedCornerShape(4.dp))
                            .background(Color.Black, RoundedCornerShape(4.dp))
                            .clickable { onNoteClick(note) }
                    )
                }
            }
        }
    }
}
