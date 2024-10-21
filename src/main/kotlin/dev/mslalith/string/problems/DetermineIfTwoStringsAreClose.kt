package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class DetermineIfTwoStringsAreClose : Problem<Pair<String, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DetermineIfTwoStringsAreClose().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, String>, Boolean>> = arrayOf(
        TestCase(
            input = "abc" to "bca",
            output = true
        ),
        TestCase(
            input = "a" to "aa",
            output = false
        ),
        TestCase(
            input = "cabbba" to "abbccc",
            output = true
        ),
        TestCase(
            input = "cabbba" to "aabbss",
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<String, String>): Boolean {
        return closeStrings(testCaseInput.first, testCaseInput.second)
    }

    private fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        val a1 = IntArray(26) { 0 }
        val a2 = IntArray(26) { 0 }

        word1.forEach { a1[it - 'a']++ }
        word2.forEach { a2[it - 'a']++ }

        for (i in a1.indices) {
            val x = a1[i]
            val y = a2[i]

            if (x == 0 && y != 0) return false
            if (x != 0 && y == 0) return false
        }

        a1.sort()
        a2.sort()
        for (i in a1.indices) {
            if (a1[i] != a2[i]) return false
        }

        return true
    }
}
