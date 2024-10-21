package dev.mslalith.core.problem

sealed interface Problem {
    fun run(): Boolean
}
