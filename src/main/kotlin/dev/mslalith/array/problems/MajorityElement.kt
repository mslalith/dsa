package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MajorityElement : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MajorityElement().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 2, 3),
            output = 3
        ),
        TestCase(
            input = intArrayOf(2, 2, 1, 1, 1, 2, 2),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return majorityElement(testCaseInput)
    }

    private fun majorityElement(nums: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        for (num in nums) map[num] = map.getOrDefault(num, 0) + 1

        val half = nums.size / 2
        return map.keys.maxBy { map.getValue(it) > half }
    }
}
