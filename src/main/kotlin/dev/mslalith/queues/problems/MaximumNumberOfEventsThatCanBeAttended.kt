package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import java.util.PriorityQueue

class MaximumNumberOfEventsThatCanBeAttended : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumNumberOfEventsThatCanBeAttended().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(1, 2)
            ),
            output = 4
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(1, 6),
                intArrayOf(1, 7)
            ),
            output = 7
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return maxEvents(testCaseInput.createClone())
    }

    private fun maxEvents(events: Array<IntArray>): Int {
        events.sortBy { it[0] }

        val n = events.size
        val pq = PriorityQueue<Int>()
        val firstStartDay = events[0][0]
        val lastEndDay = events.maxOf { it[1] }

        var count = 0
        var i = 0

        for (today in firstStartDay..lastEndDay) {

            while (i < n && events[i][0] == today) {
                pq.add(events[i][1])
                i++
            }

            while (pq.isNotEmpty() && pq.peek() < today) pq.remove()

            if (pq.isNotEmpty()) {
                pq.remove()
                count++
            }
        }

        return count
    }
}