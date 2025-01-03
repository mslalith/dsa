package dev.mslalith.bits.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class CountingBits : TestCaseProblem<Int, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountingBits().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, IntArray>> = arrayOf(
        TestCase(
            input = 2,
            output = intArrayOf(0, 1, 1)
        ),
        TestCase(
            input = 5,
            output = intArrayOf(0, 1, 1, 2, 1, 2)
        )
    )

    override fun solve(testCaseInput: Int): IntArray {
        return countBits(testCaseInput)
    }

    private fun countBits(n: Int): IntArray {
        return IntArray(n + 1) { countBitsFor(it) }
    }

    private fun countBitsFor(n: Int): Int {
        var i = n
        var count = 0
        while (i != 0) {
            if (i and 1 == 1) count++
            i = i shr 1
        }
        return count
    }
}
