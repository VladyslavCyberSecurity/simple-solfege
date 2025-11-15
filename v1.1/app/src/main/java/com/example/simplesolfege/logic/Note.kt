package com.example.simplesolfege.logic

enum class Note(
    val label: String,
    val frequency: Int,
    val isWhite: Boolean
) {
    C("C", 261, true),
    C_SHARP("C#", 277, false),
    D("D", 293, true),
    D_SHARP("D#", 311, false),
    E("E", 329, true),
    F("F", 349, true),
    F_SHARP("F#", 370, false),
    G("G", 392, true),
    G_SHARP("G#", 415, false),
    A("A", 440, true),
    A_SHARP("A#", 466, false),
    B("B", 494, true)
}
    