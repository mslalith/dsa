package dev.mslalith.graph.impl.algorithms.shortedpath

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph

/**
 * Finds the shortest path from single source to all nodes
 *
 * This only works with Directed Graph.
 * When an Undirected Graph is given, it needs to be converted to a Directed Graph
 *
 * Pros:
 * - helps in detecting -ve cycles
 *
 * Time Complexity: O(V * E)
 *
 */
object BellmanFord : ShortestPath {

    override fun shortestPathFrom(undirectedGraph: UndirectedGraph, source: Int): IntArray {
        return shortestPathFrom(undirectedGraph.asDirectedGraph(), source)
    }

    override fun shortestPathFrom(directedGraph: DirectedGraph, source: Int): IntArray {
        val n = directedGraph.vertices.size
        val distance = IntArray(n) { Int.MAX_VALUE }
        distance[source] = 0

        repeat(n - 1) {
            for (node in directedGraph.vertices) {
                if (distance[node] == Int.MAX_VALUE) continue

                for ((adjNode, weight) in directedGraph.neighboursFor(node)) {
                    val d = distance[node] + weight
                    if (d < distance[adjNode]) distance[adjNode] = d
                }
            }
        }

        // Nth relaxation to check -ve cycle
        for (node in directedGraph.vertices) {
            if (distance[node] == Int.MAX_VALUE) continue

            for ((adjNode, weight) in directedGraph.neighboursFor(node)) {
                val d = distance[node] + weight
                if (d < distance[adjNode]) return intArrayOf(-1)
            }
        }

        return distance
    }
}