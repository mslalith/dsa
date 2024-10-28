package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

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

        for (i in digits.lastIndex downTo 0) {
            if (digits[i] < 9) {
                digits[i] += 1
                return digits
            }
            digits[i] = 0
        }

        return IntArray(digits.size + 1) { if (it == 0) 1 else 0 }
    }
}
