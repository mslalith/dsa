package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSet

class RedundantConnection : TestCaseProblem<Array<IntArray>, IntArray>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RedundantConnection().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, IntArray>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3)
            ),
            output = intArrayOf(2, 3)
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(1, 4),
                intArrayOf(1, 5)
            ),
            output = intArrayOf(1, 4)
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): IntArray {
        return findRedundantConnection(testCaseInput)
    }

    private fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        val ds = DisjointSet(edges.size)
        val extraEdge = intArrayOf(0, 0)

        for ((u, v) in edges) {
            when {
                ds.findParent(u - 1) != ds.findParent(v - 1) -> ds.union(u - 1, v - 1)
                else -> {
                    extraEdge[0] = u
                    extraEdge[1] = v
                }
            }
        }

        return extraEdge
    }
}
