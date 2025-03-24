package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class NumberOfWaysToArriveAtDestination : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfWaysToArriveAtDestination().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 7 to arrayOf(
                intArrayOf(0, 6, 7),
                intArrayOf(0, 1, 2),
                intArrayOf(1, 2, 3),
                intArrayOf(1, 3, 3),
                intArrayOf(6, 3, 3),
                intArrayOf(3, 5, 1),
                intArrayOf(6, 5, 1),
                intArrayOf(2, 5, 1),
                intArrayOf(0, 4, 5),
                intArrayOf(4, 6, 2)
            ),
            output = 4
        ),
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(1, 0, 10)
            ),
            output = 1
        ),
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(0, 1, 1000000000),
                intArrayOf(0, 3, 1000000000),
                intArrayOf(1, 3, 1000000000),
                intArrayOf(1, 2, 1000000000),
                intArrayOf(1, 5, 1000000000),
                intArrayOf(3, 4, 1000000000),
                intArrayOf(4, 5, 1000000000),
                intArrayOf(2, 5, 1000000000)
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return countPaths(testCaseInput.first, testCaseInput.second)
    }

    private fun countPaths(n: Int, roads: Array<IntArray>): Int {
        val adjList = Array(n) { mutableListOf<Pair<Int, Int>>() }
        for ((u, v, t) in roads) {
            adjList[u] += v to t
            adjList[v] += u to t
        }

        val mod = 1_000_000_000 + 7
        val source = 0
        val target = n - 1

        val distance = LongArray(n) { Long.MAX_VALUE }
        val ways = IntArray(n)

        distance[source] = 0
        ways[source] = 1

        // (distance to vertex)
        val queue = PriorityQueue(compareBy<Pair<Long, Int>> { it.first })
        queue.add(0L to source)

        while (queue.isNotEmpty()) {
            val (distanceSoFar, node) = queue.poll()

            for ((adjNode, weight) in adjList[node]) {
                val d = distanceSoFar + weight

                if (d < distance[adjNode]) {
                    distance[adjNode] = d
                    queue.add(d to adjNode)

                    // encountered shorted path
                    // there's only [node] ways we can visit to [adjNode]
                    ways[adjNode] = ways[node]
                } else if (d == distance[adjNode]) {
                    // we can come to [adjNode] with the same [d] distance
                    // so add up [node] ways
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod
                }
            }
        }

        return ways[target]
    }
}
