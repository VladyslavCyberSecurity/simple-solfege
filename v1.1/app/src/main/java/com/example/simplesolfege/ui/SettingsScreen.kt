package com.example.simplesolfege.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.logic.MusicScale
import com.example.simplesolfege.logic.Settings

@Composable
fun SettingsScreen(
    settings: Settings,
    onUpdate: (Settings) -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(24.dp))

        // Octave
        Text("Octave", color = MaterialTheme.colorScheme.onBackground)
        Slider(
            value = settings.octave.toFloat(),
            onValueChange = {
                onUpdate(settings.copy(octave = it.toInt()))
            },
            steps = 3,
            valueRange = 2f..5f
        )

        Spacer(Modifier.height(24.dp))


        // Scale
        Text("Scale")
        MusicScale.values().forEach { scale ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = settings.scale == scale,
                    onClick = { onUpdate(settings.copy(scale = scale)) }
                )
                Text(scale.title)
            }
        }

        Spacer(Modifier.height(24.dp))

        // Sound
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = settings.soundEnabled,
                onCheckedChange = { onUpdate(settings.copy(soundEnabled = it)) }
            )
            Text("Sound enabled")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Back")
        }
    }
}
