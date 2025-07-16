package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindMaximumLengthOfValidSubsequenceI : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMaximumLengthOfValidSubsequenceI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4),
            output = 4
        ),
        TestCase(
            input = intArrayOf(1, 2, 1, 1, 2, 1, 2),
            output = 6
        ),
        TestCase(
            input = intArrayOf(1, 3),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maximumLength(testCaseInput)
    }

    private fun maximumLength(nums: IntArray): Int {
        var countEven = 0
        var countOdd = 0
        var evenDp = 0
        var oddDp = 0

        for (num in nums) {
            if (num % 2 == 0) {
                countEven++
                evenDp = maxOf(evenDp, oddDp + 1)
            } else {
                countOdd++
                oddDp = maxOf(oddDp, evenDp + 1)
            }
        }

        return maxOf(maxOf(countEven, countOdd), maxOf(evenDp, oddDp))
    }
}
