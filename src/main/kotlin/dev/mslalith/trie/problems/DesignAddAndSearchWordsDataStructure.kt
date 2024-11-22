package dev.mslalith.trie.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.trie.TrieNode
import dev.mslalith.trie.problems.DesignAddAndSearchWordsDataStructureType.Add
import dev.mslalith.trie.problems.DesignAddAndSearchWordsDataStructureType.Search

class DesignAddAndSearchWordsDataStructure : TestCaseProblem<List<DesignAddAndSearchWordsDataStructureType>, List<Boolean?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DesignAddAndSearchWordsDataStructure().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<DesignAddAndSearchWordsDataStructureType>, List<Boolean?>>> = arrayOf(
        TestCase(
            input = listOf(
                Add("bad"),
                Add("dad"),
                Add("mad"),
                Search("pad"),
                Search("bad"),
                Search(".ad"),
                Search("b..")
            ),
            output = listOf(null, null, null, false, true, true, true)
        ),
        TestCase(
            input = listOf(
                Add("bad"),
                Add("dad"),
                Add("mad"),
                Search("pad"),
                Search("bad"),
                Search(".ad"),
                Search("b.."),
                Search("ba")
            ),
            output = listOf(null, null, null, false, true, true, true, false)
        ),
        TestCase(
            input = listOf(
                Add("a"),
                Add("a"),
                Search("."),
                Search("a"),
                Search("aa"),
                Search("a"),
                Search(".a"),
                Search("a.")
            ),
            output = listOf(null, null, true, true, false, true, false, false)
        )
    )

    override fun solve(testCaseInput: List<DesignAddAndSearchWordsDataStructureType>): List<Boolean?> = buildList {
        val dictionary = WordDictionary()
        testCaseInput.forEach {
            when (it) {
                is Search -> add(dictionary.search(it.word))
                is Add -> {
                    dictionary.addWord(it.word)
                    add(null)
                }
            }
        }
    }
}

sealed interface DesignAddAndSearchWordsDataStructureType {
    data class Add(val word: String) : DesignAddAndSearchWordsDataStructureType
    data class Search(val word: String) : DesignAddAndSearchWordsDataStructureType
}

private class WordDictionary {

    private val root = TrieNode<Char>()

    fun addWord(word: String) {
        if (word.isEmpty()) return

        var currentNode = root

        for (ch in word) {
            if (ch !in currentNode) currentNode.add(ch)
            currentNode = currentNode.getValue(ch)
        }

        currentNode.markAsEnd()
    }

    fun search(word: String): Boolean {
        return searchFrom(root, word, 0)
    }

    private fun searchFrom(node: TrieNode<Char>, word: String, index: Int): Boolean {
        var currentNode = node

        for (i in index until word.length) {
            when (val ch = word[i]) {
                '.' -> return currentNode.children.any { (_, nextNode) -> searchFrom(nextNode, word, i + 1) }
                else -> currentNode = currentNode[ch] ?: return false
            }
        }

        return currentNode.isEnd
    }
}
