package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindCenterOfStarGraph : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindCenterOfStarGraph().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(4, 2)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(5, 1),
                intArrayOf(1, 3),
                intArrayOf(1, 4)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 18),
                intArrayOf(18, 2),
                intArrayOf(3, 18),
                intArrayOf(18, 4),
                intArrayOf(18, 5),
                intArrayOf(6, 18),
                intArrayOf(18, 7),
                intArrayOf(18, 8),
                intArrayOf(18, 9),
                intArrayOf(18, 10),
                intArrayOf(18, 11),
                intArrayOf(12, 18),
                intArrayOf(18, 13),
                intArrayOf(18, 14),
                intArrayOf(15, 18),
                intArrayOf(16, 18),
                intArrayOf(17, 18)
            ),
            output = 18
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return findCenter(testCaseInput)
    }

    private fun findCenter(edges: Array<IntArray>): Int {
        val n = edges.size
        val inDegreeMap = hashMapOf<Int, Int>()

        for (edge in edges) {
            inDegreeMap[edge[0]] = inDegreeMap.getOrDefault(edge[0], 0) + 1
            inDegreeMap[edge[1]] = inDegreeMap.getOrDefault(edge[1], 0) + 1
        }

        for (key in inDegreeMap.keys) {
            if (inDegreeMap.getValue(key) == n) return key
        }

        return -1
    }
}
