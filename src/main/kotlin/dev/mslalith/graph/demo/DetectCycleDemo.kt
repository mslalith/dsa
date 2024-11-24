package dev.mslalith.graph.demo

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph
import dev.mslalith.graph.impl.algorithms.detectcycle.DetectCycle
import dev.mslalith.graph.impl.algorithms.detectcycle.DetectCycleBFS
import dev.mslalith.graph.impl.algorithms.detectcycle.DetectCycleDFS
import dev.mslalith.graph.impl.algorithms.detectcycle.DetectCycleKahnAlgorithm

object DetectCycleDemo {

    private val algorithms: List<DetectCycle> = listOf(
        DetectCycleDFS,
        DetectCycleBFS,
        DetectCycleKahnAlgorithm
    )

    private val undirectedNonCyclicGraph = UndirectedGraph.fromAdjacencyArray(
        adjArray = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 5),
            intArrayOf(2, 6),
            intArrayOf(3, 4),
            intArrayOf(3, 7),
            intArrayOf(7, 8)
        )
    )

    private val undirectedCyclicGraph = UndirectedGraph.fromAdjacencyArray(
        adjArray = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 5),
            intArrayOf(3, 4),
            intArrayOf(3, 6),
            intArrayOf(5, 7),
            intArrayOf(6, 7)
        )
    )

    private val directedNonCyclicGraph = DirectedGraph.fromAdjacencyArray(
        adjArray = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(3, 7),
            intArrayOf(4, 5),
            intArrayOf(7, 5),
            intArrayOf(5, 6),
            intArrayOf(8, 2),
            intArrayOf(8, 9),
            intArrayOf(9, 10)
        )
    )

    private val directedCyclicGraph = DirectedGraph.fromAdjacencyArray(
        adjArray = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(3, 7),
            intArrayOf(4, 5),
            intArrayOf(7, 5),
            intArrayOf(5, 6),
            intArrayOf(8, 2),
            intArrayOf(8, 9),
            intArrayOf(9, 10),
            intArrayOf(10, 8)
        )
    )

    @JvmStatic
    fun main(args: Array<String>) {
        for (algorithm in algorithms) {
            println()
            println("➡\uFE0F ${algorithm.javaClass.simpleName}")
            println()

            algorithm.runForUndirectedGraph()
            algorithm.runForDirectedGraph()

            println()
            println("-".repeat(n = 36))
        }
    }

    private fun DetectCycle.runForUndirectedGraph() {
        runViaCatching { hasCycle(undirectedGraph = undirectedNonCyclicGraph) }.display("Undirected Non-cyclic graph", false)
        runViaCatching { hasCycle(undirectedGraph = undirectedCyclicGraph) }.display("Undirected Cyclic graph", true)
    }

    private fun DetectCycle.runForDirectedGraph() {
        runViaCatching { hasCycle(directedGraph = directedNonCyclicGraph) }.display("Directed Non-cyclic graph", false)
        runViaCatching { hasCycle(directedGraph = directedCyclicGraph) }.display("Directed Cyclic graph", true)
    }

    private fun Result<Boolean>.display(message: String, expected: Boolean) {
        if (isSuccess) {
            val hasCycle = getOrNull()!!
            println("\t- $message: $hasCycle")
            check(hasCycle == expected)
        } else {
            val resultStr = exceptionOrNull()!!.message ?: "Unknown"
            println("\t- $message: $resultStr")
        }
    }

    private fun runViaCatching(
        block: () -> Boolean
    ): Result<Boolean> = try {
        Result.success(block())
    } catch (ex: UnsupportedOperationException) {
        Result.failure(ex)
    }
}
