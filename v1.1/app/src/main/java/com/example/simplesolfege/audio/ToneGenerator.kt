package com.example.simplesolfege.audio

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.concurrent.thread
import kotlin.math.sin

class ToneGenerator(
    private val adsr: ADSR = ADSR()
) {
    fun play(freq: Int, durationMs: Int = 350) {
        thread {
            val rate = 44100
            val samples = IntArray(rate * durationMs / 1000)

            val attackEnd = (samples.size * adsr.attack).toInt()
            val decayEnd = attackEnd + (samples.size * adsr.decay).toInt()
            val releaseStart = (samples.size * 0.8).toInt()

            for (i in samples.indices) {
                val base = sin(2 * Math.PI * freq * i / rate)

                val amp = when {
                    i < attackEnd -> i.toFloat() / attackEnd
                    i < decayEnd -> 1f - (1f - adsr.sustain) * ((i - attackEnd).toFloat() / (decayEnd - attackEnd))
                    i > releaseStart -> adsr.sustain * (1f - (i - releaseStart).toFloat() / (samples.size - releaseStart))
                    else -> adsr.sustain
                }

                samples[i] = (base * amp * Short.MAX_VALUE).toInt()
            }

            val audio = ShortArray(samples.size)
            for (i in audio.indices) audio[i] = samples[i].toShort()

            val track = AudioTrack(
                AudioManager.STREAM_MUSIC, rate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                audio.size * 2,
                AudioTrack.MODE_STATIC
            )

            track.write(audio, 0, audio.size)
            track.play()
        }
    }
}
