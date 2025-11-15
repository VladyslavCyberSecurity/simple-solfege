package com.example.simplesolfege.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.concurrent.thread
import kotlin.math.sin

class ToneGenerator {

    fun play(frequency: Int, durationMs: Int = 300) {

        thread {
            val sampleRate = 44100
            val sampleCount = (sampleRate * (durationMs / 1000f)).toInt()
            val samples = ShortArray(sampleCount)

            // Простий синус — стабільно працює
            for (i in 0 until sampleCount) {
                val t = i.toDouble() / sampleRate
                samples[i] = (sin(2 * Math.PI * frequency * t) * Short.MAX_VALUE).toInt().toShort()
            }

            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setSampleRate(sampleRate)
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(sampleCount * 2)
                .build()

            track.write(samples, 0, samples.size)
            track.play()
        }
    }
}
