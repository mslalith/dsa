package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue

class MinimumOperationsToExceedThresholdValueII : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumOperationsToExceedThresholdValueII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 11, 10, 1, 3) to 10,
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 1, 2, 4, 9) to 20,
            output = 4
        ),
        TestCase(
            input = intArrayOf(1000000000, 999999999, 1000000000, 999999999, 1000000000, 999999999) to 1000000000,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return minOperations(testCaseInput.first, testCaseInput.second)
    }

    private fun minOperations(nums: IntArray, k: Int): Int {
        val pq = PriorityQueue<Long>()
        for (num in nums) pq.add(num.toLong())

        var count = 0

        while (pq.peek() < k && pq.size > 1) {
            count++
            val small = pq.poll()
            val large = pq.poll()
            pq.add((small * 2) + large)
        }

        return count
    }
}
