package dev.mslalith.graph.impl.algorithms.spanningtree

import dev.mslalith.graph.impl.WeightedEdge
import dev.mslalith.utils.toTriple
import java.util.*

/**
 * Finds the minimum spanning tree
 *
 * Time Complexity: O(E * logE)
 *
 */
object PrimsAlgorithm : MinimumSpanningTree {
    override fun minimumSpanningTree(adjList: Array<List<WeightedEdge>>): Array<List<WeightedEdge>> {
        // (weight to node to parent)
        val queue = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first - b.first }
        queue.add(0 to 0 toTriple -1)

        val visited = hashSetOf<Int>()
        val minSpanningTree = Array(adjList.size) { mutableListOf<WeightedEdge>() }

        while (queue.isNotEmpty()) {
            val (weight, node, parent) = queue.poll()
            if (node in visited) continue

            visited += node
            if (parent != -1) minSpanningTree[parent].add(WeightedEdge(node, weight))

            for ((adjNode, adjWeight) in adjList[node]) {
                if (adjNode !in visited) queue.add(adjWeight to adjNode toTriple node)
            }
        }

        return minSpanningTree.map { it.toList() }.toTypedArray()
    }
}
