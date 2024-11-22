package dev.mslalith.trie

class Trie {

    private val root = TrieNode<Char>()

    fun insert(word: String) {
        if (word.isEmpty()) return

        var currentNode = root

        for (ch in word) {
            if (ch !in currentNode) currentNode.add(ch)
            currentNode = currentNode.getValue(ch)
        }

        currentNode.markAsEnd()
    }

    fun search(word: String): Boolean {
        var currentNode = root

        for (ch in word) {
            currentNode = currentNode[ch] ?: return false
        }

        return currentNode.isEnd
    }

    fun startsWith(prefix: String): Boolean {
        var currentNode = root

        for (ch in prefix) {
            currentNode = currentNode[ch] ?: return false
        }

        return true
    }

    fun findAllThatStartsWith(word: String): List<String> {
        if (word.isEmpty()) return emptyList()

        var i = 0
        var node = root

        while (i < word.length) {
            node = node[word[i]] ?: return emptyList()
            i++
        }

        val prefix = word.dropLast(n = 1)
        return findCompleteWordsFrom(word[i - 1], node)
            .map { prefix + it }
    }

    private fun findCompleteWordsFrom(char: Char, node: TrieNode<Char>): List<String> {
        val list = arrayListOf<String>()
        findCompleteWordsFromInternal(char, node, list, StringBuilder())
        return list
    }

    private fun findCompleteWordsFromInternal(char: Char, node: TrieNode<Char>, outList: ArrayList<String>, sb: StringBuilder) {
        sb.append(char)
        if (node.isEnd) outList.add(sb.toString())
        node.children.forEach { (char, nextNode) -> findCompleteWordsFromInternal(char, nextNode, outList, sb) }
        sb.deleteAt(sb.lastIndex)
    }
}
