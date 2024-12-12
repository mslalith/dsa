package dev.mslalith.graph.demo

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.algorithms.shortedpath.ShortestPath
import dev.mslalith.graph.impl.algorithms.shortedpath.BellmanFord
import dev.mslalith.graph.impl.algorithms.shortedpath.Dijkstra
import dev.mslalith.graph.impl.algorithms.shortedpath.FloydWarshall

object ShortestPathDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val graph = DirectedGraph.fromAdjacencyMatrix(
            matrix = arrayOf(
                intArrayOf(0, 4, 0, 0, 0, 0, 0, 8, 0),
                intArrayOf(4, 0, 8, 0, 0, 0, 0, 11, 0),
                intArrayOf(0, 8, 0, 7, 0, 4, 0, 0, 2),
                intArrayOf(0, 0, 7, 0, 9, 14, 0, 0, 0),
                intArrayOf(0, 0, 0, 9, 0, 10, 0, 0, 0),
                intArrayOf(0, 0, 4, 14, 10, 0, 2, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 2, 0, 1, 6),
                intArrayOf(8, 11, 0, 0, 0, 0, 1, 0, 7),
                intArrayOf(0, 0, 2, 0, 0, 0, 6, 7, 0)
            )
        )

        val algorithms: List<ShortestPath> = listOf(
            Dijkstra,
            BellmanFord,
            FloydWarshall
        )

        val expected = intArrayOf(0, 4, 12, 19, 21, 11, 9, 8, 14)
        for (algorithm in algorithms) {
            println()
            println("➡\uFE0F ${algorithm.javaClass.simpleName}")
            println()

            val shortedPath = algorithm.shortestPathFrom(directedGraph = graph, source = 0)
            println(shortedPath.contentToString())
            check(shortedPath.contentEquals(expected))

            println()
            println("-".repeat(n = 36))
        }
    }
}
