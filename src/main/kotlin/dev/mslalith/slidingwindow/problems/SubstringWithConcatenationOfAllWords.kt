package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class SubstringWithConcatenationOfAllWords : TestCaseProblem<Pair<String, Array<String>>, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SubstringWithConcatenationOfAllWords().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Array<String>>, List<Int>>> = arrayOf(
        TestCase(
            input = "barfoothefoobarman" to arrayOf("foo", "bar"),
            output = listOf(0, 9)
        ),
        TestCase(
            input = "wordgoodgoodgoodbestword" to arrayOf("word", "good", "best", "word"),
            output = emptyList()
        ),
        TestCase(
            input = "barfoofoobarthefoobarman" to arrayOf("bar", "foo", "the"),
            output = listOf(6, 9, 12)
        ),
        TestCase(
            input = "lingmindraboofooowingdingbarrwingmonkeypoundcake" to arrayOf("fooo", "barr", "wing", "ding", "wing"),
            output = listOf(13)
        )
    )

    override fun solve(testCaseInput: Pair<String, Array<String>>): List<Int> {
        return findSubstring(testCaseInput.first, testCaseInput.second)
    }

    private fun findSubstring(s: String, words: Array<String>): List<Int> {
        val n = s.length
        val totalWords = words.size
        val wordSize = words[0].length

        val wordMap = hashMapOf<String, Int>()
        for (word in words) wordMap[word] = wordMap.getOrDefault(word, 0) + 1

        val result = mutableListOf<Int>()

        for (offset in 0 until wordSize) {
            val windowMap = hashMapOf<String, Int>()

            var left = offset
            var right = offset
            var count = 0

            while (right + wordSize <= n) {
                val word = s.substring(right, right + wordSize)
                right += wordSize

                if (word in wordMap) {
                    windowMap[word] = windowMap.getOrDefault(word, 0) + 1
                    count++

                    while (windowMap.getValue(word) > wordMap.getValue(word)) {
                        val leftWord = s.substring(left, left + wordSize)
                        windowMap[leftWord] = windowMap.getOrDefault(leftWord, 0) - 1
                        count--
                        left += wordSize
                    }

                    if (count == totalWords) result += left
                } else {
                    windowMap.clear()
                    count = 0
                    left = right
                }
            }
        }

        return result
    }
}
