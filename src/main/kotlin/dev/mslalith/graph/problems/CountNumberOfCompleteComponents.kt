package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSetBySize

class CountNumberOfCompleteComponents : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountNumberOfCompleteComponents().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            ),
            output = 3
        ),
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 2),
                intArrayOf(3, 4),
                intArrayOf(3, 5)
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return countCompleteComponents(testCaseInput.first, testCaseInput.second)
    }

    private fun countCompleteComponents(n: Int, edges: Array<IntArray>): Int {
        val ds = DisjointSetBySize(n)
        val inDegree = IntArray(n)

        for ((u, v) in edges) {
            ds.union(u, v)
            inDegree[u]++
            inDegree[v]++
        }

        val components = Array(n) { mutableListOf<Int>() }
        for (i in 0 until n) components[ds.findParent(i)] += i

        return components.count { component ->
            when {
                component.isEmpty() -> false
                else -> component.all { inDegree[it] == component.size - 1 }
            }
        }
    }
}
