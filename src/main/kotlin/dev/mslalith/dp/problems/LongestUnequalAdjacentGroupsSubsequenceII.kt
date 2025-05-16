package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LongestUnequalAdjacentGroupsSubsequenceII : TestCaseProblem<Pair<Array<String>, IntArray>, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestUnequalAdjacentGroupsSubsequenceII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<String>, IntArray>, List<String>>> = arrayOf(
        TestCase(
            input = arrayOf("bab", "dab", "cab") to intArrayOf(1, 2, 2),
            output = listOf("bab", "cab"),
            otherAcceptableOutputs = listOf(
                listOf("bab", "dab")
            ),
        ),
        TestCase(
            input = arrayOf("a", "b", "c", "d") to intArrayOf(1, 2, 3, 4),
            output = listOf("a", "b", "c", "d")
        )
    )

    override fun solve(testCaseInput: Pair<Array<String>, IntArray>): List<String> {
        return getWordsInLongestSubsequence(testCaseInput.first, testCaseInput.second)
    }

    private fun getWordsInLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
        val n = groups.size
        val dp = IntArray(n) { 1 }
        val parent = IntArray(n) { -1 }

        var maxiIndex = -1

        fun differByOneChar(a: String, b: String): Boolean {
            if (a.length != b.length) return false
            return (0 until a.length).count { a[it] != b[it] } == 1
        }

        for (i in 0 until n) {
            for (j in 0 until i) {
                if (groups[i] != groups[j] && differByOneChar(words[i], words[j])) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1
                        parent[i] = j
                    }
                }
            }
            if (maxiIndex == -1 || dp[i] > dp[maxiIndex]) maxiIndex = i
        }

        val result = mutableListOf<String>()

        var i = maxiIndex
        while (i != -1) {
            result.add(0, words[i])
            i = parent[i]
        }

        return result
    }
}