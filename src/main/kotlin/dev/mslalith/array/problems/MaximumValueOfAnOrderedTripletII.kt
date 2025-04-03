package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximumValueOfAnOrderedTripletII : TestCaseProblem<IntArray, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumValueOfAnOrderedTripletII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(12, 6, 1, 2, 7),
            output = 77
        ),
        TestCase(
            input = intArrayOf(1, 10, 3, 4, 19),
            output = 133
        ),
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = 0
        )
    )

    override fun solve(testCaseInput: IntArray): Long {
        return maximumTripletValue(testCaseInput)
    }

    private fun maximumTripletValue(nums: IntArray): Long {
        val n = nums.size

        val right = IntArray(n)
        right[n - 1] = nums[n - 1]
        for (i in (n - 2) downTo 0) right[i] = max(nums[i], right[i + 1])

        var value = 0L
        var left = nums[0].toLong()

        for (i in 0 until (n - 1)) {
            value = max(value, (left - nums[i]) * right[i + 1])
            left = max(left, nums[i].toLong())
        }

        return value
    }
}
