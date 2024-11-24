package dev.mslalith.graph.impl

class UndirectedGraph(
    edges: List<Edge>,
) : Graph(
    edges = edges
) {
    companion object {

        fun fromAdjacencyArray(
            adjArray: Array<IntArray>
        ): UndirectedGraph = buildList {
            for (i in adjArray.indices) {
                val edge = adjArray[i]
                val (src, dst) = edge
                val weight = edge.getOrNull(2) ?: 1

                add(Edge(src, dst, weight))
                add(Edge(dst, src, weight))
            }
        }.toGraph()

        fun fromAdjacencyMatrix(
            matrix: Array<IntArray>
        ): UndirectedGraph = buildList {
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

        private fun List<Edge>.toGraph(): UndirectedGraph = UndirectedGraph(edges = this)
    }

    fun asDirectedGraph(): DirectedGraph = DirectedGraph(edges = edges)
}
