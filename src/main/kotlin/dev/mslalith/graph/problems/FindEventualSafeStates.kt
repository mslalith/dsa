package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindEventualSafeStates : TestCaseProblem<Array<IntArray>, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindEventualSafeStates().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, List<Int>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(5),
                intArrayOf(0),
                intArrayOf(5),
                intArrayOf(),
                intArrayOf()
            ),
            output = listOf(2, 4, 5, 6)
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 2),
                intArrayOf(3, 4),
                intArrayOf(0, 4),
                intArrayOf()
            ),
            output = listOf(4)
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): List<Int> {
        return eventualSafeNodes(testCaseInput)
    }

    private fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
        val n = graph.size

        val adjList = Array(n) { mutableListOf<Int>() }
        for (i in graph.indices) {
            graph[i].forEach { adjList[i].add(it) }
        }

        val visited = IntArray(n)
        val safeNodes = BooleanArray(n)

        fun visitNode(node: Int, safeNodes: BooleanArray): Boolean {
            visited[node] = 2

            for (neighbour in adjList[node]) {
                if (visited[neighbour] == 0) {
                    if (!visitNode(neighbour, safeNodes)) return false
                } else if (visited[neighbour] == 2) return false
            }

            visited[node] = 1
            safeNodes[node] = true
            return true
        }

        for (i in graph.indices) {
            if (visited[i] == 0) visitNode(i, safeNodes)
        }

        val result = mutableListOf<Int>()
        for (i in graph.indices) {
            if (safeNodes[i]) result.add(i)
        }
        return result
    }
}
