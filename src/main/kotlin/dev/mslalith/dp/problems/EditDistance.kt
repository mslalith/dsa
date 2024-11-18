package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min


class EditDistance : TestCaseProblem<Pair<String, String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EditDistance().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, String>, Int>> = arrayOf(
        TestCase(
            input = "horse" to "ros",
            output = 3
        ),
        TestCase(
            input = "intention" to "execution",
            output = 5
        ),
        TestCase(
            input = "" to "",
            output = 0
        ),
        TestCase(
            input = "abc" to "defghi",
            output = 6
        ),
        TestCase(
            input = "leet" to "code",
            output = 4
        ),
        TestCase(
            input = "azertyqsdfghqwerty" to "azertyjklmqwerty",
            output = 6
        ),
        TestCase(
            input = "aaaaaaaaaaaaaaaaaaaaaa" to "aaaaaaaaaaaaaav",
            output = 8
        ),
        TestCase(
            input = "aaayuio" to "hjklaaa",
            output = 7
        )
    )

    override fun solve(testCaseInput: Pair<String, String>): Int {
        return minDistance(testCaseInput.first, testCaseInput.second)
    }

    private fun minDistance(word1: String, word2: String): Int {
        if (word1.isEmpty() && word2.isEmpty()) return 0
        if (word1.isEmpty()) return word2.length
        if (word2.isEmpty()) return word1.length
        if (word1 == word2) return 0

        val m = word1.length
        val n = word2.length

        val curr = IntArray(n + 1)
        var prev = IntArray(n + 1)
        for (j in 0..n) prev[j] = j

        for (i in 1..m) {
            curr[0] = i
            for (j in 1..n) {
                curr[j] = when {
                    word1[i - 1] == word2[j - 1] -> prev[j - 1]
                    else -> 1 + min(curr[j - 1], min(prev[j], prev[j - 1]))
                }
            }
            prev = curr.clone()
        }

        return prev[n]
    }

    private fun minDistanceDp(word1: String, word2: String): Int {
        if (word1.isEmpty() && word2.isEmpty()) return 0
        if (word1.isEmpty()) return word2.length
        if (word2.isEmpty()) return word1.length
        if (word1 == word2) return 0

        val m = word1.length
        val n = word2.length

        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) dp[i][0] = i
        for (j in 0..n) dp[0][j] = j

        for (i in 1..m) {
            for (j in 1..n) {
                dp[i][j] = when {
                    word1[i - 1] == word2[j - 1] -> dp[i - 1][j - 1]
                    else -> 1 + min(dp[i][j - 1], min(dp[i - 1][j], dp[i - 1][j - 1]))
                }
            }
        }

        return dp[m][n]
    }

    private fun minDistanceRecursive(word1: String, word2: String): Int {
        if (word1.isEmpty() && word2.isEmpty()) return 0
        if (word1.isEmpty()) return word2.length
        if (word2.isEmpty()) return word1.length
        if (word1 == word2) return 0

        val m = word1.length
        val n = word2.length

        val dp = Array(m) { IntArray(n) { -1 } }

        fun findMinDistance(i: Int, j: Int): Int {
            if (i < 0) return j + 1
            if (j < 0) return i + 1
            if (dp[i][j] != -1) return dp[i][j]

            dp[i][j] = when {
                word1[i] == word2[j] -> findMinDistance(i - 1, j - 1)
                else -> 1 + min(findMinDistance(i, j - 1), min(findMinDistance(i - 1, j), findMinDistance(i - 1, j - 1)))
            }

            return dp[i][j]
        }

        return findMinDistance(m - 1, n - 1)
    }
}
