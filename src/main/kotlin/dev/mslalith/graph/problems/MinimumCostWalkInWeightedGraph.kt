package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.impl.disjointset.DisjointSet
import dev.mslalith.graph.problems.MinimumCostWalkInWeightedGraph.MinimumCostWalkInWeightedGraphParams
import dev.mslalith.utils.stringFromArray

class MinimumCostWalkInWeightedGraph : TestCaseProblem<MinimumCostWalkInWeightedGraphParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumCostWalkInWeightedGraph().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MinimumCostWalkInWeightedGraphParams, IntArray>> = arrayOf(
        TestCase(
            input = MinimumCostWalkInWeightedGraphParams(
                n = 5,
                edges = arrayOf(
                    intArrayOf(0, 1, 7),
                    intArrayOf(1, 3, 7),
                    intArrayOf(1, 2, 1)
                ),
                query = arrayOf(
                    intArrayOf(0, 3),
                    intArrayOf(3, 4)
                )
            ),
            output = intArrayOf(1, -1)
        ),
        TestCase(
            input = MinimumCostWalkInWeightedGraphParams(
                n = 3,
                edges = arrayOf(
                    intArrayOf(0, 2, 7),
                    intArrayOf(0, 1, 15),
                    intArrayOf(1, 2, 6),
                    intArrayOf(1, 2, 1)
                ),
                query = arrayOf(
                    intArrayOf(1, 2)
                )
            ),
            output = intArrayOf(0)
        )
    )

    override fun solve(testCaseInput: MinimumCostWalkInWeightedGraphParams): IntArray {
        return minimumCost(testCaseInput.n, testCaseInput.edges, testCaseInput.query)
    }

    private fun minimumCost(n: Int, edges: Array<IntArray>, query: Array<IntArray>): IntArray {
        val ds = DisjointSet(n)
        for ((u, v, _) in edges) ds.union(u, v)

        val andScore = IntArray(n) { -1 }

        for ((u, _, w) in edges) {
            val uPar = ds.findParent(u)
            andScore[uPar] = if (andScore[uPar] == -1) w else andScore[uPar] and w
        }

        val queryResult = IntArray(query.size)

        for (i in query.indices) {
            val (u, v) = query[i]
            queryResult[i] = when {
                u == v -> 0
                ds.findParent(u) != ds.findParent(v) -> -1
                else -> andScore[ds.findParent(u)]
            }
        }

        return queryResult
    }

    data class MinimumCostWalkInWeightedGraphParams(
        val n: Int,
        val edges: Array<IntArray>,
        val query: Array<IntArray>
    ) {
        override fun toString(): String = """
                
                n : $n
                edges: ${stringFromArray(edges)}
                query: ${stringFromArray(query)}
            """.trimIndent()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MinimumCostWalkInWeightedGraphParams

            if (n != other.n) return false
            if (!edges.contentDeepEquals(other.edges)) return false
            if (!query.contentDeepEquals(other.query)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = n
            result = 31 * result + edges.contentDeepHashCode()
            result = 31 * result + query.contentDeepHashCode()
            return result
        }
    }
}
