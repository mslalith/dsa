package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class BitwiseANDOfNumbersRange : TestCaseProblem<Pair<Int, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BitwiseANDOfNumbersRange().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 5 to 7,
            output = 4
        ),
        TestCase(
            input = 0 to 0,
            output = 0
        ),
        TestCase(
            input = 1 to 2147483647,
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return rangeBitwiseAnd(testCaseInput.first, testCaseInput.second)
    }

    private fun rangeBitwiseAnd(left: Int, right: Int): Int {
        var x = left
        var y = right
        var shifts = 0

        // shift right until both are equal
        while (x != y) {
            x = x shr 1
            y = y shr 1
            shifts++
        }

        // once equal, shifts back to get the common prefix
        return x shl shifts
    }
}
