package dev.mslalith.graph.impl.algorithms.spanningtree

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.Edge
import dev.mslalith.graph.impl.UndirectedGraph

/**
 *
 * Spanning Tree needs to have N nodes and (N - 1) edges
 * and all nodes are reachable from each other.
 *
 * The minimum path sum is called to be Minimum Spanning Tree (MST)
 *
 * Properties:
 * - Should not be disconnected
 * - Should be acyclic
 * - Can have multiple spanning trees
 *
 */
interface MinimumSpanningTree {
    fun minimumSpanningTree(undirectedGraph: UndirectedGraph): List<Edge>
    fun minimumSpanningTree(directedGraph: DirectedGraph): List<Edge>

    fun minimumSpanningTreeSum(directedGraph: DirectedGraph) = minimumSpanningTree(directedGraph = directedGraph).sumOf { it.weight }
    fun minimumSpanningTreeSum(undirectedGraph: UndirectedGraph) = minimumSpanningTree(undirectedGraph = undirectedGraph).sumOf { it.weight }
}
