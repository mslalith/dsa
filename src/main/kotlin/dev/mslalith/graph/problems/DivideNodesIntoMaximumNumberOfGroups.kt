package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*
import kotlin.math.abs
import kotlin.math.max

class DivideNodesIntoMaximumNumberOfGroups : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DivideNodesIntoMaximumNumberOfGroups().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 6),
                intArrayOf(2, 3),
                intArrayOf(4, 6)
            ),
            output = 4
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 1)
            ),
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return magnificentSets(testCaseInput.first, testCaseInput.second)
    }

    private fun magnificentSets(n: Int, edges: Array<IntArray>): Int {
        val adjList = Array(n + 1) { mutableListOf<Int>() }
        for ((u, v) in edges) {
            adjList[u] += v
            adjList[v] += u
        }

        val visited = IntArray(n + 1) { -1 }

        fun getConnectedNodes(node: Int): Set<Int> {
            val connectedNodes = mutableSetOf<Int>()
            val queue: Queue<Int> = LinkedList()
            queue.add(node)

            while (queue.isNotEmpty()) {
                val curr = queue.poll()
                if (curr in connectedNodes) continue

                connectedNodes += curr
                for (neighbour in adjList[curr]) queue.add(neighbour)
            }

            return connectedNodes
        }

        fun findGroupCount(connectedNodes: Set<Int>, node: Int): Int {
            for (i in connectedNodes) visited[i] = -1

            val queue: Queue<Int> = LinkedList()
            queue.add(node)
            visited[node] = 1

            var depth = 1

            while (queue.isNotEmpty()) {
                val curr = queue.poll()

                for (neighbour in adjList[curr]) {
                    if (visited[neighbour] == -1) {
                        visited[neighbour] = visited[curr] + 1
                        depth = max(depth, visited[neighbour])
                        queue.add(neighbour)
                    } else if (abs(visited[curr] - visited[neighbour]) != 1) {
                        return -1
                    }

                }
            }

            return depth
        }

        var maxGroupCount = 0

        for (i in 1..n) {
            if (visited[i] != -1) continue

            val connectedNodes = getConnectedNodes(i)

            val groupCount = connectedNodes.maxOf { findGroupCount(connectedNodes, it) }
            if (groupCount == -1) return -1

            maxGroupCount += groupCount
        }

        return maxGroupCount
    }
}
