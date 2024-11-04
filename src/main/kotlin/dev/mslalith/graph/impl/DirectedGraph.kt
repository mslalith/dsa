package dev.mslalith.graph.impl

import java.util.*

class DirectedGraph private constructor(
    private val adjMap: Map<Int, List<WeightedEdge>>,
    private val isWeighted: Boolean
) : WeightedGraph(
    adjMap = adjMap
) {

    companion object {

        fun fromAdjacencyArray(
            adjArray: Array<IntArray>
        ): DirectedGraph {
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
        ): DirectedGraph {
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
        ): DirectedGraph = DirectedGraph(
            adjMap = mapValues { it.value.toList() },
            isWeighted = isWeighted
        )
    }

    fun stronglyConnectedComponentsOf(node: Int): List<Int> {
        return emptyList()
    }

    fun topologicalSort(): List<Int> {
        val n = adjMap.keys.size
        val visited = hashSetOf<Int>()
        val stack = Stack<Int>()

        fun dfs(node: Int) {
            visited += node

            val neighbours = adjMap.getOrDefault(node, emptyList())
            for ((neighbour, weight) in neighbours) {
                if (neighbour !in visited) dfs(neighbour)
            }

            stack.push(node)
        }

        for (i in adjMap.keys) {
            if (i !in visited) dfs(i)
        }

        return buildList {
            while (stack.isNotEmpty()) add(stack.pop())
        }
    }
}
