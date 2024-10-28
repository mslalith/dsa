package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray

class SubArrayProductLessThanK : TestCaseProblem<SubArrayProductLessThanKParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SubArrayProductLessThanK().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<SubArrayProductLessThanKParams, Int>> = arrayOf(
        TestCase(
            input = SubArrayProductLessThanKParams(
                nums = intArrayOf(10, 5, 2, 6),
                k = 100
            ),
            output = 8
        ),
        TestCase(
            input = SubArrayProductLessThanKParams(
                nums = intArrayOf(1, 2, 3),
                k = 0
            ),
            output = 0
        ),
        TestCase(
            input = SubArrayProductLessThanKParams(
                nums = intArrayOf(10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3),
                k = 19
            ),
            output = 18
        )
    )

    override fun solve(testCaseInput: SubArrayProductLessThanKParams): Int {
        return numSubArrayProductLessThanK(testCaseInput.nums, testCaseInput.k)
    }

    private fun numSubArrayProductLessThanK(nums: IntArray, k: Int): Int {
        if (k <= 1) return 0

        var count = 0
        var left = 0
        var product = 1

        for (right in nums.indices) {
            product *= nums[right]
            while (product >= k) product /= nums[left++]
            count += right - left + 1
        }

        return count
    }
}

data class SubArrayProductLessThanKParams(
    val nums: IntArray,
    val k: Int
) {
    override fun toString(): String {
        return """
            
            nums: ${stringFromArray(nums)}
            k: $k
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SubArrayProductLessThanKParams

        if (!nums.contentEquals(other.nums)) return false
        if (k != other.k) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nums.contentHashCode()
        result = 31 * result + k
        return result
    }
}