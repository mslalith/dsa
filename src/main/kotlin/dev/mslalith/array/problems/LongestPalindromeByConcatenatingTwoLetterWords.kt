package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LongestPalindromeByConcatenatingTwoLetterWords : TestCaseProblem<Array<String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestPalindromeByConcatenatingTwoLetterWords().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, Int>> = arrayOf(
        TestCase(
            input = arrayOf("lc", "cl", "gg"),
            output = 6
        ),
        TestCase(
            input = arrayOf("ab", "ty", "yt", "lc", "cl", "ab"),
            output = 8
        ),
        TestCase(
            input = arrayOf("cc", "ll", "xx"),
            output = 2
        )
    )

    override fun solve(testCaseInput: Array<String>): Int {
        return longestPalindrome(testCaseInput)
    }

    private fun longestPalindrome(words: Array<String>): Int {
        val wordFreq = hashMapOf<String, Int>()
        for (word in words) wordFreq[word] = wordFreq.getOrDefault(word, 0) + 1

        var count = 0
        var alreadyPalindrome = 0

        for ((word, freq) in wordFreq) {
            val reverse = word.reversed()
            if (word == reverse) {
                count += (freq / 2) * 4
                if (freq % 2 == 1) alreadyPalindrome = 1
            } else if (word < reverse && reverse in wordFreq) {
                count += minOf(freq, wordFreq.getValue(reverse)) * 4
            }
        }

        return count + (alreadyPalindrome * 2)
    }
}
