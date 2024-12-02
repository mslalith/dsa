package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSet

class NumberOfOperationsToMakeNetworkConnected : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfOperationsToMakeNetworkConnected().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 2)
            ),
            output = 1
        ),
        TestCase(
            input = 6 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(0, 3),
                intArrayOf(1, 2),
                intArrayOf(1, 3)
            ),
            output = 2
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return makeConnected(testCaseInput.first, testCaseInput.second)
    }

    private fun makeConnected(n: Int, connections: Array<IntArray>): Int {
        val ds = DisjointSet(n)

        for ((u, v) in connections) ds.union(u, v)

        if ((n - 1) <= connections.size) return ds.componentsCount() - 1

        return -1
    }
}
