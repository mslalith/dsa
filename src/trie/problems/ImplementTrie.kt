package src.trie.problems

import src.trie.Trie

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
