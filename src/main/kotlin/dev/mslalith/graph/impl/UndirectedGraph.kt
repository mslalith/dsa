package dev.mslalith.graph.impl

class UndirectedGraph private constructor(
    private val adjMap: Map<Int, List<WeightedEdge>>,
    private val isWeighted: Boolean
) : WeightedGraph(
    adjMap = adjMap
) {

    companion object {

        fun fromAdjacencyArray(
            adjArray: Array<IntArray>
        ): UndirectedGraph {
            val isWeighted = adjArray.any { it.size > 2 }
            return buildMap<Int, MutableList<WeightedEdge>> {
                for (i in adjArray.indices) {
                    val edge = adjArray[i]
                    val src = edge[0]
                    val dst = edge[1]
                    val weight = edge.getOrNull(2) ?: 0

                    put(src, getOrDefault(src, mutableListOf()).apply { add(WeightedEdge(dst, weight)) })
                    put(dst, getOrDefault(dst, mutableListOf()).apply { add(WeightedEdge(src, weight)) })
                }
            }.toGraph(isWeighted = isWeighted)
        }

        fun fromAdjacencyMatrix(
            matrix: Array<IntArray>
        ): UndirectedGraph {
            var isWeighted = false
            return buildMap<Int, MutableList<WeightedEdge>> {
                if (matrix.isEmpty()) return@buildMap

                val m = matrix.size
                val n = matrix[0].size

                for (src in 0 until m) {
                    for (dst in 0 until n) {
                        val weight = matrix[src][dst]
                        if (!isWeighted && weight > 1) isWeighted = true

                        if (weight != 0) put(src, getOrDefault(src, mutableListOf()).apply { add(WeightedEdge(dst, weight)) })
                    }
                }
            }.toGraph(isWeighted = isWeighted)
        }

        private fun Map<Int, MutableList<WeightedEdge>>.toGraph(
            isWeighted: Boolean
        ): UndirectedGraph = UndirectedGraph(
            adjMap = mapValues { it.value.toList() },
            isWeighted = isWeighted
        )
    }
}
