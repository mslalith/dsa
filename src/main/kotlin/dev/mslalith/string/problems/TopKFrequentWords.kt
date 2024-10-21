package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray
import java.util.*

class TopKFrequentWords : TestCaseProblem<TopKFrequentWordsParams, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TopKFrequentWords().runAll()
    }

    override fun getTestCases(): Array<TestCase<TopKFrequentWordsParams, List<String>>> = arrayOf(
        TestCase(
            input = TopKFrequentWordsParams(
                words = arrayOf("i", "love", "leetcode", "i", "love", "coding"),
                k = 2
            ),
            output = listOf("i", "love")
        ),
        TestCase(
            input = TopKFrequentWordsParams(
                words = arrayOf("i", "love", "leetcode", "i", "love", "coding"),
                k = 1
            ),
            output = listOf("i")
        ),
        TestCase(
            input = TopKFrequentWordsParams(
                words = arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"),
                k = 4
            ),
            output = listOf("the", "is", "sunny", "day")
        )
    )

    override fun solve(testCaseInput: TopKFrequentWordsParams): List<String> {
        return topKFrequent(testCaseInput.words, testCaseInput.k)
    }

    private fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val queue = PriorityQueue<Word>(Collections.reverseOrder())
        val map = hashMapOf<String, Int>()
        words.forEach { map[it] = map.getOrDefault(it, 0) + 1 }
        map.forEach { queue.add(Word(it.key, it.value)) }

        val list = mutableListOf<String>()
        repeat(k) {
            list.add(queue.poll().text)
        }

        return list
    }

    inner class Word(
        val text: String,
        val count: Int
    ) : Comparable<Word> {
        override fun compareTo(other: Word): Int {
            return if (count != other.count) count.compareTo(other.count) else other.text.compareTo(text)
        }

        override fun toString(): String {
            return "($count) $text"
        }
    }
}

data class TopKFrequentWordsParams(
    val words: Array<String>,
    val k: Int
) {
    override fun toString(): String {
        return """
            
            words: ${stringFromArray(words)}
            k: $k
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TopKFrequentWordsParams

        if (!words.contentEquals(other.words)) return false
        if (k != other.k) return false

        return true
    }

    override fun hashCode(): Int {
        var result = words.contentHashCode()
        result = 31 * result + k
        return result
    }
}