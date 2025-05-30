package dev.mslalith.core.problem

sealed interface Problem {

    val name: String
        get() = javaClass.simpleName

    val trackTime: Boolean get() = true

    fun run(): Boolean
    fun runForConsole()
}
