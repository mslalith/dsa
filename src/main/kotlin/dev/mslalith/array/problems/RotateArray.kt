package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class RotateArray : Problem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RotateArray().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 4, 5, 6, 7) to 3,
            output = intArrayOf(5, 6, 7, 1, 2, 3, 4)
        ),
        TestCase(
            input = intArrayOf(-1, -100, 3, 99) to 2,
            output = intArrayOf(3, 99, -1, -100)
        ),
        TestCase(
            input = intArrayOf(1, 2) to 3,
            output = intArrayOf(2, 1)
        )
    )
        .let { arrayOf(it.last()) }

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        rotate(testCaseInput.first, testCaseInput.second)
        return testCaseInput.first
    }

    private fun rotate(nums: IntArray, k: Int) {
        val n = nums.size
        val rotations = k % n
        if (rotations == 0) return

        val lastK = IntArray(rotations)

        for (i in 0 until rotations) lastK[i] = nums[n - rotations + i]
        for (i in (n - 1) downTo rotations) nums[i] = nums[i - rotations]
        for (i in lastK.indices) nums[i] = lastK[i]
    }
}
