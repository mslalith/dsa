package dev.mslalith.trie.problems

import dev.mslalith.core.problem.SimpleProblem
import dev.mslalith.trie.Trie

class ImplementTrie : SimpleProblem<Unit>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ImplementTrie().runForConsole()
    }

    override fun runWithResult(): Result<Unit> = kotlin.runCatching {
        val trie = Trie()
        trie.insert("apple")
        check(trie.search("apple")) // return True
        check(!trie.search("app")) // return False
        check(trie.startsWith("app")) // return True
        trie.insert("app")
        check(trie.search("app")) // return True
    }
}
