package dev.mslalith.graph.impl.algorithms.detectcycle

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph
import java.util.*

object DetectCycleBFS : DetectCycle {

    /**
     * Time Complexity: O(V + 2E)
     * Space Complexity: O(V)
     */
    override fun hasCycle(undirectedGraph: UndirectedGraph): Boolean {
        val visited = hashSetOf<Int>()

        fun checkForCycle(vertex: Int): Boolean {
            // (node, parent)
            val queue: Queue<Pair<Int, Int>> = LinkedList() //BFS
            queue.add(vertex to -1)

            visited += vertex

            while (!queue.isEmpty()) {
                val (node, parent) = queue.poll()

                for ((adjNode, _) in undirectedGraph.neighboursFor(node)) {
                    if (adjNode !in visited) {
                        queue.add(adjNode to node)
                        visited += adjNode
                    } else if (adjNode != parent) return true
                }
            }

            return false
        }


        val n = undirectedGraph.vertices.size
        for (i in 0 until n) {
            if (i !in visited && checkForCycle(i)) return true
        }

        return false
    }

    override fun hasCycle(directedGraph: DirectedGraph): Boolean {
        throw UnsupportedOperationException("Not supported")
    }
}