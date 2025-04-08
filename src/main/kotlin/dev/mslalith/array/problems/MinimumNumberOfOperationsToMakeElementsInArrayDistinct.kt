package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumNumberOfOperationsToMakeElementsInArrayDistinct : TestCaseProblem<IntArray, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumNumberOfOperationsToMakeElementsInArrayDistinct().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1,2,3,4,2,3,3,5,7),
            output = 2
        ),
        TestCase(
            input = intArrayOf(4,5,6,4,4),
            output = 2
        ),
        TestCase(
            input = intArrayOf(6,7,8,9),
            output = 0
        )
    )
    
    override fun solve(testCaseInput: IntArray): Int {
        return minimumOperations(testCaseInput)
    }

    private fun minimumOperations(nums: IntArray): Int {
        val numsFreq = IntArray(101)

        for (i in nums.lastIndex downTo 0) {
            if (++numsFreq[nums[i]] > 1) return (i + 3) / 3
        }

        return 0
    }

    private fun minimumOperationsNaive(nums: IntArray): Int {
        val n = nums.size
        val duplicateSet = hashSetOf<Int>()

        val numsFreq = hashMapOf<Int, Int>()
        for (num in nums) {
            numsFreq[num] = numsFreq.getOrDefault(num, 0) + 1
            if (numsFreq.getValue(num) > 1) duplicateSet += num
        }

        var i = 0
        var steps = 0
        while (i < n && duplicateSet.isNotEmpty()) {
            repeat(3) {
                if (i < n) {
                    val num = nums[i]
                    numsFreq[num] = numsFreq.getValue(num) - 1
                    if (numsFreq.getValue(num) == 1) duplicateSet -= num
                }
                i++
            }
            steps++
        }

        return if (duplicateSet.isNotEmpty()) 0 else steps
    }
}
