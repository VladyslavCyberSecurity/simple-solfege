package com.example.simplesolfege.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplesolfege.audio.ToneGenerator
import com.example.simplesolfege.logic.Note

@Composable
fun PianoKeys(tone: ToneGenerator) {
    var activeNote by remember { mutableStateOf<Note?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Note.values().forEach { note ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .background(
                        if (activeNote == note) Color(0xFFBBDEFB) else Color.White
                    )
                    .clickable {
                        activeNote = note
                        tone.playTone(note.freq)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = note.name,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
