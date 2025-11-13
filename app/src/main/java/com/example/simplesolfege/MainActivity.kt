package com.example.simplesolfege

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.audio.ToneGenerator
import com.example.simplesolfege.logic.Note

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Нота ДО
        Button(onClick = { ToneGenerator.playTone(Note.C.freq) }) {
            Text("Generate (C)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Нота РЕ
        Button(onClick = { ToneGenerator.playTone(Note.D.freq) }) {
            Text("Play (D)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Нота МІ
        Button(onClick = { ToneGenerator.playTone(Note.E.freq) }) {
            Text("Replay (E)")
        }
    }
}
