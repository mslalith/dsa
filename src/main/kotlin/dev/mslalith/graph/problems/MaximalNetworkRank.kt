package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MaximalNetworkRank : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximalNetworkRank().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 3),
                intArrayOf(1, 2),
                intArrayOf(1, 3)
            ),
            output = 4
        ),
        TestCase(
            input = 5 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 3),
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3),
                intArrayOf(2, 4)
            ),
            output = 5
        ),
        TestCase(
            input = 8 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(2, 4),
                intArrayOf(5, 6),
                intArrayOf(5, 7)
            ),
            output = 5
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return maximalNetworkRank(testCaseInput.first, testCaseInput.second)
    }

    private fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val inDegree = IntArray(n)
        val outDegree = IntArray(n)
        val adjMatrix = Array(n) { IntArray(n) }

        for ((u, v) in roads) {
            outDegree[u]++
            inDegree[v]++
            adjMatrix[u][v] = 1
            adjMatrix[v][u] = 1
        }

        var maxRank = 0

        for (i in 0 until n) {
            for (j in (i + 1) until n) {
                val uEdges = inDegree[i] + outDegree[i]
                val vEdges = inDegree[j] + outDegree[j]
                val rank = uEdges + vEdges - adjMatrix[i][j]
                maxRank = max(maxRank, rank)
            }
        }

        return maxRank
    }
}
