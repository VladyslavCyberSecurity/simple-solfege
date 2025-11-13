package com.example.simplesolfege.audio

import android.content.Context
import android.media.SoundPool
import com.example.simplesolfege.R

class AudioEngine(context: Context) {

    private val soundPool = SoundPool.Builder()
        .setMaxStreams(5)
        .build()

    private val sounds = mapOf(
        "c" to soundPool.load(context, R.raw.c, 1),
        "d" to soundPool.load(context, R.raw.d, 1),
        "e" to soundPool.load(context, R.raw.e, 1),
        "f" to soundPool.load(context, R.raw.f, 1),
        "g" to soundPool.load(context, R.raw.g, 1),
        "a" to soundPool.load(context, R.raw.a, 1),
        "b" to soundPool.load(context, R.raw.b, 1)
    )

    fun play(note: String) {
        sounds[note]?.let { soundId ->
            soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
        }
    }
}
