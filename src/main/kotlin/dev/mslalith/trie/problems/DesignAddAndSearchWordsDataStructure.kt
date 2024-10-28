package dev.mslalith.trie.problems

import dev.mslalith.core.problem.SimpleProblem
import dev.mslalith.trie.TrieNode

class DesignAddAndSearchWordsDataStructure : SimpleProblem<Unit>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DesignAddAndSearchWordsDataStructure().runForConsole()
    }

    override fun runWithResult(): Result<Unit> = kotlin.runCatching {
        with(WordDictionary()) {
            addWord("bad")
            addWord("dad")
            addWord("mad")
            check(!search("pad")) // return False
            check(search("bad")) // return True
            check(search(".ad")) // return True
            check(search("b..")) // return True
        }
    }
}

private class WordDictionary {

    private val root = TrieNode('.')

    fun addWord(word: String) {
        if (word.isEmpty()) return

        var currentNode = root
        for (i in word.indices) {
            val ch = word[i]
            if (currentNode.children.contains(ch)) {
                currentNode = currentNode.children.getValue(ch)
            } else {
                val newNode = TrieNode(value = ch, isEnd = i == word.lastIndex)
                currentNode.children[ch] = newNode
                currentNode = newNode
            }
        }
    }

    fun search(word: String): Boolean {
        var currentNode = root
        for (ch in word) {
            currentNode = currentNode.children[ch] ?: return false
        }
        return currentNode.isEnd
    }
}
