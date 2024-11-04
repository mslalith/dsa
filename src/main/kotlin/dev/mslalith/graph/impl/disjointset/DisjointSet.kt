package dev.mslalith.graph.impl.disjointset

fun DisjointSet(n: Int): DisjointSet = DisjointSetByRank(n)

interface DisjointSet {
    fun findParent(u: Int): Int
    fun union(u: Int, v: Int): Boolean

    fun componentsCount(): Int
}
