package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class AllPathsFromSourceToTarget : TestCaseProblem<Array<IntArray>, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AllPathsFromSourceToTarget().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, List<List<Int>>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3),
                intArrayOf(3),
                intArrayOf(0)
            ),
            output = listOf(
                listOf(0, 1, 3),
                listOf(0, 2, 3)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(4, 3, 1),
                intArrayOf(3, 2, 4),
                intArrayOf(3),
                intArrayOf(4),
                intArrayOf()
            ),
            output = listOf(
                listOf(0, 4),
                listOf(0, 3, 4),
                listOf(0, 1, 3, 4),
                listOf(0, 1, 2, 3, 4),
                listOf(0, 1, 4)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): List<List<Int>> {
        return allPathsSourceTarget(testCaseInput)
    }

    private fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        visitNode(graph, 0, graph.size - 1, result, mutableListOf())
        return result
    }

    private fun visitNode(graph: Array<IntArray>, source: Int, target: Int, result: MutableList<List<Int>>, currentPath: MutableList<Int>) {
        if (source == target) {
            currentPath += source
            result += currentPath.toList()
            return
        }

        currentPath += source
        for (neighbour in graph[source]) {
            visitNode(graph, neighbour, target, result, currentPath)
            currentPath -= neighbour
        }
    }
}
