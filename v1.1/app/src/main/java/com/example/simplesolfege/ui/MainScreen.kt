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
import com.example.simplesolfege.audio.ToneGenerator
import com.example.simplesolfege.logic.*
import com.example.simplesolfege.ui.components.*
import com.example.simplesolfege.ui.theme.CardNavy

@Composable
fun MainScreen() {
    val tone = ToneGenerator()

    var selectedTab by remember { mutableStateOf(0) }
    var notePos by remember { mutableStateOf(0.5f) }
    var levelState by remember { mutableStateOf(LevelState()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(CardNavy)
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TopBar()

                Spacer(modifier = Modifier.height(20.dp))

                TopTabs(
                    tabs = listOf("Melody", "Intervals", "Rhythm"),
                    selectedIndex = selectedTab,
                    onSelect = { selectedTab = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                NoteStaff(notePosition = notePos)

                Spacer(modifier = Modifier.height(24.dp))

                PianoKeys { note ->
                    tone.play(note.frequency)
                    notePos = noteToPosition(note)
                    levelState = addXp(levelState, 5)
                }

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.width(140.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Generate")
                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.width(140.dp),
                        shape = RoundedCornerShape(24.dp),
                    ) {
                        Text("Replay")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // точки як індикатор прогресу
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(6) { index ->
                        val alpha =
                            if (index == 2) 1f else 0.3f
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .padding(horizontal = 4.dp)
                                .clip(RoundedCornerShape(50))
                                .background(
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = alpha)
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Accuracy",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
                Text(
                    text = "Level: ${levelState.level} (XP: ${levelState.xp}/${levelState.nextXp})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}

fun noteToPosition(note: Note): Float {
    return when (note) {
        Note.C -> 0.15f
        Note.D -> 0.25f
        Note.E -> 0.35f
        Note.F -> 0.45f
        Note.G -> 0.55f
        Note.A -> 0.70f
        Note.B -> 0.85f
        else -> 0.5f
    }
}
