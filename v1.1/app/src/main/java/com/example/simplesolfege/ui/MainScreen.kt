package com.example.simplesolfege.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.audio.MelodyGenerator
import com.example.simplesolfege.audio.ToneGenerator
import com.example.simplesolfege.logic.*
import com.example.simplesolfege.ui.components.*
import com.example.simplesolfege.ui.theme.CardDark

@Composable
fun MainScreen() {

    val tone = ToneGenerator()

    var selectedTab by remember { mutableStateOf(0) }

    var notePos by remember { mutableStateOf(0.50f) }

    var levelState by remember { mutableStateOf(LevelState()) }

    var currentMelody by remember { mutableStateOf(emptyList<Note>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
                .background(CardDark)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ВЕРХ
            TopBar()

            Spacer(modifier = Modifier.height(20.dp))

            // ТАБИ
            TopTabs(
                tabs = listOf("Melody", "Intervals", "Rhythm"),
                selectedIndex = selectedTab,
                onSelect = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // НОТНИЙ СТАН
            NoteStaff(notePosition = notePos)

            Spacer(modifier = Modifier.height(28.dp))

            // ПІАНІНО
            PianoKeys { note ->
                tone.play(note.frequency)
                notePos = noteToPosition(note)
                levelState = addXp(levelState, 5)
            }

            Spacer(modifier = Modifier.height(36.dp))

            // КНОПКИ
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // Generate
                Button(
                    onClick = {
                        val melody = MelodyGenerator().generate(C_MAJOR, 4)
                        currentMelody = melody

                        if (melody.isNotEmpty()) {
                            notePos = noteToPosition(melody.first())
                        }
                    },
                    modifier = Modifier.width(140.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Generate")
                }

                // Replay
                OutlinedButton(
                    onClick = {
                        currentMelody.forEach { note ->
                            tone.play(note.frequency)
                        }
                    },
                    modifier = Modifier.width(140.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Replay")
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ІНДИКАТОР
            BottomIndicator(active = 2)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Accuracy",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )

            Text(
                "Level: ${levelState.level} • XP: ${levelState.xp}/${levelState.nextXp}",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

// Переводимо ноту у вертикальну позицію
fun noteToPosition(note: Note): Float {
    return when (note) {
        Note.C -> 0.18f
        Note.D -> 0.28f
        Note.E -> 0.36f
        Note.F -> 0.45f
        Note.G -> 0.56f
        Note.A -> 0.70f
        Note.B -> 0.84f
        else -> 0.50f
    }
}
