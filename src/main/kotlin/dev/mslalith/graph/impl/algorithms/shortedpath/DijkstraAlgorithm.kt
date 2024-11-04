package dev.mslalith.graph.impl.algorithms.shortedpath

import dev.mslalith.graph.impl.WeightedGraph
import java.util.*

/**
 * Finds the shortest path from single source to all nodes
 *
 * Cons:
 * - Dijkstra's Algorithm will not work with -ve weights
 *
 * Time Complexity: O(E * logV)
 *
 */
object Dijkstra : ShortestPath {

    override fun shortestPathFrom(graph: WeightedGraph, source: Int): IntArray {
        val n = graph.vertices.size
        val distance = IntArray(n) { Int.MAX_VALUE }
        distance[source] = 0

        // (distance to vertex)
        val queue = PriorityQueue<Pair<Int, Int>> { a, b ->
            a.first - b.first
        }
        queue.add(0 to source)

        while (queue.isNotEmpty()) {
            val (distanceSoFar, node) = queue.poll()

            for ((adjNode, weight) in graph.neighboursFor(node)) {
                val d = distanceSoFar + weight
                if (d < distance[adjNode]) {
                    distance[adjNode] = d
                    queue.add(d to adjNode)
                }
            }
        }

        return distance
    }
}