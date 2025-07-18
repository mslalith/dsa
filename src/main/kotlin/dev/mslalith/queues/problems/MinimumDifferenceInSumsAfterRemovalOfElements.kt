package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class MinimumDifferenceInSumsAfterRemovalOfElements : TestCaseProblem<IntArray, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumDifferenceInSumsAfterRemovalOfElements().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 1, 2),
            output = -1
        ),
        TestCase(
            input = intArrayOf(7, 9, 5, 8, 1, 3),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Long {
        return minimumDifference(testCaseInput)
    }

    private fun minimumDifference(nums: IntArray): Long {
        val n = nums.size
        val k = n / 3

        val leftMinimums = LongArray(n)
        val rightMaximums = LongArray(n)

        val maxLeftHeap = PriorityQueue<Int> { a, b -> b - a }
        val minRightHeap = PriorityQueue<Int>()

        var leftSum = 0L
        var rightSum = 0L
        var minDiff = Long.MAX_VALUE

        for (i in 0..<k) {
            maxLeftHeap.add(nums[i])
            leftSum += nums[i]
        }
        leftMinimums[k - 1] = leftSum

        for (i in k..<n - k) {
            val x = nums[i]
            if (x < maxLeftHeap.peek()) {
                leftSum += (x - maxLeftHeap.poll()).toLong()
                maxLeftHeap.add(x)
            }
            leftMinimums[i] = leftSum
        }


        for (i in n - 1 downTo n - k) {
            minRightHeap.add(nums[i])
            rightSum += nums[i]
        }
        rightMaximums[n - k] = rightSum

        for (i in n - k - 1 downTo k - 1) {
            val x = nums[i]
            if (x > minRightHeap.peek()) {
                rightSum += (x - minRightHeap.poll()).toLong()
                minRightHeap.add(x)
            }
            rightMaximums[i] = rightSum
        }


        for (i in k - 1..<n - k) {
            minDiff = minOf(minDiff, leftMinimums[i] - rightMaximums[i + 1])
        }

        return minDiff
    }
}