package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.FindClosestNodeToGivenTwoNodes.FindClosestNodeToGivenTwoNodesParams
import dev.mslalith.utils.stringFromArray

class FindClosestNodeToGivenTwoNodes : TestCaseProblem<FindClosestNodeToGivenTwoNodesParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindClosestNodeToGivenTwoNodes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FindClosestNodeToGivenTwoNodesParams, Int>> = arrayOf(
        TestCase(
            input = FindClosestNodeToGivenTwoNodesParams(
                edges = intArrayOf(2, 2, 3, -1),
                node1 = 0,
                node2 = 1
            ),
            output = 2
        ),
        TestCase(
            input = FindClosestNodeToGivenTwoNodesParams(
                edges = intArrayOf(1, 2, -1),
                node1 = 0,
                node2 = 2
            ),
            output = 2
        ),
        TestCase(
            input = FindClosestNodeToGivenTwoNodesParams(
                edges = intArrayOf(5, 4, 5, 4, 3, 6, -1),
                node1 = 0,
                node2 = 1
            ),
            output = -1
        )
    )

    override fun solve(testCaseInput: FindClosestNodeToGivenTwoNodesParams): Int {
        return closestMeetingNode(testCaseInput.edges, testCaseInput.node1, testCaseInput.node2)
    }

    private fun closestMeetingNode(edges: IntArray, node1: Int, node2: Int): Int {
        val n = edges.size

        fun dfs(node: Int, distance: Int, distances: IntArray) {
            var current = node
            var dist = distance

            while (current != -1 && distances[current] == -1) {
                distances[current] = dist++
                current = edges[current]
            }
        }

        val distance1 = IntArray(n) { -1 }
        val distance2 = IntArray(n) { -1 }
        dfs(node1, 0, distance1)
        dfs(node2, 0, distance2)

        var res = -1
        var mini = Int.MAX_VALUE

        for (i in 0..<n) {
            if (distance1[i] >= 0 && distance2[i] >= 0) {
                val maxi = maxOf(distance1[i], distance2[i])
                if (maxi < mini) {
                    mini = maxi
                    res = i
                }
            }
        }

        return res
    }

    data class FindClosestNodeToGivenTwoNodesParams(
        val edges: IntArray,
        val node1: Int,
        val node2: Int
    ) {
        override fun toString(): String {
            return """
                
                edges: ${stringFromArray(edges)}
                node1: $node1
                node2: $node2
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FindClosestNodeToGivenTwoNodesParams

            if (node1 != other.node1) return false
            if (node2 != other.node2) return false
            if (!edges.contentEquals(other.edges)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = node1
            result = 31 * result + node2
            result = 31 * result + edges.contentHashCode()
            return result
        }
    }
}
