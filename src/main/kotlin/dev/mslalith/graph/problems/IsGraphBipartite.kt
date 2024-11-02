package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class IsGraphBipartite : TestCaseProblem<Array<IntArray>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsGraphBipartite().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Boolean>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(0, 2),
                intArrayOf(0, 1, 3),
                intArrayOf(0, 2)
            ),
            output = false
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3),
                intArrayOf(0, 2),
                intArrayOf(1, 3),
                intArrayOf(0, 2)
            ),
            output = true
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Boolean {
        return isBipartite(testCaseInput)
    }

    private fun isBipartite(graph: Array<IntArray>): Boolean {
        if (graph.isEmpty()) return true

        val n = graph.size
        val colors = IntArray(n)
        for (i in 0 until n) {
            if (colors[i] == 0 && !colorConnectedNodes(i, 1, colors, graph)) return false
        }

        return true
    }

    private fun colorConnectedNodes(node: Int, color: Int, colors: IntArray, graph: Array<IntArray>): Boolean {
        colors[node] = color

        for (neighbour in graph[node]) {
            if (colors[neighbour] == 0) {
                val nextColor = if (color == 1) 2 else 1
                if (!colorConnectedNodes(neighbour, nextColor, colors, graph)) return false
            } else if (colors[neighbour] == color) return false
        }

        return true
    }
}
