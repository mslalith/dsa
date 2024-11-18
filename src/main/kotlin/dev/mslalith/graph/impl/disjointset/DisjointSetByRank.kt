package dev.mslalith.graph.impl.disjointset

class DisjointSetByRank(n: Int): DisjointSet {

    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 0 }

    override fun findParent(u: Int): Int {
        if (parent[u] == u) return u
        // path compression
        parent[u] = findParent(parent[u])
        return parent[u]
    }

    override fun union(u: Int, v: Int): Boolean {
        val uPar = findParent(u)
        val vPar = findParent(v)

        // belongs to same component
        if (uPar == vPar) return false

        // since smaller is being attached to bigger
        // bigger rank is kept the same
        if (rank[uPar] < rank[vPar]) {
            // u is smaller, attach u to v
            parent[uPar] = vPar
        } else if (rank[vPar] < rank[uPar]) {
            // v is smaller, attach v to u
            parent[vPar] = uPar
        } else {
            // both the ranks are same
            // anyone can attach to the other
            parent[uPar] = vPar
            rank[vPar]++
        }
        return true
    }

    override fun componentsCount(): Int = parent.indices.count { parent[it] == it }
}
