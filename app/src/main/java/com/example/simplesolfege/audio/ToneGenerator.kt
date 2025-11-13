package com.example.simplesolfege.audio

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.math.sin

object ToneGenerator {

    private const val sampleRate = 44100

    fun playTone(frequency: Double, durationMs: Int = 300) {
        val count = (sampleRate * (durationMs / 1000.0)).toInt()
        val sound = ShortArray(count)

        for (i in 0 until count) {
            val angle = 2.0 * Math.PI * i * frequency / sampleRate
            sound[i] = (sin(angle) * Short.MAX_VALUE).toInt().toShort()
        }

        val audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            sound.size * 2,
            AudioTrack.MODE_STATIC
        )

        audioTrack.write(sound, 0, sound.size)
        audioTrack.play()
    }
}
