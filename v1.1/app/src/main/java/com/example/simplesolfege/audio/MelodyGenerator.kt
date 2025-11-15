package com.example.simplesolfege.audio

import com.example.simplesolfege.logic.Note
import kotlin.random.Random

class MelodyGenerator {

    fun generate(scale: List<Note>, length: Int = 4): List<Note> {
        return List(length) {
            scale[Random.nextInt(scale.size)]
        }
    }
}
