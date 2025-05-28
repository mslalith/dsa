package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.MaximizeNumberOfTargetNodesAfterConnectingTreesI.MaximizeNumberOfTargetNodesAfterConnectingTreesIParams
import dev.mslalith.utils.stringFromArray

class MaximizeNumberOfTargetNodesAfterConnectingTreesI : TestCaseProblem<MaximizeNumberOfTargetNodesAfterConnectingTreesIParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximizeNumberOfTargetNodesAfterConnectingTreesI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MaximizeNumberOfTargetNodesAfterConnectingTreesIParams, IntArray>> = arrayOf(
        TestCase(
            input = MaximizeNumberOfTargetNodesAfterConnectingTreesIParams(
                edges1 = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(2, 3),
                    intArrayOf(2, 4)
                ),
                edges2 = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(2, 7),
                    intArrayOf(1, 4),
                    intArrayOf(4, 5),
                    intArrayOf(4, 6)
                ),
                k = 2
            ),
            output = intArrayOf(9, 7, 9, 8, 8)
        ),
        TestCase(
            input = MaximizeNumberOfTargetNodesAfterConnectingTreesIParams(
                edges1 = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(0, 3),
                    intArrayOf(0, 4)
                ),
                edges2 = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 3)
                ),
                k = 1
            ),
            output = intArrayOf(6, 3, 3, 3, 3)
        )
    )

    override fun solve(testCaseInput: MaximizeNumberOfTargetNodesAfterConnectingTreesIParams): IntArray {
        return maxTargetNodes(testCaseInput.edges1, testCaseInput.edges2, testCaseInput.k)
    }

    private fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>, k: Int): IntArray {

        fun buildAdjList(edges: Array<IntArray>): List<List<Int>> {
            val adjList = List(edges.size + 1) { mutableListOf<Int>() }

            for ((u, v) in edges) {
                adjList[u] += v
                adjList[v] += u
            }

            return adjList
        }

        fun dfs(adjList: List<List<Int>>, node: Int, curr: Int, kCount: Int): Int {
            if (kCount < 0) return 0

            var count = 1

            for (v in adjList[node]) {
                if (v != curr) count += dfs(adjList, v, node, kCount - 1)
            }

            return count
        }

        val adjList1 = buildAdjList(edges1)
        val adjList2 = buildAdjList(edges2)

        var maxiB = 0
        for (i in adjList2.indices) maxiB = maxOf(maxiB, dfs(adjList2, i, -1, k - 1))

        val res = IntArray(adjList1.size)
        for (i in adjList1.indices) res[i] = dfs(adjList1, i, -1, k) + maxiB
        return res
    }

    data class MaximizeNumberOfTargetNodesAfterConnectingTreesIParams(
        val edges1: Array<IntArray>,
        val edges2: Array<IntArray>,
        val k: Int
    ) {
        override fun toString(): String {
            return """
                
                edges1: ${stringFromArray(edges1)}
                edges2: ${stringFromArray(edges2)}
                k: $k
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MaximizeNumberOfTargetNodesAfterConnectingTreesIParams

            if (k != other.k) return false
            if (!edges1.contentDeepEquals(other.edges1)) return false
            if (!edges2.contentDeepEquals(other.edges2)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = k
            result = 31 * result + edges1.contentDeepHashCode()
            result = 31 * result + edges2.contentDeepHashCode()
            return result
        }
    }
}