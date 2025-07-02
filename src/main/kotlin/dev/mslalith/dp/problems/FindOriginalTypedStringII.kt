package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class FindOriginalTypedStringII : TestCaseProblem<Pair<String, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindOriginalTypedStringII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "aabbccdd" to 7,
            output = 5
        ),
        TestCase(
            input = "aabbccdd" to 8,
            output = 1
        ),
        TestCase(
            input = "aaabbb" to 3,
            output = 8
        )
    )
    
    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return possibleStringCount(testCaseInput.first, testCaseInput.second)
    }

    private fun possibleStringCount(word: String, k: Int): Int {
        if (word.isEmpty()) return 0
        val mod = 1_000_000_000 + 7

        val groups = mutableListOf<Int>()
        var count = 1

        for (i in 1..<word.length) {
            if (word[i] == word[i - 1]) count++ else {
                groups.add(count)
                count = 1
            }
        }
        groups.add(count)

        var total = 1L
        for (num in groups) total = (total * num) % mod

        if (k <= groups.size) return total.toInt()

        var dp = IntArray(k)
        dp[0] = 1

        for (num in groups) {
            val newDp = IntArray(k)
            var sum = 0L
            for (s in 0..<k) {
                if (s > 0) sum = (sum + dp[s - 1]) % mod
                if (s > num) sum = (sum - dp[s - num - 1] + mod) % mod
                newDp[s] = sum.toInt()
            }
            dp = newDp
        }

        var invalid = 0L
        for (s in groups.size..<k) invalid = (invalid + dp[s]) % mod

        return ((total - invalid + mod) % mod).toInt()
    }
}
