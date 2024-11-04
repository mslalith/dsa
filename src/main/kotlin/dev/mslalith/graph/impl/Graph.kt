package dev.mslalith.graph.impl

abstract class Graph(
    private val adjMap: Map<Int, List<Edge>>
) {
    val vertices: List<Int>
        get() = adjMap.keys.toList()

    val edges: List<Pair<Int, Int>>
        get() = adjMap.flatMap { (node, neighbours) ->
            neighbours.map { node to it.dst }
        }
}
