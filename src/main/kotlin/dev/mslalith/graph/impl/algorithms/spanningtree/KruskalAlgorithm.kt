package dev.mslalith.graph.impl.algorithms.spanningtree

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.Edge
import dev.mslalith.graph.impl.Graph
import dev.mslalith.graph.impl.UndirectedGraph
import dev.mslalith.graph.impl.disjointset.DisjointSet

/**
 * Finds the minimum spanning tree
 *
 * Time Complexity: O(E * logE) + O(E * 4α * 2)
 *
 */
object KruskalAlgorithm : MinimumSpanningTree {

    override fun minimumSpanningTree(undirectedGraph: UndirectedGraph): List<Edge> {
        return minimumSpanningTree(graph = undirectedGraph)
    }

    override fun minimumSpanningTree(directedGraph: DirectedGraph): List<Edge> {
        return minimumSpanningTree(graph = directedGraph)
    }

    private fun minimumSpanningTree(graph: Graph): List<Edge> {
        val edges = graph.edges.sortedBy { it.weight }

        val ds = DisjointSet(graph.vertices.size)
        val minSpanningTree = mutableListOf<Edge>()

        for ((src, dst, weight) in edges) {
            if (ds.findParent(src) == ds.findParent(dst)) continue
            ds.union(src, dst)
            minSpanningTree.add(Edge(src, dst, weight))
        }

        return minSpanningTree
    }
}
