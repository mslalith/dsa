package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.unOrderEquals
import java.util.*

class GroupAnagrams : TestCaseProblem<Array<String>, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GroupAnagrams().runAll()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, List<List<String>>>> = arrayOf(
        TestCase(
            input = arrayOf("eat", "tea", "tan", "ate", "nat", "bat"),
            output = listOf(
                listOf("bat"),
                listOf("nat", "tan"),
                listOf("ate", "eat", "tea")
            )
        ),
        TestCase(
            input = arrayOf(""),
            output = listOf(listOf(""))
        ),
        TestCase(
            input = arrayOf("a"),
            output = listOf(listOf("a"))
        ),
        TestCase(
            input = arrayOf("ac", "d"),
            output = listOf(
                listOf("d"),
                listOf("ac")
            )
        ),
        TestCase(
            input = arrayOf(
                "eat",
                "tea",
                "tan",
                "ate",
                "nat",
                "bat",
                "ac",
                "bd",
                "aac",
                "bbd",
                "aacc",
                "bbdd",
                "acc",
                "bdd"
            ),
            output = listOf(
                listOf("bdd"),
                listOf("bat"),
                listOf("nat", "tan"),
                listOf("ac"),
                listOf("ate", "eat", "tea"),
                listOf("bd"),
                listOf("aac"),
                listOf("bbd"),
                listOf("aacc"),
                listOf("bbdd"),
                listOf("acc")
            )
        )
    )

    override fun isTestPassed(actual: List<List<String>>, expected: List<List<String>>): Boolean {
        return actual.unOrderEquals(expected)
    }

    override fun solve(testCaseInput: Array<String>): List<List<String>> {
        return groupAnagrams(strs = testCaseInput)
    }

    private fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val hashMap = hashMapOf<String, MutableList<String>>()

        strs.forEach { word ->
            val key = compress(word)
            val entry = hashMap.getOrPut(key) { mutableListOf() }
            entry.add(word)
        }

        return hashMap.map { it.value }
    }

    private fun compress(word: String): String = buildString {
        val hashMap = TreeMap<Char, Int>()
        word.forEach {
            hashMap[it] = hashMap.getOrDefault(it, 0) + 1
        }
        hashMap.forEach { (k, v) ->
            append(k)
            append(v)
        }
    }
}
