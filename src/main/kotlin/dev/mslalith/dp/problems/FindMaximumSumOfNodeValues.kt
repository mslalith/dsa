package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.dp.problems.FindMaximumSumOfNodeValues.FindMaximumSumOfNodeValuesParams
import dev.mslalith.utils.stringFromArray
import kotlin.math.max

class FindMaximumSumOfNodeValues : TestCaseProblem<FindMaximumSumOfNodeValuesParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMaximumSumOfNodeValues().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FindMaximumSumOfNodeValuesParams, Long>> = arrayOf(
        TestCase(
            input = FindMaximumSumOfNodeValuesParams(
                nums = intArrayOf(1, 2, 1),
                k = 3,
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2)
                )
            ),
            output = 6
        ),
        TestCase(
            input = FindMaximumSumOfNodeValuesParams(
                nums = intArrayOf(2, 3),
                k = 7,
                edges = arrayOf(
                    intArrayOf(0, 1)
                )
            ),
            output = 9
        ),
        TestCase(
            input = FindMaximumSumOfNodeValuesParams(
                nums = intArrayOf(7, 7, 7, 7, 7, 7),
                k = 3,
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(0, 4),
                    intArrayOf(0, 5)
                )
            ),
            output = 42
        )
    )

    override fun solve(testCaseInput: FindMaximumSumOfNodeValuesParams): Long {
        return maximumValueSum(testCaseInput.nums, testCaseInput.k, testCaseInput.edges)
    }

    private fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        var dp = longArrayOf(0, Long.MIN_VALUE)
        for (n in nums) {
            val x = n xor k
            dp = longArrayOf(
                max(dp[0] + n, dp[1] + x),
                max(dp[1] + n, dp[0] + x)
            )
        }
        return dp[0]
    }

    data class FindMaximumSumOfNodeValuesParams(
        val nums: IntArray,
        val k: Int,
        val edges: Array<IntArray>
    ) {
        override fun toString(): String {
            return """
                
                nums: ${stringFromArray(nums)}
                k: $k
                edges: ${stringFromArray(edges)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FindMaximumSumOfNodeValuesParams

            if (k != other.k) return false
            if (!nums.contentEquals(other.nums)) return false
            if (!edges.contentDeepEquals(other.edges)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = k
            result = 31 * result + nums.contentHashCode()
            result = 31 * result + edges.contentDeepHashCode()
            return result
        }
    }
}
