package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class CountTheNumberOfIdealArrays : TestCaseProblem<Pair<Int, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountTheNumberOfIdealArrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 2 to 5,
            output = 10
        ),
        TestCase(
            input = 5 to 3,
            output = 11
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return idealArrays(testCaseInput.first, testCaseInput.second)
    }

    private fun idealArrays(n: Int, maxValue: Int): Int {
        val mod = 1_000_000_000 + 7L

        val combinations = Combinations(n, mod)
        var dp = LongArray(maxValue + 1)
        var totalAns = maxValue.toLong()

        for (i in 1..maxValue) dp[i] = 1

        val kLimit = min(n, 16)

        for (k in 2..kLimit) {
            val nextDp = LongArray(maxValue + 1)
            for (j in 1..maxValue) {
                if (dp[j] == 0L) continue

                var i = 2L * j
                while (i <= maxValue) {
                    nextDp[i.toInt()] = (nextDp[i.toInt()] + dp[j]) % mod
                    i += j
                }
            }

            var count = 0L
            for (i in 1..maxValue) {
                count = (count + nextDp[i]) % mod
            }

            if (count == 0L) break

            val factor = combinations.nCr(n - 1, k - 1)
            totalAns = (totalAns + count * factor % mod) % mod

            dp = nextDp
        }

        return totalAns.toInt()

    }
}

private class Combinations(
    n: Int,
    private val mod: Long
) {
    private val fact: LongArray = LongArray(n + 1)
    private val invFact: LongArray = LongArray(n + 1)

    init {
        fact[0] = 1
        invFact[0] = 1

        for (i in 1..n) {
            fact[i] = (fact[i - 1] * i) % mod
            invFact[i] = power(fact[i], mod - 2)
        }
    }

    fun power(base: Long, exp: Long): Long {
        var base = base
        var exp = exp
        var res = 1L
        base %= mod
        while (exp > 0) {
            if ((exp and 1L) == 1L) res = (res * base) % mod
            base = (base * base) % mod
            exp = exp shr 1
        }
        return res
    }

    fun nCr(n: Int, r: Int): Long {
        if (r < 0 || r > n) return 0
        val num = fact[n]
        val den = (invFact[r] * invFact[n - r]) % mod
        return (num * den) % mod
    }
}