package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LargestColorValueInDirectedGraph : TestCaseProblem<Pair<String, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LargestColorValueInDirectedGraph().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = "abaca" to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4)
            ),
            output = 3
        ),
        TestCase(
            input = "a" to arrayOf(
                intArrayOf(0, 0)
            ),
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<String, Array<IntArray>>): Int {
        return largestPathValue(testCaseInput.first, testCaseInput.second)
    }

    private fun largestPathValue(colors: String, edges: Array<IntArray>): Int {
        val n = colors.length

        val adjList = Array(n) { mutableListOf<Int>() }
        for ((u, v) in edges) adjList[u] += v

        val count = Array(n) { IntArray(26) }
        val visited = IntArray(n)

        fun dfs(node: Int): Int {
            if (visited[node] == 1) return Int.MAX_VALUE
            if (visited[node] == 2) return count[node][colors[node] - 'a']

            visited[node] = 1

            for (adjNode in adjList[node]) {
                val res = dfs(adjNode)
                if (res == Int.MAX_VALUE) return Int.MAX_VALUE
                for (c in 0..<26) count[node][c] = maxOf(count[node][c], count[adjNode][c])
            }

            val col = colors[node] - 'a'
            count[node][col]++
            visited[node] = 2

            return count[node][col]
        }

        var ans = 0

        for (i in 0..<n) {
            ans = maxOf(ans, dfs(i))
            if (ans == Int.MAX_VALUE) break
        }

        return if (ans == Int.MAX_VALUE) -1 else ans
    }
}
