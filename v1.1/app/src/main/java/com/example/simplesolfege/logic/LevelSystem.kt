package com.example.simplesolfege.logic

data class LevelState(
    val level: Int = 1,
    val xp: Int = 0,
    val nextXp: Int = 50
)

fun addXp(state: LevelState, amount: Int): LevelState {
    val newXp = state.xp + amount
    return if (newXp >= state.nextXp) {
        LevelState(
            level = state.level + 1,
            xp = newXp - state.nextXp,
            nextXp = (state.nextXp * 1.2f).toInt()  // кожен рівень потребує більше XP
        )
    } else {
        state.copy(xp = newXp)
    }
}
