package dev.mslalith.core.problem

import kotlin.time.measureTimedValue

abstract class SimpleProblem<T> : Problem {

    abstract fun runWithResult(): Result<T>

    override fun run(): Boolean = runWithResult().isSuccess

    override fun runForConsole() {
        val (result, timeTaken) = measureTimedValue { runWithResult() }

        val exception = result.exceptionOrNull()
        val isSuccess = exception == null

        val displayResultStatus = buildString {
            append(if (isSuccess) "✅ Passed" else "❌ Failed")
            if (trackTime) {
                append(" (")
                append(timeTaken)
                append(")")
            }

            exception?.printStackTrace()
        }

        println()
        println(displayResultStatus)
    }
}