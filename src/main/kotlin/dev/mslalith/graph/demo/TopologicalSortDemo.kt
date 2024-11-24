package dev.mslalith.graph.demo

import dev.mslalith.graph.impl.DirectedGraph

object TopologicalSortDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val directedGraph = DirectedGraph.fromAdjacencyArray(
            adjArray = arrayOf(
                intArrayOf(2, 3),
                intArrayOf(3, 1),
                intArrayOf(4, 0),
                intArrayOf(4, 1),
                intArrayOf(5, 0),
                intArrayOf(5, 2)
            )
        )

        val expected = listOf(5, 4, 2, 3, 1, 0)

        val actual = directedGraph.topologicalSort()

        println(actual)
        check(actual == expected)
    }
}
