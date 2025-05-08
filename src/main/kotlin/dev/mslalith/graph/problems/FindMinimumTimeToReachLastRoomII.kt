package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue
import kotlin.math.max

class FindMinimumTimeToReachLastRoomII : TestCaseProblem<Array<IntArray>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMinimumTimeToReachLastRoomII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 4),
                intArrayOf(4, 4)
            ),
            output = 7
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0)
            ),
            output = 6
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2)
            ),
            output = 4
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Int {
        return minTimeToReach(testCaseInput)
    }

    private fun minTimeToReach(moveTime: Array<IntArray>): Int {
        val rows = moveTime.size
        val cols = moveTime[0].size
        val directions = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        val time = Array(rows) { IntArray(cols) { Int.MAX_VALUE } }
        time[0][0] = 0

        val pq = PriorityQueue<Node>(compareBy { it.time })
        pq.add(Node(time = 0, x = 0, y = 0, isOneSec = true))

        while (pq.isNotEmpty()) {
            val (currentTime, x, y, isOneSec) = pq.poll()
            if (x == rows - 1 && y == cols - 1) return currentTime

            for ((dx, dy) in directions) {
                val r = x + dx
                val c = y + dy

                if (r in 0 until rows && c in 0 until cols) {
                    val waitTime = max(moveTime[r][c] - currentTime, 0)
                    val newTime = currentTime + (if (isOneSec) 1 else 2) + waitTime

                    if (newTime < time[r][c]) {
                        time[r][c] = newTime
                        pq.add(Node(time = newTime, x = r, y = c, isOneSec = !isOneSec))
                    }
                }
            }
        }

        return -1
    }

    private data class Node(
        val time: Int,
        val x: Int,
        val y: Int,
        val isOneSec: Boolean
    )
}
