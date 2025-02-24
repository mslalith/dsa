package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.MostProfitablePathInTree.MostProfitablePathInTreeParams
import dev.mslalith.utils.stringFromArray
import kotlin.math.max

class MostProfitablePathInTree : TestCaseProblem<MostProfitablePathInTreeParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MostProfitablePathInTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MostProfitablePathInTreeParams, Int>> = arrayOf(
        TestCase(
            input = MostProfitablePathInTreeParams(
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(3, 4)
                ),
                bob = 3,
                amount = intArrayOf(-2, 4, 2, -4, 6)
            ),
            output = 6
        ),
        TestCase(
            input = MostProfitablePathInTreeParams(
                edges = arrayOf(
                    intArrayOf(0, 1)
                ),
                bob = 1,
                amount = intArrayOf(-7280, 235)
            ),
            output = -7280
        )
    )

    override fun solve(testCaseInput: MostProfitablePathInTreeParams): Int {
        return mostProfitablePath(testCaseInput.edges, testCaseInput.bob, testCaseInput.amount)
    }

    private fun mostProfitablePath(edges: Array<IntArray>, bob: Int, amount: IntArray): Int {
        val n = amount.size
        val depth = IntArray(n)
        val parent = IntArray(n)
        val adjList = Array(n) { mutableListOf<Int>() }

        for ((u, v) in edges) {
            adjList[u] += v
            adjList[v] += u
        }

        fun letAliceAndBobWalk(node: Int, par: Int, dep: Int) {
            depth[node] = dep
            parent[node] = par
            for (neighbour in adjList[node]) {
                if (neighbour != par) letAliceAndBobWalk(neighbour, node, dep + 1)
            }
        }

        fun collectMaxAmount(node: Int, par: Int): Int {
            val total = amount[node]
            var maxProfit = Int.MIN_VALUE

            for (neighbour in adjList[node]) {
                if (neighbour != par) maxProfit = max(maxProfit, collectMaxAmount(neighbour, node))
            }

            val extra = if (maxProfit == Int.MIN_VALUE) 0 else maxProfit
            return total + extra
        }

        letAliceAndBobWalk(0, -1, 0)

        var bobNode = bob
        var bobDepth = 0

        while (bobNode != -1) {
            if (depth[bobNode] > bobDepth) {
                amount[bobNode] = 0
            } else if (depth[bobNode] == bobDepth) {
                amount[bobNode] /= 2
            }
            bobNode = parent[bobNode]
            bobDepth++
        }

        return collectMaxAmount(0, -1)
    }

    data class MostProfitablePathInTreeParams(
        val edges: Array<IntArray>,
        val bob: Int,
        val amount: IntArray
    ) {
        override fun toString(): String = """
                
                edges: ${stringFromArray(edges)}
                bob: $bob
                amount: ${stringFromArray(amount)}
            """.trimIndent()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MostProfitablePathInTreeParams

            if (bob != other.bob) return false
            if (!edges.contentDeepEquals(other.edges)) return false
            if (!amount.contentEquals(other.amount)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = bob
            result = 31 * result + edges.contentDeepHashCode()
            result = 31 * result + amount.contentHashCode()
            return result
        }
    }
}
