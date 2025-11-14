package com.example.simplesolfege.logic

import kotlin.random.Random

object MelodyGenerator {
    fun generate(count: Int): List<Note> {
        return List(count) {
            Note.values()[Random.nextInt(Note.values().size)]
        }
    }
}
