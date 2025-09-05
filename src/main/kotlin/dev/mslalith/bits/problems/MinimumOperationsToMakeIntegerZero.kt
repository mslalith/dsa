package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.lang.Long

class MinimumOperationsToMakeIntegerZero : TestCaseProblem<Pair<Int, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumOperationsToMakeIntegerZero().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 3 to -2,
            output = 3
        ),
        TestCase(
            input = 5 to 7,
            output = -1
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return makeTheIntegerZero(testCaseInput.first, testCaseInput.second)
    }

    private fun makeTheIntegerZero(num1: Int, num2: Int): Int {
        for (k in 1..60) {
            val x = (1L * num1) - (1L * num2 * k)
            if (x < k) return -1
            if (k >= Long.bitCount(x)) return k
        }

        return -1
    }
}
