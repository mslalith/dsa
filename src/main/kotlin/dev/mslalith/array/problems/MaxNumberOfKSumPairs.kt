package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaxNumberOfKSumPairs : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaxNumberOfKSumPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1,2,3,4) to 5,
            output = 2
        ),
        TestCase(
            input = intArrayOf(3,1,3,4,3) to 6,
            output = 1
        ),
        TestCase(
            input = intArrayOf(2,2,2,3,1,1,4,1) to 4,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return maxOperations(testCaseInput.first, testCaseInput.second)
    }

    private fun maxOperations(nums: IntArray, k: Int): Int {
        val hashMap = hashMapOf<Int, Int>()
        for (num in nums) {
            if (num < k) hashMap[num] = hashMap.getOrDefault(num, 0) + 1
        }

        var count = 0
        for (num in nums) {
            val other = k - num
            val minimum = when {
                other == num -> 2
                else -> 1
            }
            if (hashMap.getOrDefault(num, 0) > 0 && hashMap.getOrDefault(other, 0) >= minimum) {
                hashMap[num] = hashMap.getOrDefault(num, 0) - 1
                hashMap[other] = hashMap.getOrDefault(other, 0) - 1
                count++
            }
        }

        return count
    }

    private fun maxOperationsNaive(nums: IntArray, k: Int): Int {
        val n = nums.size
        if (n == 1) return if (nums[0] == k) 1 else 0

        var left = 0
        var right = 1
        var count = 0

        while (left < n) {
            if (nums[left] == 0) {
                left++
                right = left + 1
                continue
            }
            while (right < n) {
                if (nums[right] == 0) {
                    right++
                    continue
                }
                if (nums[left] + nums[right] == k) {
                    nums[left] = 0
                    nums[right] = 0
                    count++
                    break
                }
                right++
            }
            left++
            right = left + 1
        }

        return count
    }
}
