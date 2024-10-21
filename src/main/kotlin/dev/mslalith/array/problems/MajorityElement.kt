package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class MajorityElement : Problem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MajorityElement().run()
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
        val half = nums.size / 2
        val map = hashMapOf<Int, Int>()
        nums.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        return map.keys.maxBy { map.getValue(it) > half }
    }
}
