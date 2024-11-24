package dev.mslalith.graph.impl.algorithms.shortedpath

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph

interface ShortestPath {
    fun shortestPathFrom(undirectedGraph: UndirectedGraph, source: Int): IntArray
    fun shortestPathFrom(directedGraph: DirectedGraph, source: Int): IntArray

    fun shortestPathFrom(undirectedGraph: UndirectedGraph, source: Int, destination: Int): Int = shortestPathFrom(undirectedGraph, source).getOrNull(destination) ?: -1
    fun shortestPathFrom(directedGraph: DirectedGraph, source: Int, destination: Int): Int = shortestPathFrom(directedGraph, source).getOrNull(destination) ?: -1
}
