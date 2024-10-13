package src.trie

class Trie {

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
        if (node.value == END_SYMBOL) {
            outList.add(sb.toString())
            return
        }

        sb.append(node.value)
        node.children.forEach { (_, childNode) -> findCompleteWordsFromInternal(childNode, outList, sb) }
        sb.deleteAt(sb.length - 1)
    }
}
