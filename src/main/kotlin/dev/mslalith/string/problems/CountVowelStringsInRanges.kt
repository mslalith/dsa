package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.string.problems.CountVowelStringsInRanges.CountVowelStringsInRangesParams
import dev.mslalith.utils.stringFromArray

class CountVowelStringsInRanges : TestCaseProblem<CountVowelStringsInRangesParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountVowelStringsInRanges().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CountVowelStringsInRangesParams, IntArray>> = arrayOf(
        TestCase(
            input = CountVowelStringsInRangesParams(
                words = arrayOf("aba", "bcb", "ece", "aa", "e"),
                queries = arrayOf(
                    intArrayOf(0, 2),
                    intArrayOf(1, 4),
                    intArrayOf(1, 1)
                )
            ),
            output = intArrayOf(2, 3, 0)
        ),
        TestCase(
            input = CountVowelStringsInRangesParams(
                words = arrayOf("a", "e", "i"),
                queries = arrayOf(
                    intArrayOf(0, 2),
                    intArrayOf(0, 1),
                    intArrayOf(2, 2)
                )
            ),
            output = intArrayOf(3, 2, 1)
        )
    )

    override fun solve(testCaseInput: CountVowelStringsInRangesParams): IntArray {
        return vowelStrings(testCaseInput.words, testCaseInput.queries)
    }

    private fun vowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {

        fun isVowel(ch: Char): Boolean = when (ch) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }

        val prefixMatch = IntArray(words.size + 1)

        for (i in words.indices) {
            val word = words[i]
            val matches = isVowel(word.first()) && isVowel(word.last())
            prefixMatch[i + 1] = prefixMatch[i] + if (matches) 1 else 0
        }

        val result = IntArray(queries.size)

        for (i in queries.indices) {
            val (start, end) = queries[i]
            result[i] = prefixMatch[end + 1] - prefixMatch[start]
        }

        return result
    }

    data class CountVowelStringsInRangesParams(
        val words: Array<String>,
        val queries: Array<IntArray>
    ) {
        override fun toString(): String {
            return """
                
                words: ${stringFromArray(words)}
                queries: ${stringFromArray(queries)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CountVowelStringsInRangesParams

            if (!words.contentEquals(other.words)) return false
            if (!queries.contentDeepEquals(other.queries)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = words.contentHashCode()
            result = 31 * result + queries.contentDeepHashCode()
            return result
        }
    }
}
