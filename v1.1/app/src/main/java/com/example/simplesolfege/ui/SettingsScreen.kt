package com.example.simplesolfege.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.logic.Settings

@Composable
fun SettingsScreen(settings: Settings, onChange: (Settings) -> Unit) {

    var scale by remember { mutableStateOf(settings.scale) }
    var sound by remember { mutableStateOf(settings.soundEnabled) }
    var adsr by remember { mutableStateOf(settings.adsrEnabled) }

    Column(modifier = Modifier.padding(24.dp)) {

        Text("Settings", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(20.dp))

        Text("Scale")
        Spacer(Modifier.height(6.dp))
        DropdownMenu(
            expanded = false,
            onDismissRequest = {}
        ) {}

        Spacer(Modifier.height(20.dp))

        Row {
            Checkbox(checked = sound, onCheckedChange = { sound = it })
            Text("Enable sound")
        }

        Row {
            Checkbox(checked = adsr, onCheckedChange = { adsr = it })
            Text("Soft ADSR")
        }
    }

    onChange(Settings(scale, sound, adsr))
}
