package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class LongestSubsequenceRepeatedKTimes : TestCaseProblem<Pair<String, Int>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestSubsequenceRepeatedKTimes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, String>> = arrayOf(
        TestCase(
            input = "letsleetcode" to 2,
            output = "let"
        ),
        TestCase(
            input = "bb" to 2,
            output = "b"
        ),
        TestCase(
            input = "ab" to 2,
            output = ""
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): String {
        return longestSubsequenceRepeatedK(testCaseInput.first, testCaseInput.second)
    }

    private fun longestSubsequenceRepeatedK(s: String, k: Int): String {

        fun isKRepeatedSubsequence(str: String): Boolean {
            val target = str.repeat(k)
            var j = 0

            for (ch in s) {
                if (j < target.length && ch == target[j]) j++
            }

            return j == target.length
        }

        var answer = ""

        val queue: Queue<String> = LinkedList()
        queue.add("")

        while (!queue.isEmpty()) {
            val curr: String = queue.poll()
            for (ch in 'a'..'z') {
                val n = curr + ch
                if (isKRepeatedSubsequence(n)) {
                    answer = n
                    queue.add(n)
                }
            }
        }

        return answer
    }
}
