package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.string.problems.WordSubsets.WordSubsetsParams
import dev.mslalith.utils.stringFromArray
import kotlin.math.max


class WordSubsets : TestCaseProblem<WordSubsetsParams, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = WordSubsets().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<WordSubsetsParams, List<String>>> = arrayOf(
        TestCase(
            input = WordSubsetsParams(
                words1 = arrayOf("amazon", "apple", "facebook", "google", "leetcode"),
                words2 = arrayOf("e", "o")
            ),
            output = listOf("facebook", "google", "leetcode")
        ),
        TestCase(
            input = WordSubsetsParams(
                words1 = arrayOf("amazon", "apple", "facebook", "google", "leetcode"),
                words2 = arrayOf("l", "e")
            ),
            output = listOf("apple", "google", "leetcode")
        ),
        TestCase(
            input = WordSubsetsParams(
                words1 = arrayOf("amazon", "apple", "facebook", "google", "leetcode"),
                words2 = arrayOf("lo", "eo")
            ),
            output = listOf("google", "leetcode")
        ),
        TestCase(
            input = WordSubsetsParams(
                words1 = arrayOf("amazon", "apple", "facebook", "google", "leetcode"),
                words2 = arrayOf("e", "oo")
            ),
            output = listOf("facebook", "google")
        )
    )

    override fun solve(testCaseInput: WordSubsetsParams): List<String> {
        return wordSubsets(testCaseInput.words1, testCaseInput.words2)
    }

    private fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
        val universalStrings = mutableListOf<String>()
        val word2FreqArray = IntArray(26)

        fun getCharFrequencyArray(word: String): IntArray {
            val charFreq = IntArray(26)
            for (ch in word) charFreq[ch - 'a']++
            return charFreq
        }

        for (word in words2) {
            val charFreq = getCharFrequencyArray(word)
            for (i in word2FreqArray.indices) word2FreqArray[i] = max(word2FreqArray[i], charFreq[i])
        }

        fun isSubset(word: String): Boolean {
            val word1FreqArray = getCharFrequencyArray(word)

            for (i in word1FreqArray.indices) {
                if (word2FreqArray[i] > word1FreqArray[i]) return false
            }

            return true
        }

        for (word1 in words1) {
            if (isSubset(word1)) universalStrings += word1
        }

        return universalStrings
    }

    private fun wordSubsetsBrute(words1: Array<String>, words2: Array<String>): List<String> {
        val universalStrings = mutableListOf<String>()

        val words1Map = Array(words1.size) {
            val map = hashMapOf<Char, Int>()
            for (ch in words1[it]) map[ch] = map.getOrDefault(ch, 0) + 1
            map
        }

        for (i in words1.indices) {
            var matchingCount = 0

            for (word2 in words2) {
                val map = words1Map[i].toMutableMap()
                var presentCount = 0

                for (ch in word2) {
                    val count = map.getOrDefault(ch, 0)
                    if (count <= 0) break

                    presentCount++
                    map[ch] = count - 1
                }

                if (presentCount == word2.length) matchingCount++
            }

            if (matchingCount == words2.size) universalStrings += words1[i]
        }

        return universalStrings
    }

    data class WordSubsetsParams(
        val words1: Array<String>,
        val words2: Array<String>
    ) {
        override fun toString(): String {
            return """
                
                words1: ${stringFromArray(words1)}
                words2: ${stringFromArray(words2)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as WordSubsetsParams

            if (!words1.contentEquals(other.words1)) return false
            if (!words2.contentEquals(other.words2)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = words1.contentHashCode()
            result = 31 * result + words2.contentHashCode()
            return result
        }
    }
}
