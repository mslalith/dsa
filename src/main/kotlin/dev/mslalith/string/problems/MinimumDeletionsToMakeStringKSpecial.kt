package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumDeletionsToMakeStringKSpecial : TestCaseProblem<Pair<String, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumDeletionsToMakeStringKSpecial().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "aabcaba" to 0,
            output = 3
        ),
        TestCase(
            input = "dabdcbdcdcd" to 2,
            output = 2
        ),
        TestCase(
            input = "aaabaaa" to 2,
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return minimumDeletions(testCaseInput.first, testCaseInput.second)
    }

    private fun minimumDeletions(word: String, k: Int): Int {
        val freq = IntArray(26)
        for (ch in word) freq[ch - 'a']++
        freq.sort()

        var minDeletions = Int.MAX_VALUE

        for (i in freq.indices) {
            val base = freq[i]
            var deletions = 0

            for (j in 0..<i) deletions += freq[j]

            for (j in (i + 1)..<freq.size) {
                if (freq[j] > base + k) deletions += freq[j] - (base + k)
            }

            minDeletions = minOf(minDeletions, deletions)
        }

        return minDeletions
    }
}
