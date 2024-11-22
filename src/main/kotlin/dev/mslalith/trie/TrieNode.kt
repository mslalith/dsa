package dev.mslalith.trie

data class TrieNode<T : Comparable<T>> private constructor(
    private val childrenMap: HashMap<T, TrieNode<T>>,
    private var isEndInternal: Boolean
) {
    constructor() : this(
        childrenMap = hashMapOf(),
        isEndInternal = false
    )

    val children: List<Pair<T, TrieNode<T>>>
        get() = childrenMap.map { it.key to it.value }

    val isEnd: Boolean
        get() = isEndInternal

    fun markAsEnd() { isEndInternal = true }

    operator fun contains(value: T): Boolean = childrenMap.contains(value)

    operator fun get(value: T): TrieNode<T>? = childrenMap[value]
    fun getValue(value: T): TrieNode<T> = childrenMap.getValue(value)

    private fun put(value: T, node: TrieNode<T>): TrieNode<T> {
        childrenMap[value] = node
        return childrenMap.getValue(value)
    }

    fun add(value: T): TrieNode<T> {
        put(value, TrieNode())
        return getValue(value)
    }
}

