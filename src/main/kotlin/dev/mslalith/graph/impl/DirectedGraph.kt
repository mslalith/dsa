package dev.mslalith.graph.impl

import java.util.*

class DirectedGraph(
    edges: List<Edge>
) : Graph(
    edges = edges
) {

    companion object {

        fun fromAdjacencyArray(
            adjArray: Array<IntArray>
        ): DirectedGraph = buildList {
            for (i in adjArray.indices) {
                val edge = adjArray[i]
                val src = edge[0]
                val dst = edge[1]
                val weight = edge.getOrNull(2) ?: 1

                add(Edge(src, dst, weight))
            }
        }.toGraph()

        fun fromAdjacencyMatrix(
            matrix: Array<IntArray>
        ): DirectedGraph = buildList {
            if (matrix.isEmpty()) return@buildList

            val m = matrix.size
            val n = matrix[0].size

            for (src in 0 until m) {
                for (dst in 0 until n) {
                    val weight = matrix[src][dst]
                    if (weight != 0) add(Edge(src, dst, weight))
                }
            }
        }.toGraph()

        private fun List<Edge>.toGraph(): DirectedGraph = DirectedGraph(edges = this)
    }

    fun topologicalSort(): List<Int> {
        val visited = hashSetOf<Int>()
        val stack = Stack<Int>()

        fun dfs(node: Int) {
            visited += node

            for ((neighbour, _) in neighboursFor(node)) {
                if (neighbour !in visited) dfs(neighbour)
            }

            stack.push(node)
        }

        for (i in vertices) {
            if (i !in visited) dfs(i)
        }

        return buildList {
            while (stack.isNotEmpty()) add(stack.pop())
        }
    }
}
