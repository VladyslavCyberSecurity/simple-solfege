package com.example.simplesolfege.logic

data class LevelState(
    val level: Int = 1,
    val xp: Int = 0,
    val nextXp: Int = 50
)

fun addXp(state: LevelState, gained: Int): LevelState {
    var xp = state.xp + gained
    var level = state.level
    var next = state.nextXp

    while (xp >= next) {
        xp -= next
        level++

        next = when (level) {
            1 -> 50
            2 -> 75
            3 -> 100
            4 -> 130
            5 -> 170
            else -> 200 + (level - 5) * 40
        }
    }

    return LevelState(level, xp, next)
}
