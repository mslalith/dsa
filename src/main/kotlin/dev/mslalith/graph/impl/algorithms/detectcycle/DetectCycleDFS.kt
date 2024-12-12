package dev.mslalith.graph.impl.algorithms.detectcycle

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph
import java.util.*

object DetectCycleDFS : DetectCycle {

    /**
     * Time Complexity: O(V + 2E)
     * Space Complexity: O(V)
     */
    override fun hasCycle(undirectedGraph: UndirectedGraph): Boolean {
        val n = undirectedGraph.vertices.size
        val visited = hashSetOf<Int>()

        fun checkForCycle(node: Int, parent: Int): Boolean {
            visited += node

            for ((adjNode, _) in undirectedGraph.neighboursFor(node)) {
                if (adjNode !in visited) {
                    if (checkForCycle(adjNode, node)) return true
                } else if (adjNode != parent) return true
            }

            return false
        }

        for (i in 0 until n) {
            if (i !in visited && checkForCycle(i, -1)) return true
        }

        return false
    }

    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    override fun hasCycle(directedGraph: DirectedGraph): Boolean {
        val n = directedGraph.vertices.size
        val visited = IntArray(n + 1)

        fun checkForCycle(node: Int): Boolean {
            // mark as path visited
            visited[node] = 2

            for ((adjNode, _) in directedGraph.neighboursFor(node)) {
                if (visited[adjNode] == 0) {
                    if (checkForCycle(adjNode)) return true
                } else if (visited[adjNode] == 2) return true
            }

            // unmark as path visited
            // mark as visited
            visited[node] = 1

            return false
        }

        for (i in 0 until n) {
            if (visited[i] == 0 && checkForCycle(i)) return true
        }

        return false
    }
}
