package dev.mslalith.graph.impl

abstract class WeightedGraph(
    private val adjMap: Map<Int, List<WeightedEdge>>
) {
    val vertices: List<Int>
        get() = adjMap.keys.toList()

    val edges: List<Pair<Int, Int>>
        get() = adjMap.flatMap { (node, neighbours) ->
            neighbours.map { node to it.dst }
        }

    fun neighboursFor(vertex: Int): List<WeightedEdge> = adjMap.getOrDefault(vertex, listOf())
}
