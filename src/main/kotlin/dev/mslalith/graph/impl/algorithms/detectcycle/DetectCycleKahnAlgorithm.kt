package dev.mslalith.graph.impl.algorithms.detectcycle

import dev.mslalith.graph.impl.DirectedGraph
import dev.mslalith.graph.impl.UndirectedGraph
import java.util.*

object DetectCycleKahnAlgorithm : DetectCycle {

    override fun hasCycle(undirectedGraph: UndirectedGraph): Boolean {
        throw UnsupportedOperationException("Not supported")
    }

    /**
     * Uses Topological sort to detect cycle
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    override fun hasCycle(directedGraph: DirectedGraph): Boolean {
        val n = directedGraph.vertices.size
        val inDegree = IntArray(n + 1)

        for (vertex in directedGraph.vertices) {
            for ((adjNode, _) in directedGraph.neighboursFor(vertex)) {
                inDegree[adjNode]++
            }
        }

        val queue: Queue<Int> = LinkedList()
        for (vertex in directedGraph.vertices) {
            if (inDegree[vertex] == 0) queue.add(vertex)
        }

        var count = 0

        while (!queue.isEmpty()) {
            val node = queue.poll()
            count++

            for ((adjNode, _) in directedGraph.neighboursFor(node)) {
                inDegree[adjNode]--
                if (inDegree[adjNode] == 0) queue.add(adjNode)
            }
        }

        return count != n
    }
}
