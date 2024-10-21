package dev.mslalith.trie.problems

import dev.mslalith.core.DirectProblem
import dev.mslalith.trie.Trie

class ImplementTrie : DirectProblem() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ImplementTrie().run()
    }

    override fun runProblem() {
        val trie = Trie()
        trie.insert("apple")
        check(trie.search("apple")) // return True
        check(!trie.search("app")) // return False
        check(trie.startsWith("app")) // return True
        trie.insert("app")
        check(trie.search("app")) // return True
    }
}
