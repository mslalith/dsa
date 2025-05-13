package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class TotalCharactersInStringAfterTransformationsI : TestCaseProblem<Pair<String, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TotalCharactersInStringAfterTransformationsI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "abcyy" to 2,
            output = 7
        ),
        TestCase(
            input = "azbk" to 1,
            output = 5
        ),
        TestCase(
            input = "k" to 10,
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return lengthAfterTransformations(testCaseInput.first, testCaseInput.second)
    }

    private fun lengthAfterTransformations(s: String, t: Int): Int {
        val mod = 1_000_000_000 + 7

        var freq = LongArray(26)
        for (ch in s) freq[ch - 'a']++

        repeat(t) {
            val curr = LongArray(26)
            for (i in freq.indices) {
                if (i == 25) {
                    curr[0] = (curr[0] + freq[i]) % mod
                    curr[1] = (curr[1] + freq[i]) % mod
                } else {
                    curr[i + 1] = (curr[i + 1] + freq[i]) % mod
                }
            }
            freq = curr
        }

        var count = 0L
        for (i in freq) count = (count + i) % mod
        return count.toInt()
    }
}
