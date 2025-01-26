package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

class MaximumEmployeesToBeInvitedToMeeting : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumEmployeesToBeInvitedToMeeting().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 2, 1, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(1, 2, 0),
            output = 3
        ),
        TestCase(
            input = intArrayOf(3, 0, 1, 4, 1),
            output = 4
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return maximumInvitations(testCaseInput)
    }

    private fun maximumInvitations(favorite: IntArray): Int {
        val n = favorite.size
        val inDegree = IntArray(n)

        fun buildDepth(): IntArray {
            val queue: Queue<Int> = LinkedList()
            val depth = IntArray(n) { 1 }

            for (fav in favorite) inDegree[fav]++
            for (i in 0 until n) if (inDegree[i] == 0) queue.add(i)

            while (queue.isNotEmpty()) {
                val curr = queue.poll()
                val next = favorite[curr]

                depth[next] = max(depth[next], depth[curr] + 1)

                if (--inDegree[next] == 0) queue.add(next)
            }

            return depth
        }

        fun findMaxInvitations(depth: IntArray): Int {
            var longestCycle = 0
            var twoCycleInvitations = 0

            for (person in 0 until n) {
                if (inDegree[person] == 0) continue
                var cycleLength = 0
                var curr = person

                while (inDegree[curr] != 0) {
                    inDegree[curr] = 0
                    cycleLength++
                    curr = favorite[curr]
                }

                if (cycleLength == 2) {
                    twoCycleInvitations += depth[person] + depth[favorite[person]]
                } else {
                    longestCycle = max(longestCycle, cycleLength)
                }
            }

            return max(longestCycle, twoCycleInvitations)
        }

        val depth = buildDepth()
        return findMaxInvitations(depth)
    }
}
