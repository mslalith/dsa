package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class TwoSum : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TwoSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 7, 11, 15) to 9,
            output = intArrayOf(0, 1)
        ),
        TestCase(
            input = intArrayOf(3, 2, 4) to 6,
            output = intArrayOf(1, 2)
        ),
        TestCase(
            input = intArrayOf(3, 3) to 6,
            output = intArrayOf(0, 1)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return twoSum(nums = testCaseInput.first, target = testCaseInput.second)
    }

    private fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = hashMapOf<Int, Int>()
        nums.forEachIndexed { i, num ->
            val index = map[target - num]
            if (index != null) return intArrayOf(index, i)
            map[num] = i
        }
        return intArrayOf()
    }
}
