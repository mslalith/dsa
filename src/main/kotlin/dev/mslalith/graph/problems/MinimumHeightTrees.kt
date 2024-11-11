package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

class MinimumHeightTrees : TestCaseProblem<Pair<Int, Array<IntArray>>, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumHeightTrees().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, List<Int>>> = arrayOf(
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(1, 0),
                intArrayOf(1, 2),
                intArrayOf(1, 3)
            ),
            output = listOf(1)
        ),
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(3, 0),
                intArrayOf(3, 1),
                intArrayOf(3, 2),
                intArrayOf(3, 4),
                intArrayOf(5, 4)
            ),
            output = listOf(3, 4)
        ),
        TestCase(
            input = 1 to emptyArray(),
            output = listOf(0)
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): List<Int> {
        return findMinHeightTrees(testCaseInput.first, testCaseInput.second)
    }

    private fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1) return listOf(0)

        val adjList = Array(n) { mutableListOf<Int>() }
        val inDegree = IntArray(n)
        for ((a, b) in edges) {
            inDegree[a]++
            inDegree[b]++
            adjList[a] += b
            adjList[b] += a
        }

        val queue: Queue<Int> = LinkedList()
        for (i in 0 until n) if (inDegree[i] == 1) queue.add(i)

        var processed = 0
        while (n - processed > 2) {
            val size = queue.size
            processed += size

            for (i in 0 until size) {
                val node = queue.poll()
                for (adjNode in adjList[node]) {
                    inDegree[adjNode]--
                    if (inDegree[adjNode] == 1) queue.add(adjNode)
                }
            }
        }

        return queue.toList()
    }

    private fun findMinHeightTreesNaive(n: Int, edges: Array<IntArray>): List<Int> {
        val adjList = Array(n) { mutableListOf<Int>() }
        val mapping = Array(n) { IntArray(n) }
        val inDegree = IntArray(n)
        for ((a, b) in edges) {
            inDegree[a]++
            inDegree[b]++
            adjList[a] += b
            adjList[b] += a
            mapping[a][b] = 1
            mapping[b][a] = 1
        }

        fun findHeights(adjList: Array<MutableList<Int>>, node: Int): Int {
            val visited = hashSetOf<Int>()

            fun findHeightFor(i: Int): Int {
                visited += i
                val neighbours = adjList[i]
                if (neighbours.isEmpty()) return 1

                var h = 1
                neighbours.forEach {
                    if (it !in visited) h = max(h, 1 + findHeightFor(it))
                }
                return h
            }

            return findHeightFor(node)
        }

        val heightsAtNode = IntArray(n)
        for (i in 0 until n) {
            heightsAtNode[i] = findHeights(adjList, i)
        }

        val minHeight = heightsAtNode.min()
        val result = mutableListOf<Int>()
        for (i in heightsAtNode.indices) if (heightsAtNode[i] == minHeight) result.add(i)
        return result
    }
}
