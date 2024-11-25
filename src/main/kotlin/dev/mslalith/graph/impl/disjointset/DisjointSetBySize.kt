package dev.mslalith.graph.impl.disjointset

class DisjointSetBySize(n: Int): DisjointSet {

    private val parent = IntArray(n) { it }
    private val size = IntArray(n) { 1 }

    override fun findParent(u: Int): Int {
        if (parent[u] == u) return u
        parent[u] = findParent(parent[u])
        return parent[u]
    }

    override fun union(u: Int, v: Int): Boolean {
        val uPar = findParent(u)
        val vPar = findParent(v)

        // belongs to same component
        if (uPar == vPar) return false

        if (size[uPar] < size[vPar]) {
            // u is smaller, attach u to v
            parent[uPar] = vPar
            size[vPar] += size[uPar]
        } else {
            // v is smaller, attach v to u
            parent[vPar] = uPar
            size[uPar] += size[vPar]
        }
        return true
    }

    override fun componentsCount(): Int = parent.indices.count { parent[it] == it }

    fun uniqueParents(): List<Int> = parent.filterIndexed { index, value -> parent[value] == index }

    fun nodesCountFor(node: Int) = size[parent[node]]
}
