package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.MaximizeNumberOfTargetNodesAfterConnectingTreesII.MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams
import dev.mslalith.utils.stringFromArray

class MaximizeNumberOfTargetNodesAfterConnectingTreesII : TestCaseProblem<MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximizeNumberOfTargetNodesAfterConnectingTreesII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams, IntArray>> = arrayOf(
        TestCase(
            input = MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams(
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
                )
            ),
            output = intArrayOf(8, 7, 7, 8, 8)
        ),
        TestCase(
            input = MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams(
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
                )
            ),
            output = intArrayOf(3, 6, 6, 6, 6)
        )
    )

    override fun solve(testCaseInput: MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams): IntArray {
        return maxTargetNodes(testCaseInput.edges1, testCaseInput.edges2)
    }

    private fun maxTargetNodes(edges1: Array<IntArray>, edges2: Array<IntArray>): IntArray {

        var even1 = 0
        var even2 = 0
        var odd1 = 0
        var odd2 = 0

        fun buildAdjList(edges: Array<IntArray>): List<List<Int>> {
            val adjList = List(edges.size + 1) { mutableListOf<Int>() }

            for ((u, v) in edges) {
                adjList[u] += v
                adjList[v] += u
            }

            return adjList
        }

        fun dfs(adjList: List<List<Int>>, node: Int, parent: Int, color: IntArray, isOne: Boolean) {
            if (color[node] == 0) {
                if (isOne) even1++ else even2++
            } else {
                if (isOne) odd1++ else odd2++
            }

            for (v in adjList[node]) {
                if (v != parent) {
                    color[v] = color[node] xor 1
                    dfs(adjList, v, node, color, isOne)
                }
            }
        }

        val adjList1 = buildAdjList(edges1)
        val adjList2 = buildAdjList(edges2)

        val color1 = IntArray(adjList1.size) { -1 }
        val color2 = IntArray(adjList2.size) { -1 }

        color1[0] = 0
        dfs(adjList1, 0, -1, color1, true)

        color2[0] = 0
        dfs(adjList2, 0, -1, color2, false)

        val maxiB = maxOf(even2, odd2)
        val res = IntArray(adjList1.size)
        for (i in adjList1.indices) res[i] = (if (color1[i] == 0) even1 else odd1) + maxiB
        return res
    }

    data class MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams(
        val edges1: Array<IntArray>,
        val edges2: Array<IntArray>,
    ) {
        override fun toString(): String {
            return """
                
                edges1: ${stringFromArray(edges1)}
                edges2: ${stringFromArray(edges2)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MaximizeNumberOfTargetNodesAfterConnectingTreesIIParams

            if (!edges1.contentDeepEquals(other.edges1)) return false
            if (!edges2.contentDeepEquals(other.edges2)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = edges1.contentDeepHashCode()
            result = 31 * result + edges2.contentDeepHashCode()
            return result
        }
    }
}