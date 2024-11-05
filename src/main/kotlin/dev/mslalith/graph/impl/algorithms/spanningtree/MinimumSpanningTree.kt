package dev.mslalith.graph.impl.algorithms.spanningtree

import dev.mslalith.graph.impl.WeightedEdge

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
    fun minimumSpanningTree(adjList: Array<List<WeightedEdge>>): Array<List<WeightedEdge>>

    fun minimumSpanningTreeSum(adjList: Array<List<WeightedEdge>>) = minimumSpanningTree(adjList).sumOf { edges ->
        edges.sumOf { it.weight }
    }
}
