package dev.mslalith.trie.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.trie.Trie

class SearchSuggestionsSystem : TestCaseProblem<Pair<Array<String>, String>, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SearchSuggestionsSystem().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<String>, String>, List<List<String>>>> = arrayOf(
        TestCase(
            input = arrayOf("mobile", "mouse", "moneypot", "monitor", "mousepad") to "mouse",
            output = listOf(
                listOf("mobile", "moneypot", "monitor"),
                listOf("mobile", "moneypot", "monitor"),
                listOf("mouse", "mousepad"),
                listOf("mouse", "mousepad"),
                listOf("mouse", "mousepad")
            )
        ),
        TestCase(
            input = arrayOf("havana") to "havana",
            output = listOf(
                listOf("havana"),
                listOf("havana"),
                listOf("havana"),
                listOf("havana"),
                listOf("havana"),
                listOf("havana")
            )
        )
    )

    override fun solve(testCaseInput: Pair<Array<String>, String>): List<List<String>> {
        return suggestedProducts(testCaseInput.first, testCaseInput.second)
    }

    private fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        val trie = Trie()
        products.forEach { trie.insert(it) }

        return buildList {
            var query = ""
            for (i in searchWord.indices) {
                query += searchWord[i]
                val words = trie.findAllThatStartsWith(query)
                add(words.sorted().take(3))
            }
        }
    }
}
