package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SubarraySumEqualsK : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SubarraySumEqualsK().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 1) to 2,
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 2, 3) to 3,
            output = 2
        ),
        TestCase(
            input = intArrayOf(1) to 0,
            output = 0
        ),
        TestCase(
            input = intArrayOf(-1, -1, 1) to 0,
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return subarraySum(testCaseInput.first, testCaseInput.second)
    }

    private fun subarraySum(nums: IntArray, k: Int): Int {
        val frequencyMap = hashMapOf<Int, Int>()
        frequencyMap[0] = 1

        var prefixSum = 0
        var count = 0

        for (num in nums) {
            prefixSum += num
            val other = prefixSum - k

            count += frequencyMap.getOrDefault(other, 0)

            frequencyMap[prefixSum] = frequencyMap.getOrDefault(prefixSum, 0) + 1
        }

        return count
    }
}
