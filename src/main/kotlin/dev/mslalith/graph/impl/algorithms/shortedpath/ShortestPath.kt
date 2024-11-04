package dev.mslalith.graph.impl.algorithms.shortedpath

import dev.mslalith.graph.impl.WeightedGraph

interface ShortestPath {
    fun shortestPathFrom(graph: WeightedGraph, source: Int): IntArray

    fun shortestPathFrom(graph: WeightedGraph, source: Int, destination: Int): Int = shortestPathFrom(graph, source).getOrNull(destination) ?: -1
}
