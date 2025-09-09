package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.dp.problems.NumberOfPeopleAwareOfASecret.NumberOfPeopleAwareOfASecretParams

class NumberOfPeopleAwareOfASecret : TestCaseProblem<NumberOfPeopleAwareOfASecretParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NumberOfPeopleAwareOfASecret().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<NumberOfPeopleAwareOfASecretParams, Int>> = arrayOf(
        TestCase(
            input = NumberOfPeopleAwareOfASecretParams(
                n = 6,
                delay = 2,
                forget = 4
            ),
            output = 5
        ),
        TestCase(
            input = NumberOfPeopleAwareOfASecretParams(
                n = 4,
                delay = 1,
                forget = 3
            ),
            output = 6
        )
    )

    override fun solve(testCaseInput: NumberOfPeopleAwareOfASecretParams): Int {
        return peopleAwareOfSecret(testCaseInput.n, testCaseInput.delay, testCaseInput.forget)
    }

    private fun peopleAwareOfSecret(n: Int, delay: Int, forget: Int): Int {
        if (n == 1) return 1

        val mod = 1_000_000_000 + 7
        val dp = LongArray(n + 1)
        dp[1] = 1

        var share = 0L

        for (i in 2..n) {
            val enter = i - delay
            val exit = i - forget

            if (enter >= 1) share = (share + dp[enter]) % mod
            if (exit >= 1) share = (share - dp[exit] + mod) % mod

            dp[i] = share
        }

        var ans = 0L
        val start = maxOf(1, n - forget + 1)
        for (i in start..n) ans = (ans + dp[i]) % mod

        return ans.toInt()
    }

    data class NumberOfPeopleAwareOfASecretParams(
        val n: Int,
        val delay: Int,
        val forget: Int
    ) {
        override fun toString(): String {
            return """
                
                n: $n
                delay: $delay
                forget: $forget
            """.trimIndent()
        }
    }
}
