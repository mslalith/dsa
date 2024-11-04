package dev.mslalith.graph.impl

data class Edge(
    val src: Int,
    val dst: Int
)

data class WeightedEdge(
    val dst: Int,
    val weight: Int
)
