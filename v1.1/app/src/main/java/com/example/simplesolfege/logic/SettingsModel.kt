package com.example.simplesolfege.logic

data class Settings(
    val octave: Int = 4,
    val scale: MusicScale = MusicScale.C_MAJOR,
    val soundEnabled: Boolean = true,
    val darkTheme: Boolean = true
)

enum class MusicScale(val title: String) {
    C_MAJOR("C Major"),
    G_MAJOR("G Major"),
    D_MAJOR("D Major"),
    A_MINOR("A Minor")
}
