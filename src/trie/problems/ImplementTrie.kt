package src.trie.problems

import src.trie.TrieNode

class ImplementTrie {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ImplementTrie().run()
    }

    private fun run() {
        val trie = Trie()
        trie.insert("apple")
        check(trie.search("apple")) // return True
        check(!trie.search("app")) // return False
        check(trie.startsWith("app")) // return True
        trie.insert("app")
        check(trie.search("app")) // return True
    }
}

private class Trie {

    companion object {
        private const val ROOT_SYMBOL = ' '
        private const val END_SYMBOL = ' '
    }
    private val root = TrieNode(value = ROOT_SYMBOL)

    fun insert(word: String) {
        if (word.isEmpty()) return

        var currentNode = root
        for (ch in word) {
            if (currentNode.children.contains(ch)) {
                currentNode = currentNode.children.getValue(ch)
            } else {
                val newNode = TrieNode(value = ch)
                currentNode.children[ch] = newNode
                currentNode = newNode
            }
        }
        currentNode.children[END_SYMBOL] = TrieNode(value = END_SYMBOL)
    }

    fun search(word: String): Boolean {
        var currentNode = root
        for (ch in word) {
            currentNode = currentNode.children[ch] ?: return false
        }
        return currentNode.children[END_SYMBOL] != null
    }

    fun startsWith(prefix: String): Boolean {
        var currentNode = root
        for (ch in prefix) {
            currentNode = currentNode.children[ch] ?: return false
        }
        return true
    }
}

