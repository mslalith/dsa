package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class StringMatchingInArray : TestCaseProblem<Array<String>, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = StringMatchingInArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, List<String>>> = arrayOf(
        TestCase(
            input = arrayOf("mass", "as", "hero", "superhero"),
            output = listOf("as", "hero")
        ),
        TestCase(
            input = arrayOf("leetcode", "et", "code"),
            output = listOf("et", "code")
        ),
        TestCase(
            input = arrayOf("blue", "green", "bu"),
            output = listOf()
        )
    )

    override fun solve(testCaseInput: Array<String>): List<String> {
        return stringMatching(testCaseInput)
    }

    private fun stringMatching(words: Array<String>): List<String> {
        val n = words.size
        val matchingWords = mutableListOf<String>()

        for (i in 0 until n) {
            val word = words[i]
            for (ind in 0 until n) {
                if (i == ind) continue
                if (words[ind].contains(word)) {
                    matchingWords += word
                    break
                }
            }
        }

        return matchingWords
    }
}
