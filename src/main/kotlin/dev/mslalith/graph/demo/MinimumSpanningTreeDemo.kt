package dev.mslalith.graph.demo

import dev.mslalith.graph.impl.UndirectedGraph
import dev.mslalith.graph.impl.algorithms.spanningtree.KruskalAlgorithm
import dev.mslalith.graph.impl.algorithms.spanningtree.MinimumSpanningTree
import dev.mslalith.graph.impl.algorithms.spanningtree.PrimsAlgorithm

object MinimumSpanningTreeDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val graph = UndirectedGraph.fromAdjacencyArray(
            adjArray = arrayOf(
                intArrayOf(0, 1, 2),
                intArrayOf(0, 2, 1),
                intArrayOf(1, 2, 1),
                intArrayOf(2, 3, 2),
                intArrayOf(3, 4, 1),
                intArrayOf(4, 2, 2)
            )
        )

        val algorithms: List<MinimumSpanningTree> = listOf(
            PrimsAlgorithm,
            KruskalAlgorithm
        )

        val expectedMstSum = 5

        for (algorithm in algorithms) {
            println()
            println("➡\uFE0F ${algorithm.javaClass.simpleName}")
            println()

            val mstSum = algorithm.minimumSpanningTreeSum(adjList = graph.asAdjacencyList())

            println("Sum: $mstSum")
            check(mstSum == expectedMstSum)

            println()
            println("-".repeat(n = 36))
        }
    }
}
