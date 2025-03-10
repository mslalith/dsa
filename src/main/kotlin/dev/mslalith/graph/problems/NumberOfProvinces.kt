package dev.mslalith.graph.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.graph.impl.disjointset.DisjointSet

class NumberOfProvinces : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfProvinces().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 0, 1)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 1)
            ),
            output = 3
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                intArrayOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
                intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)
            ),
            output = 8
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return findCircleNum(testCaseInput)
    }

    private fun findCircleNum(isConnected: Array<IntArray>): Int {
        val ds = DisjointSet(isConnected.size)

        for (i in isConnected.indices) {
            for (j in isConnected[i].indices) {
                if (isConnected[i][j] == 1) {
                    if (ds.findParent(i) != ds.findParent(j)) ds.union(i, j)
                }
            }
        }

        return ds.componentsCount()
    }

    private fun findCircleNumUsingDfs(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val visited = BooleanArray(n) { false }
        var count = 0

        for (i in 0 until n) {
            if (!visited[i]) {
                count++
                visited[i] = true
                visitCity(isConnected, i, visited)
            }
        }

        return count
    }

    private fun visitCity(connected: Array<IntArray>, i: Int, visited: BooleanArray) {
        connected[i].forEachIndexed { index, value ->
            if (value == 1 && !visited[index]) {
                visited[index] = true
                visitCity(connected, index, visited)
            }
        }
    }
}
