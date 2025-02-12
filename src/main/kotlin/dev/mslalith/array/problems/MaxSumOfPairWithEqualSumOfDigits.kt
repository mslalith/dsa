package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaxSumOfPairWithEqualSumOfDigits : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaxSumOfPairWithEqualSumOfDigits().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(18, 43, 36, 13, 7),
            output = 54
        ),
        TestCase(
            input = intArrayOf(10, 12, 19, 14),
            output = -1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maximumSum(testCaseInput)
    }

    private fun maximumSum(nums: IntArray): Int {
        val n = nums.size
        if (n == 1) return -1

        fun Int.digitsSum(): Int {
            var x = this
            var sum = 0

            while (x != 0) {
                sum += x % 10
                x /= 10
            }

            return sum
        }

        var maxSum = -1
        val sumToNumsMap = nums.groupBy { it.digitsSum() }

        for (list in sumToNumsMap.values) {
            if (list.size == 1) continue

            var largest = 0
            var secondLargest = 0

            for (item in list) {
                if (item > largest) {
                    secondLargest = largest
                    largest = item
                } else if (item > secondLargest) {
                    secondLargest = item
                }
            }

            maxSum = max(maxSum, largest + secondLargest)
        }

        return maxSum
    }

    private fun maximumSumNaive(nums: IntArray): Int {
        val n = nums.size

        fun Int.digitsSum(): Int {
            var x = this
            var sum = 0

            while (x != 0) {
                sum += x % 10
                x /= 10
            }

            return sum
        }

        val sumArray = IntArray(n) { nums[it].digitsSum() }

        var maxSum = -1

        for (i in 0 until n) {
            for (j in (i + 1) until n) {
                if (i != j && sumArray[i] == sumArray[j]) maxSum = max(maxSum, nums[i] + nums[j])
            }
        }

        return maxSum
    }
}
