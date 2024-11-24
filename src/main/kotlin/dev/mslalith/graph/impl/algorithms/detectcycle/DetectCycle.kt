package dev.mslalith.graph.impl.algorithms.detectcycle

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph

interface DetectCycle {
    fun hasCycle(undirectedGraph: UndirectedGraph): Boolean
    fun hasCycle(directedGraph: DirectedGraph): Boolean
}
