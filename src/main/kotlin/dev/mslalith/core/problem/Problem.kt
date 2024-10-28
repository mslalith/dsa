package dev.mslalith.core.problem

sealed interface Problem {

    val trackTime: Boolean get() = true

    fun run(): Boolean
    fun runForConsole()
}
