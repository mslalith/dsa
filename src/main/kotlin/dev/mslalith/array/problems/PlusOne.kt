package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class PlusOne : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PlusOne().runAll()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = intArrayOf(1, 2, 4)
        ),
        TestCase(
            input = intArrayOf(4, 3, 2, 1),
            output = intArrayOf(4, 3, 2, 2)
        ),
        TestCase(
            input = intArrayOf(9),
            output = intArrayOf(1, 0)
        ),
        TestCase(
            input = intArrayOf(9, 9),
            output = intArrayOf(1, 0, 0)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        return plusOne(testCaseInput)
    }

    private fun plusOne(digits: IntArray): IntArray {
        return plusOne(digits, digits.lastIndex)
    }

    private fun plusOne(digits: IntArray, index: Int): IntArray {
        if (index == -1) {
            digits[0] = 0
            return intArrayOf(1, *digits)
        }
        if (digits[index] < 9) {
            digits[index] = digits[index] + 1
            return digits
        }

        digits[index] = 0
        return plusOne(digits, index - 1)
    }
}