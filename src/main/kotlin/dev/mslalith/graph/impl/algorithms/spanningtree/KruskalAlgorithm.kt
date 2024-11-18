package dev.mslalith.graph.impl.algorithms.spanningtree

import dev.mslalith.graph.impl.WeightedEdge
import dev.mslalith.graph.impl.disjointset.DisjointSet
import dev.mslalith.utils.toTriple
import java.util.*

/**
 * Finds the minimum spanning tree
 *
 * Time Complexity: O(E * logE)
 *
 */
object KruskalAlgorithm : MinimumSpanningTree {
    override fun minimumSpanningTree(adjList: Array<List<WeightedEdge>>): Array<List<WeightedEdge>> {
        // (weight, src, dst)
        val edges = buildList {
            adjList.forEachIndexed { node, edges ->
                edges.forEach { add(it.weight to node toTriple it.dst) }
            }
        }.sortedBy { it.first }

        val ds = DisjointSet(adjList.size)
        val minSpanningTree = Array(adjList.size) { mutableListOf<WeightedEdge>() }

        for ((weight, src, dst) in edges) {
            if (ds.findParent(src) == ds.findParent(dst)) continue
            ds.union(src, dst)
            minSpanningTree[src].add(WeightedEdge(dst, weight))
        }

        return minSpanningTree.map { it.toList() }.toTypedArray()
    }
}
