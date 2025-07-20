package dev.mslalith.trie.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.unOrderEquals
import java.util.*


class DeleteDuplicateFoldersInSystem : TestCaseProblem<List<List<String>>, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DeleteDuplicateFoldersInSystem().runForConsole()
    }

    override fun isTestPassed(actual: List<List<String>>, expected: List<List<String>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun getTestCases(): Array<TestCase<List<List<String>>, List<List<String>>>> = arrayOf(
        TestCase(
            input = listOf(
                listOf("a"),
                listOf("c"),
                listOf("d"),
                listOf("a", "b"),
                listOf("c", "b"),
                listOf("d", "a")
            ),
            output = listOf(
                listOf("d"),
                listOf("d", "a")
            )
        ),
        TestCase(
            input = listOf(
                listOf("a"),
                listOf("c"),
                listOf("a", "b"),
                listOf("c", "b"),
                listOf("a", "b", "x"),
                listOf("a", "b", "x", "y"),
                listOf("w"),
                listOf("w", "y")
            ),
            output = listOf(
                listOf("c"),
                listOf("c", "b"),
                listOf("a"),
                listOf("a", "b")
            )
        )
    )

    override fun solve(testCaseInput: List<List<String>>): List<List<String>> {
        return deleteDuplicateFolder(testCaseInput)
    }

    private fun deleteDuplicateFolder(paths: List<List<String>>): List<List<String>> {
        val root = TrieNode("*")
        val map = hashMapOf<String, TrieNode>()
        val res = mutableListOf<List<String>>()

        fun constructTrie() {
            for (path in paths) {
                var curr = root
                for (ss in path) {
                    curr.children.putIfAbsent(ss, TrieNode(ss))
                    curr = curr.children.getValue(ss)
                }
            }
        }


        fun postOrder(node: TrieNode): String {
            val sb = StringBuffer()
            for (child in node.children.values) {
                sb.append(postOrder(child))
                sb.append(",")
            }

            val key = sb.toString()
            if (sb.isEmpty()) return node.value + key

            if (key !in map) map[key] = node else {
                val orig = map.getValue(key)
                orig.duplicate = true
                node.duplicate = true
            }

            return node.value + "(" + key + ")"
        }

        fun dfs(node: TrieNode, str: MutableList<String>) {
            for (cn in node.children.values) {
                if (cn.duplicate) continue

                str.add(cn.value)
                res += listOf(str.toList())

                dfs(cn, str)
                str.removeLast()
            }
        }

        constructTrie()
        postOrder(root)
        dfs(root, mutableListOf())

        return res
    }

    data class TrieNode(val value: String) {
        val children = TreeMap<String , TrieNode>()
        var duplicate: Boolean = false
    }
}
