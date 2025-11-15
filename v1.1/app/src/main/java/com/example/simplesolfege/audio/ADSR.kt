package com.example.simplesolfege.audio

data class ADSR(
    val attack: Float = 0.03f,
    val decay: Float = 0.05f,
    val sustain: Float = 0.6f,
    val release: Float = 0.1f
)
