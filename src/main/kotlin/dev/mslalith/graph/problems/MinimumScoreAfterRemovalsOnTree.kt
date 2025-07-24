package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class MinimumScoreAfterRemovalsOnTree : TestCaseProblem<Pair<IntArray, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumScoreAfterRemovalsOnTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 5, 5, 4, 11) to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(3, 4)
            ),
            output = 9
        ),
        TestCase(
            input = intArrayOf(5, 5, 2, 4, 4, 2) to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(5, 2),
                intArrayOf(4, 3),
                intArrayOf(1, 3)
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Array<IntArray>>): Int {
        return minimumScore(testCaseInput.first, testCaseInput.second)
    }

    private fun minimumScore(nums: IntArray, edges: Array<IntArray>): Int {
        val n = nums.size
        val subtreeXor = IntArray(n)
        val descendants = Array(n) { hashSetOf<Int>() }
        val graph = Array(n) { mutableListOf<Int>() }

        for ((u, v) in edges) {
            graph[u] += v
            graph[v] += u
        }

        fun dfs(node: Int, parent: Int, nums: IntArray) {
            subtreeXor[node] = nums[node]
            descendants[node] += node

            for (neighbor in graph[node]) {
                if (neighbor != parent) {
                    dfs(neighbor, node, nums)
                    subtreeXor[node] = subtreeXor[node] xor subtreeXor[neighbor]
                    descendants[node].addAll(descendants[neighbor])
                }
            }
        }

        dfs(0, -1, nums)

        val totalXor = subtreeXor[0]
        var minScore = Int.MAX_VALUE

        for (i in 1..<n) {
            for (j in i + 1..<n) {
                val xorI = subtreeXor[i]
                val xorJ = subtreeXor[j]
                val val1: Int
                val val2: Int
                val val3: Int

                if (j in descendants[i]) {
                    val1 = xorJ
                    val2 = xorI xor xorJ
                    val3 = totalXor xor xorI
                } else if (i in descendants[j]) {
                    val1 = xorI
                    val2 = xorJ xor xorI
                    val3 = totalXor xor xorJ
                } else {
                    val1 = xorI
                    val2 = xorJ
                    val3 = totalXor xor xorI xor xorJ
                }

                val maxVal = maxOf(val1, maxOf(val2, val3))
                val minVal = minOf(val1, minOf(val2, val3))
                minScore = minOf(minScore, maxVal - minVal)
            }
        }

        return minScore
    }
}
