package dev.mslalith.graph.impl

abstract class Graph(
    val edges: List<Edge>
) {
    val vertices: List<Int> = buildSet {
        edges.forEach {
            add(it.src)
            add(it.dst)
        }
    }.sorted()

    val isWeighted: Boolean = edges.any { it.weight > 1 }

    private val adjMap = buildMap<Int, List<Pair<Int, Int>>> {
        edges.forEach { (src, dst, wt) ->
            val entry = getOrDefault(src, emptyList()).toMutableList()
            entry.add(dst to wt)
            put(src, entry)
        }
    }

    fun neighboursFor(vertex: Int): List<Pair<Int, Int>> = adjMap.getOrDefault(vertex, listOf())

    fun asAdjacencyList(): Array<List<Pair<Int, Int>>> {
        val maxVertex = vertices.max()
        return Array(maxVertex + 1) { adjMap.getOrDefault(it, listOf()) }
    }
}
