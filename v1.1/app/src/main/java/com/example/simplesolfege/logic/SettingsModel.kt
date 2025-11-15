package com.example.simplesolfege.logic

data class Settings(
    val scale: String = "C major",
    val soundEnabled: Boolean = true,
    val adsrEnabled: Boolean = true
)
