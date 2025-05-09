package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountNumberOfBalancedPermutations : TestCaseProblem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountNumberOfBalancedPermutations().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "123",
            output = 2
        ),
        TestCase(
            input = "112",
            output = 1
        ),
        TestCase(
            input = "12345",
            output = 0
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return countBalancedPermutations(testCaseInput)
    }

    private fun countBalancedPermutations(num: String): Int {
        val mod = 1_000_000_000 + 7
        val n = num.length

        val sum = num.sumOf { it - '0' }
        if (sum % 2 != 0) return 0

        val fact = LongArray(n + 1) { 1L }
        for (i in 1..n) fact[i] = fact[i - 1] * i % mod

        val inv = LongArray(n + 1) { 1L }
        for (i in 2..n) inv[i] = mod - (mod / i) * inv[(mod % i)] % mod

        val invFact = LongArray(n + 1) { 1L }
        for (i in 1..n) invFact[i] = invFact[i - 1] * inv[i] % mod

        val halfSum = sum / 2
        val halfLen = n / 2
        val dp = Array(halfSum + 1) { IntArray(halfLen + 1) }
        dp[0][0] = 1

        val digits = IntArray(10)
        for (c in num) {
            val d = c - '0'
            digits[d]++
            for (i in halfSum downTo d) {
                for (j in halfLen downTo 1) {
                    dp[i][j] = (dp[i][j] + dp[i - d][j - 1]) % mod
                }
            }
        }

        var res = dp[halfSum][halfLen].toLong()
        res = res * fact[halfLen] % mod * fact[n - halfLen] % mod
        for (count in digits) res = res * invFact[count] % mod

        return res.toInt()
    }
}
