package com.example.simplesolfege.logic

fun noteY(note: Note): Float {
    return when (note) {
        Note.C -> 0.15f
        Note.D -> 0.25f
        Note.E -> 0.35f
        Note.F -> 0.45f
        Note.G -> 0.55f
        Note.A -> 0.70f
        Note.B -> 0.85f
        else -> 0.50f
    }
}
