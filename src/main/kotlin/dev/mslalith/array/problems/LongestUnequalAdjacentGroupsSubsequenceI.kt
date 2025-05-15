package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LongestUnequalAdjacentGroupsSubsequenceI : TestCaseProblem<Pair<Array<String>, IntArray>, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestUnequalAdjacentGroupsSubsequenceI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<String>, IntArray>, List<String>>> = arrayOf(
        TestCase(
            input = arrayOf("e", "a", "b") to intArrayOf(0, 0, 1),
            output = listOf("e", "b")
        ),
        TestCase(
            input = arrayOf("a", "b", "c", "d") to intArrayOf(1, 0, 1, 1),
            output = listOf("a", "b", "c")
        )
    )

    override fun solve(testCaseInput: Pair<Array<String>, IntArray>): List<String> {
        return getLongestSubsequence(testCaseInput.first, testCaseInput.second)
    }

    private fun getLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
        val result = mutableListOf<String>()
        var last = -1

        for (i in groups.indices) {
            if(groups[i] != last) {
                last = groups[i]
                result += words[i]
            }
        }

        return result
    }
}
