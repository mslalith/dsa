package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountUnreachablePairsOfNodesInAnUndirectedGraph : TestCaseProblem<Pair<Int, Array<IntArray>>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountUnreachablePairsOfNodesInAnUndirectedGraph().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Long>> = arrayOf(
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 2)
            ),
            output = 0
        ),
        TestCase(
            input = 7 to arrayOf(
                intArrayOf(0, 2),
                intArrayOf(0, 5),
                intArrayOf(2, 4),
                intArrayOf(1, 6),
                intArrayOf(5, 4)
            ),
            output = 14
        ),
        TestCase(
            input = 100000 to emptyArray(),
            output = 4999950000
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Long {
        return countPairs(testCaseInput.first, testCaseInput.second)
    }

    private fun countPairs(n: Int, edges: Array<IntArray>): Long {
        val adjList = Array(n) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            adjList[u].add(v)
            adjList[v].add(u)
        }

        val visited = hashSetOf<Int>()
        var sum = 0L
        var squareSum = 0L

        val stronglyConnectedComponentsSizeList = mutableListOf<Long>()
        for (u in 0 until n) {
            if (u !in visited) {
                stronglyConnectedComponentsSizeList.add(dfs(u, visited, adjList))
            }
        }

        for (i in stronglyConnectedComponentsSizeList.indices) {
            val x = stronglyConnectedComponentsSizeList[i]
            sum += x
            squareSum += x * x
        }

        return ((sum * sum) - squareSum) / 2
    }

    private fun dfs(node: Int, visited: HashSet<Int>, adjList: Array<MutableList<Int>>): Long {
        visited += node

        var ans = 1L

        for (neighbour in adjList[node]) {
            if (neighbour !in visited) ans += dfs(neighbour, visited, adjList)
        }

        return ans
    }
}
