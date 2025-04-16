package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountTheNumberOfGoodSubarrays : TestCaseProblem<Pair<IntArray, Int>, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountTheNumberOfGoodSubarrays().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(1,1,1,1,1) to 10,
            output = 1
        ),
        TestCase(
            input = intArrayOf(3,1,4,3,2,2,4) to 2,
            output = 4
        )
    )
    
    override fun solve(testCaseInput: Pair<IntArray, Int>): Long {
        return countGood(testCaseInput.first, testCaseInput.second)
    }

    private fun countGood(nums: IntArray, k: Int): Long {
        val n = nums.size
        val numsFreq = hashMapOf<Int, Int>()

        var count = 0L
        var left = 0
        var right = 0
        var limit = k

        while (right < n) {
            val rightNum = nums[right]
            limit -= numsFreq.getOrDefault(rightNum, 0)
            numsFreq[rightNum] = numsFreq.getOrDefault(rightNum, 0) + 1

            while (limit <= 0) {
                val leftNum = nums[left]
                numsFreq[leftNum] = numsFreq.getOrDefault(leftNum, 0) - 1
                limit += numsFreq.getOrDefault(leftNum, 0)
                left++
            }

            count += left
            right++
        }

        return count
    }

    private fun countGoodNaive(nums: IntArray, k: Int): Long {
        val n = nums.size

        var count = 0L

        for (i in 0 until n) {
            val numsFreq = hashMapOf<Int, Int>()
            var pairs = 0L

            for (j in i until n) {
                val num = nums[j]
                pairs += numsFreq.getOrDefault(num, 0)
                numsFreq[num] = numsFreq.getOrDefault(num, 0) + 1
                if (pairs >= k) count++
            }
        }

        return count
    }
}
