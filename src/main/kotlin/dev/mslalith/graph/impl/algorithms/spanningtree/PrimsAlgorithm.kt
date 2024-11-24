package dev.mslalith.graph.impl.algorithms.spanningtree

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.Edge
import dev.mslalith.graph.impl.Graph
import dev.mslalith.graph.impl.UndirectedGraph
import dev.mslalith.utils.toTriple
import java.util.*

/**
 * Finds the minimum spanning tree
 *
 * Time Complexity: O(E * logE)
 *
 */
object PrimsAlgorithm : MinimumSpanningTree {

    override fun minimumSpanningTree(undirectedGraph: UndirectedGraph): List<Edge> {
        return minimumSpanningTree(graph = undirectedGraph)
    }

    override fun minimumSpanningTree(directedGraph: DirectedGraph): List<Edge> {
        return minimumSpanningTree(graph = directedGraph)
    }

    private fun minimumSpanningTree(graph: Graph): List<Edge> {
        // (weight, node, parent)
        val queue = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first - b.first }
        queue.add(0 to 0 toTriple -1)

        val visited = hashSetOf<Int>()
        val minSpanningTree = mutableListOf<Edge>()

        while (queue.isNotEmpty()) {
            val (weight, node, parent) = queue.poll()
            if (node in visited) continue

            visited += node
            if (parent != -1) minSpanningTree.add(Edge(parent, node, weight))

            for ((adjNode, adjWeight) in graph.neighboursFor(node)) {
                if (adjNode !in visited) queue.add(adjWeight to adjNode toTriple node)
            }
        }

        return minSpanningTree
    }
}
