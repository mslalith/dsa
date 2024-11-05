package dev.mslalith.graph.impl

abstract class WeightedGraph(
    private val adjMap: Map<Int, List<WeightedEdge>>
) {
    val vertices: List<Int>
        get() = adjMap.keys.toList()

    val allEdges: List<Pair<Int, WeightedEdge>>
        get() = adjMap.flatMap { (node, neighbours) ->
            neighbours.map { node to it }
        }

    fun asAdjacencyList(): Array<List<WeightedEdge>> {
        val maxVertex = adjMap.keys.max()
        return Array(maxVertex + 1) { adjMap.getOrDefault(it, listOf()) }
    }

    fun neighboursFor(vertex: Int): List<WeightedEdge> = adjMap.getOrDefault(vertex, listOf())
}
