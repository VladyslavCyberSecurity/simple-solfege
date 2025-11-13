package com.example.simplesolfege.audio

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.concurrent.thread
import kotlin.math.sin

class ToneGenerator {

    fun playTone(frequency: Float, durationMs: Int = 300) {
        thread {
            val count = (44100 * (durationMs / 1000f)).toInt()
            val audioData = ShortArray(count)

            val angularFrequency = 2.0 * Math.PI * frequency / 44100

            for (i in 0 until count) {
                audioData[i] = (sin(i * angularFrequency) * Short.MAX_VALUE).toInt().toShort()
            }

            val audioTrack = AudioTrack(
                AudioManager.STREAM_MUSIC,
                44100,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                audioData.size * 2,
                AudioTrack.MODE_STATIC
            )

            audioTrack.write(audioData, 0, audioData.size)
            audioTrack.play()
        }
    }
}
