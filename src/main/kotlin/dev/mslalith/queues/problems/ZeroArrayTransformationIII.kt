package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import java.util.PriorityQueue

class ZeroArrayTransformationIII : TestCaseProblem<Pair<IntArray, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ZeroArrayTransformationIII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 0, 2) to arrayOf(
                intArrayOf(0, 2),
                intArrayOf(0, 2),
                intArrayOf(1, 1)
            ),
            output = 1
        ),
        TestCase(
            input = intArrayOf(1, 1, 1, 1) to arrayOf(
                intArrayOf(1, 3),
                intArrayOf(0, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 2)
            ),
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4) to arrayOf(
                intArrayOf(0, 3)
            ),
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Array<IntArray>>): Int {
        return maxRemoval(testCaseInput.first, testCaseInput.second.createClone())
    }

    private fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {
        queries.sortBy { it[0] }

        val available = PriorityQueue(reverseOrder<Int>())
        val assigned = PriorityQueue<Int>()

        val querySize = queries.size
        var count = 0
        var k = 0

        for (time in nums.indices) {
            while (assigned.isNotEmpty() && assigned.peek() < time) assigned.poll()
            while (k < querySize && queries[k][0] <= time) available.add(queries[k++][1])
            while (assigned.size < nums[time] && available.isNotEmpty() && available.peek() >= time) {
                assigned.add(available.poll())
                count++
            }
            if (assigned.size < nums[time]) return -1
        }

        return querySize - count
    }
}