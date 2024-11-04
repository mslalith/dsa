package dev.mslalith.graph.impl.algorithms.shortedpath

import dev.mslalith.graph.impl.WeightedGraph
import kotlin.math.min

/**
 * Finds the shortest path from multiple sources to all nodes
 *
 * This only works with Directed Graph.
 * When an Undirected Graph is given, it needs to be converted to a Directed Graph
 *
 * Pros:
 * - helps in detecting -ve cycles
 *
 * Time Complexity: O(V ^ 3)
 *
 */
object FloydWarshall : ShortestPath {

    override fun shortestPathFrom(graph: WeightedGraph, source: Int): IntArray {
        val n = graph.vertices.size
        val distance = Array(n) {
            IntArray(n) { Int.MAX_VALUE }
        }

        for (node in graph.vertices) {
            for ((adjNode, weight) in graph.neighboursFor(node)) {
                distance[node][adjNode] = weight
            }
        }
        for (i in 0 until n) distance[i][i] = 0

        for (k in 0 until n) {
            for (i in 0 until n) {
                if (distance[i][k] == Int.MAX_VALUE) continue
                for (j in 0 until n) {
                    if (distance[k][j] == Int.MAX_VALUE) continue

                    distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])
                }
            }
        }

        // check -ve cycle
        for (i in 0 until n) if (distance[i][i] < 0) return intArrayOf(-1)

        return distance[source]
    }
}