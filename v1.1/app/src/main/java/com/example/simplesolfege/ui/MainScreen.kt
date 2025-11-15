package com.example.simplesolfege.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import com.example.simplesolfege.audio.MelodyGenerator
import com.example.simplesolfege.audio.ToneGenerator
import com.example.simplesolfege.logic.*
import com.example.simplesolfege.ui.components.*
import com.example.simplesolfege.ui.theme.CardDark

@Composable
fun MainScreen() {

    val scope = rememberCoroutineScope()
    val tone = ToneGenerator()

    // Таби зверху
    var selectedTab by remember { mutableStateOf(0) }

    // Поточна мелодія (4 ноти)
    var melody by remember { mutableStateOf<List<Note>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }

    // Перевірка
    var expectedNote by remember { mutableStateOf<Note?>(null) }
    var answerCorrect by remember { mutableStateOf<Boolean?>(null) }

    // Система рівнів
    var levelState by remember { mutableStateOf(LevelState()) }

    // Екран завершення
    var lessonComplete by remember { mutableStateOf(false) }

    if (lessonComplete) {
        LessonCompleteScreen(
            level = levelState.level,
            xp = levelState.xp,
            onNext = {
                melody = emptyList()
                currentIndex = 0
                expectedNote = null
                answerCorrect = null
                lessonComplete = false
            }
        )
        return
    }

    // -------- UI ---------

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Tabs
        TopTabs(
            tabs = listOf("Melody", "Intervals", "Rhythm"),
            selectedIndex = selectedTab,
            onSelect = { selectedTab = it }
        )

        Spacer(Modifier.height(12.dp))

        // STAFF
        NewNoteStaff(
            note = expectedNote,
            highlight = answerCorrect
        )


        Spacer(Modifier.height(12.dp))

        // KEYBOARD
        PianoKeys(
            expected = expectedNote,
            wasCorrect = answerCorrect,
            onNoteClick = { note ->

                // Програти звук
                tone.play(note.frequency)

                val expected = expectedNote ?: return@PianoKeys

                scope.launch {
                    if (note == expected) {
                        answerCorrect = true
                        delay(300)

                        // XP
                        levelState = addXp(levelState, amount = 10)

                        currentIndex++

                        // Завершення мелодії
                        if (currentIndex >= melody.size) {
                            lessonComplete = true
                        } else {
                            expectedNote = melody[currentIndex]
                        }

                        answerCorrect = null
                    } else {
                        answerCorrect = false
                        delay(350)
                        answerCorrect = null
                    }
                }
            }
        )

        Spacer(Modifier.height(24.dp))

        // BUTTONS
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = {
                    melody = MelodyGenerator().generate(
                        scale = C_MAJOR,
                        length = 4
                    )
                    currentIndex = 0
                    expectedNote = melody.firstOrNull()
                    answerCorrect = null
                },
                shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF4A8BFF)),
                modifier = Modifier.width(140.dp)
            ) {
                Text("Generate")
            }

            OutlinedButton(
                onClick = {
                    if (expectedNote != null) tone.play(expectedNote!!.frequency)
                },
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier.width(140.dp)
            ) {
                Text("Replay")
            }
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "Level: ${levelState.level}   XP: ${levelState.xp}/${levelState.nextXp}",
            color = Color.White.copy(0.8f)
        )
    }
}
