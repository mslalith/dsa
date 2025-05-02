package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class PushDominoes : TestCaseProblem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PushDominoes().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "RR.L",
            output = "RR.L"
        ),
        TestCase(
            input = ".L.R...LR..L..",
            output = "LL.RR.LLRRLL.."
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return pushDominoes(testCaseInput)
    }

    private fun pushDominoes(dominoes: String): String {
        val n = dominoes.length
        val dominoArray = dominoes.toCharArray()
        val forceArray = IntArray(n)

        var force = 0
        for (i in 0 until n) {
            force = when (dominoArray[i]) {
                'R' -> n
                'L' -> 0
                else -> max(force - 1, 0)
            }
            forceArray[i] += force
        }

        force = 0
        for (i in (n - 1) downTo 0) {
            force = when (dominoArray[i]) {
                'L' -> n
                'R' -> 0
                else -> max(force - 1, 0)
            }
            forceArray[i] -= force
        }

        val sb = StringBuilder()

        for (force in forceArray) {
            val char = when {
                force > 0 -> 'R'
                force < 0 -> 'L'
                else -> '.'
            }
            sb.append(char)
        }

        return sb.toString()
    }
}
