package com.example.simplesolfege.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.audio.ToneGenerator
import com.example.simplesolfege.logic.*
import com.example.simplesolfege.ui.components.*

@Composable
fun MainScreen() {

    val tone = ToneGenerator()

    var selectedTab by remember { mutableStateOf(0) }

    var notePos by remember { mutableStateOf(0.5f) }

    var levelState by remember { mutableStateOf(LevelState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBar()

        Spacer(modifier = Modifier.height(20.dp))

        TopTabs(
            tabs = listOf("Melody", "Intervals", "Rhythm"),
            selectedIndex = selectedTab,
            onSelect = { selectedTab = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        NoteStaff(notePosition = notePos)

        Spacer(modifier = Modifier.height(20.dp))

        PianoKeys { note ->
            tone.play(note.frequency)
            notePos = noteToPosition(note)
            levelState = addXp(levelState, 5)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {}) { Text("Generate") }
            Button(onClick = {}) { Text("Replay") }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Accuracy")
        Text("Level: ${levelState.level} (XP: ${levelState.xp}/${levelState.nextXp})")
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
