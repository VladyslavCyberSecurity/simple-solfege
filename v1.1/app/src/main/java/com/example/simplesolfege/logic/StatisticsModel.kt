package com.example.simplesolfege.logic

data class Statistics(
    val correctNotes: Int = 0,
    val wrongNotes: Int = 0,
    val playedMelodies: Int = 0
)

fun updateStats(stats: Statistics, correct: Boolean): Statistics {
    return if (correct)
        stats.copy(correctNotes = stats.correctNotes + 1)
    else
        stats.copy(wrongNotes = stats.wrongNotes + 1)
}
