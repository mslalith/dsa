package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class WordPattern : TestCaseProblem<Pair<String, String>, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordPattern().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, Boolean>> = arrayOf(
        TestCase(
            input = "abba" to "dog cat cat dog",
            output = true
        ),
        TestCase(
            input = "abba" to "dog cat cat fish",
            output = false
        ),
        TestCase(
            input = "aaaa" to "dog cat cat dog",
            output = false
        ),
        TestCase(
            input = "abba" to "dog dog dog dog",
            output = false
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): Boolean {
        return wordPattern(testCaseInput.first, testCaseInput.second)
    }

    private fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(" ")
        val n = pattern.length

        if (words.size != n) return false

        val seen = hashSetOf<String>()
        val map = hashMapOf<Char, String>()

        for (i in 0 until n) {
            val char = pattern[i]
            val word = words[i]
            if (map.contains(char)) {
                if (word != map.getValue(char)) return false
            } else {
                if (seen.contains(word)) return false
                map[char] = word
                seen.add(word)
            }
        }

        return true
    }
}
