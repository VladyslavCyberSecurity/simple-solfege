package com.example.simplesolfege.audio

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.concurrent.thread

class ToneGenerator {

    fun play(freq: Int, durationMs: Int = 300) {
        thread {
            val rate = 44100
            val count = rate * durationMs / 1000
            val samples = ShortArray(count)

            for (i in samples.indices) {
                val angle = 2.0 * Math.PI * i / (rate / freq)
                samples[i] = (Math.sin(angle) * 32767).toInt().toShort()
            }

            val track = AudioTrack(
                AudioManager.STREAM_MUSIC, rate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                samples.size * 2,
                AudioTrack.MODE_STATIC
            )

            track.write(samples, 0, samples.size)
            track.play()
        }
    }
}
