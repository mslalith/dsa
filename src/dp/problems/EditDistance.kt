package src.dp.problems

import src.core.Problem
import src.core.TestCase

class EditDistance : Problem<Pair<String, String>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EditDistance().run()
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
        val dp = Array(n + 1) {
            IntArray(m + 1) { 0 }
        }
        for (i in 0..m) dp[0][i] = i
        for (i in 0..n) dp[i][0] = i

        for (i in 1..n) {
            for (j in 1..m) {
                if (word1[j - 1] == word2[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + minOf(
                        dp[i - 1][j],
                        dp[i - 1][j - 1],
                        dp[i][j - 1],
                    )
                }
            }
        }
        return dp[n][m]
    }
}
