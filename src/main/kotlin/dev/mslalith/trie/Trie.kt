package dev.mslalith.trie

class Trie {

    companion object {
        private const val ROOT_SYMBOL = '.'
    }

    private val root = TrieNode(value = ROOT_SYMBOL)

    fun insert(word: String) {
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

    fun startsWith(prefix: String): Boolean {
        var currentNode = root
        for (ch in prefix) {
            currentNode = currentNode.children[ch] ?: return false
        }
        return true
    }

    fun findAllThatStartsWith(word: String): List<String> {
        if (word.isEmpty()) return emptyList()

        var i = 0
        var node = root
        while (i < word.length) {
            node = node.children[word[i]] ?: return emptyList()
            i++
        }

        val prefix = word.dropLast(n = 1)
        return findCompleteWordsFrom(node)
            .map { prefix + it }
    }

    private fun findCompleteWordsFrom(node: TrieNode<Char>): List<String> {
        val list = arrayListOf<String>()
        findCompleteWordsFromInternal(node, list, StringBuilder())
        return list
    }

    private fun findCompleteWordsFromInternal(node: TrieNode<Char>, outList: ArrayList<String>, sb: StringBuilder) {
        sb.append(node.value)
        if (node.isEnd) outList.add(sb.toString())
        node.children.forEach { (_, childNode) -> findCompleteWordsFromInternal(childNode, outList, sb) }
        sb.deleteAt(sb.lastIndex)
    }
}
