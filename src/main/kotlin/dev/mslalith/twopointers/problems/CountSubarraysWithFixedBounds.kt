package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.twopointers.problems.CountSubarraysWithFixedBounds.CountSubarraysWithFixedBoundsParams
import dev.mslalith.utils.stringFromArray
import kotlin.math.max
import kotlin.math.min

class CountSubarraysWithFixedBounds : TestCaseProblem<CountSubarraysWithFixedBoundsParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountSubarraysWithFixedBounds().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CountSubarraysWithFixedBoundsParams, Long>> = arrayOf(
        TestCase(
            input = CountSubarraysWithFixedBoundsParams(
                nums = intArrayOf(1, 3, 5, 2, 7, 5),
                minK = 1,
                maxK = 5
            ),
            output = 2
        ),
        TestCase(
            input = CountSubarraysWithFixedBoundsParams(
                nums = intArrayOf(1, 1, 1, 1),
                minK = 1,
                maxK = 1
            ),
            output = 10
        )
    )

    override fun solve(testCaseInput: CountSubarraysWithFixedBoundsParams): Long {
        return countSubarrays(testCaseInput.nums, testCaseInput.minK, testCaseInput.maxK)
    }

    private fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        var ans = 0L

        var maxi = -1
        var mini = -1

        var left = 0
        for (right in nums.indices){
            val num = nums[right]

            if (num < minK || num > maxK) {
                left = right + 1
                continue
            }

            if (num == maxK) maxi = right
            if (num == minK) mini = right

            val length = min(maxi, mini) - left + 1
            ans += max(length, 0);
        }

        return ans
    }

    private fun countSubarraysNaive(nums: IntArray, minK: Int, maxK: Int): Long {
        val n = nums.size
        var count = 0L

        for (i in 0 until n) {
            var mini = Int.MAX_VALUE
            var maxi = Int.MIN_VALUE
            for (j in i until n) {
                mini = min(mini, nums[j])
                maxi = max(maxi, nums[j])
                if (mini == minK && maxi == maxK) count++
            }
        }

        return count
    }

    data class CountSubarraysWithFixedBoundsParams(
        val nums: IntArray,
        val minK: Int,
        val maxK: Int
    ) {
        override fun toString(): String {
            return """
                nums: ${stringFromArray(nums)}
                minK: $minK
                maxK: $maxK
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CountSubarraysWithFixedBoundsParams

            if (minK != other.minK) return false
            if (maxK != other.maxK) return false
            if (!nums.contentEquals(other.nums)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = minK
            result = 31 * result + maxK
            result = 31 * result + nums.contentHashCode()
            return result
        }
    }
}