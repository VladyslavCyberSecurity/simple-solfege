package com.example.simplesolfege.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.logic.StatsRepository
import com.example.simplesolfege.logic.StatsState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun StatsScreen(repo: StatsRepository) {

    // ПРАВИЛЬНИЙ ВИКЛИК !!!
    val stats by repo.stats.collectAsState(initial = StatsState())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Your Progress",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )

        Spacer(Modifier.height(20.dp))

        // Accuracy
        val percent =
            if (stats.total == 0) 0.0 else (stats.correct.toDouble() / stats.total.toDouble()) * 100.0

        Text(
            text = "Accuracy: ${percent.toInt()}%",
            color = Color.White
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Total notes: ${stats.total}",
            color = Color.White
        )

        Text(
            text = "Correct notes: ${stats.correct}",
            color = Color.White
        )

        Spacer(Modifier.height(20.dp))

        // Streaks
        Text(
            text = "Current streak: ${stats.currentStreak}",
            color = Color.White
        )

        Text(
            text = "Best streak: ${stats.bestStreak}",
            color = Color.White
        )

        Spacer(Modifier.height(40.dp))

        // Mini bar chart (example)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF222222), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val barWidth = size.width * (percent.toFloat() / 100f)
                drawRect(
                    color = Color(0xFF4FC3F7),
                    size = androidx.compose.ui.geometry.Size(barWidth, size.height)
                )
            }
        }
    }
}
