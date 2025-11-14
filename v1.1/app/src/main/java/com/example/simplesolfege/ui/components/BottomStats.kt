package com.example.simplesolfege.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun BottomStats() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Accuracy")
        Text("Level: 7 (XP: 120/150)")
    }
}
