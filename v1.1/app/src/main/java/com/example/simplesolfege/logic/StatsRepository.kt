package com.example.simplesolfege.logic

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("stats")

object StatsKeys {
    val TOTAL_NOTES = intPreferencesKey("total_notes")
    val CORRECT_NOTES = intPreferencesKey("correct_notes")
    val BEST_STREAK = intPreferencesKey("best_streak")
    val CURRENT_STREAK = intPreferencesKey("current_streak")
}

data class StatsState(
    val total: Int = 0,
    val correct: Int = 0,
    val bestStreak: Int = 0,
    val currentStreak: Int = 0
)

class StatsRepository(private val context: Context) {

    val stats: Flow<StatsState> = context.dataStore.data.map { prefs ->
        StatsState(
            total = prefs[StatsKeys.TOTAL_NOTES] ?: 0,
            correct = prefs[StatsKeys.CORRECT_NOTES] ?: 0,
            bestStreak = prefs[StatsKeys.BEST_STREAK] ?: 0,
            currentStreak = prefs[StatsKeys.CURRENT_STREAK] ?: 0
        )
    }

    suspend fun addCorrect() {
        context.dataStore.edit { prefs ->
            val newTotal = (prefs[StatsKeys.TOTAL_NOTES] ?: 0) + 1
            val newCorrect = (prefs[StatsKeys.CORRECT_NOTES] ?: 0) + 1
            val newCurrent = (prefs[StatsKeys.CURRENT_STREAK] ?: 0) + 1
            val oldBest = prefs[StatsKeys.BEST_STREAK] ?: 0

            prefs[StatsKeys.TOTAL_NOTES] = newTotal
            prefs[StatsKeys.CORRECT_NOTES] = newCorrect
            prefs[StatsKeys.CURRENT_STREAK] = newCurrent

            if (newCurrent > oldBest)
                prefs[StatsKeys.BEST_STREAK] = newCurrent
        }
    }

    suspend fun addWrong() {
        context.dataStore.edit { prefs ->
            val newTotal = (prefs[StatsKeys.TOTAL_NOTES] ?: 0) + 1
            prefs[StatsKeys.TOTAL_NOTES] = newTotal
            prefs[StatsKeys.CURRENT_STREAK] = 0
        }
    }

    suspend fun reset() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
