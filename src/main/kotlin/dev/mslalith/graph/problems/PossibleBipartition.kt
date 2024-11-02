package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class PossibleBipartition : TestCaseProblem<Pair<Int, Array<IntArray>>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PossibleBipartition().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Boolean>> = arrayOf(
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 4)
            ),
            output = true
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3)
            ),
            output = false
        ),
        TestCase(
            input = 10 to arrayOf(
                intArrayOf(4, 7),
                intArrayOf(4, 8),
                intArrayOf(5, 6),
                intArrayOf(1, 6),
                intArrayOf(3, 7),
                intArrayOf(2, 5),
                intArrayOf(5, 8),
                intArrayOf(1, 2),
                intArrayOf(4, 9),
                intArrayOf(6, 10),
                intArrayOf(8, 10),
                intArrayOf(3, 6),
                intArrayOf(2, 10),
                intArrayOf(9, 10),
                intArrayOf(3, 9),
                intArrayOf(2, 3),
                intArrayOf(1, 9),
                intArrayOf(4, 6),
                intArrayOf(5, 7),
                intArrayOf(3, 8),
                intArrayOf(1, 8),
                intArrayOf(1, 7),
                intArrayOf(2, 4)
            ),
            output = true
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Boolean {
        return possibleBipartition(testCaseInput.first, testCaseInput.second)
    }

    private fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
        if (n == 1) return dislikes.isEmpty()

        val adjList = Array(n + 1) { mutableListOf<Int>() }
        for ((a, b) in dislikes) {
            adjList[a] += b
            adjList[b] += a
        }

        val relations = IntArray(n + 1)
        for (node in 1..n) {
            if (relations[node] == 0 && !relateNodeFrom(node, 1, relations, adjList)) return false
        }
        return true
    }

    private fun relateNodeFrom(node: Int, relation: Int, relations: IntArray, adjList: Array<MutableList<Int>>): Boolean {
        relations[node] = relation

        for (hater in adjList[node]) {
            if (relations[hater] == 0) {
                val nextRelation = if (relation == 1) 2 else 1
                if (!relateNodeFrom(hater, nextRelation, relations, adjList)) return false
            } else if (relations[hater] == relation) {
                return false
            }
        }

        return true
    }
}
