package dev.mslalith.trie

data class TrieNode<T : Comparable<T>>(
    val value: T,
    val children: HashMap<T, TrieNode<T>> = hashMapOf(),
    var isEnd: Boolean = false
)
