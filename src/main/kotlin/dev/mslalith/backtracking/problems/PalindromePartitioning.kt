package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class PalindromePartitioning : TestCaseProblem<String, List<List<String>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PalindromePartitioning().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, List<List<String>>>> = arrayOf(
        TestCase(
            input = "aab",
            output = listOf(
                listOf("a", "a", "b"),
                listOf("aa", "b")
            )
        ),
        TestCase(
            input = "a",
            output = listOf(
                listOf("a")
            )
        ),
        TestCase(
            input = "cdd",
            output = listOf(
                listOf("c", "d", "d"),
                listOf("c", "dd")
            )
        )
    )

    override fun solve(testCaseInput: String): List<List<String>> {
        return partition(testCaseInput)
    }

    private fun partition(s: String): List<List<String>> {
        val n = s.length
        val result = mutableListOf<List<String>>()
        val currentList = mutableListOf<String>()

        fun isPalindrome(str: String): Boolean {
            var left = 0
            var right = str.length - 1

            while (left < right) {
                if (str[left] != str[right]) return false
                left++
                right--
            }

            return true
        }

        fun buildPartitions(start: Int) {
            if (start == n) {
                result += currentList.toList()
                return
            }

            for (end in (start + 1)..n) {
                val str = s.substring(start, end)
                if (isPalindrome(str)) {
                    currentList += str
                    buildPartitions(end)
                    currentList.removeLast()
                }
            }
        }

        buildPartitions(0)
        return result
    }
}
