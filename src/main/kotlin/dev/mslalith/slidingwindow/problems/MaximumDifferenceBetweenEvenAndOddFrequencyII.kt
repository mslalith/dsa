package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MaximumDifferenceBetweenEvenAndOddFrequencyII : TestCaseProblem<Pair<String, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumDifferenceBetweenEvenAndOddFrequencyII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "12233" to 4,
            output = -1
        ),
        TestCase(
            input = "1122211" to 3,
            output = 1
        ),
        TestCase(
            input = "110" to 3,
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return maxDifference(testCaseInput.first, testCaseInput.second)
    }

    private fun maxDifference(s: String, k: Int): Int {
        var ans = Int.MIN_VALUE

        fun getStatus(a: Int, b: Int): Int = ((a and 1) shl 1) or (b and 1)

        for (a in '0'..'4') {
            for (b in '0'..'4') {
                if (a == b) continue

                // The best[status] stores the minimum (prevA - prevB) for a prefix
                // with the parity state 'status'. Initialize with a large value.
                val best = IntArray(4) { Int.MAX_VALUE }

                // cntA, cntB: Prefix counts for the 'right' pointer (s[0...right]).
                var cntA = 0
                var cntB = 0

                // prevA, prevB: Prefix counts for the 'left' pointer (s[0...left]).
                var prevA = 0
                var prevB = 0

                // 'left' tracks the end of the prefix we are recording in the 'best' array.
                var left = -1

                for (right in s.indices) {
                    // Update prefix counts for the current 'right' position.
                    cntA += if (s[right] == a) 1 else 0
                    cntB += if (s[right] == b) 1 else 0

                    // This loop updates the 'best' array. It advances the 'left' pointer
                    // and records the state of the prefix ending at 'left'.
                    // The conditions ensure that any substring starting at 'left + 1'
                    // will have a length of at least 'k'.
                    // Note: `cntB - prevB >= 2` is a specific condition in this implementation
                    // ensuring `freq[b]` is a positive even number. The base problem allows freq[b] = 0.
                    while (right - left >= k && cntB - prevB >= 2) {
                        val leftStatus = getStatus(prevA, prevB)
                        best[leftStatus] = minOf(best[leftStatus], prevA - prevB)

                        ++left
                        prevA += if (s[left] == a) 1 else 0
                        prevB += if (s[left] == b) 1 else 0
                    }

                    // Now, calculate the potential answer for the current 'right' pointer.
                    // 1. Get the parity state for the prefix ending at 'right'.
                    val rightStatus: Int = getStatus(cntA, cntB)

                    // 2. Determine the required state for the start-prefix.
                    // We need `leftStatus = rightStatus XOR 10` (binary).
                    val requiredStatus = rightStatus xor 2

                    // 3. If we have seen a valid starting prefix with the required state...
                    if (best[requiredStatus] != Int.Companion.MAX_VALUE) {
                        // Calculate the difference: (cntA - cntB) - min(prevA - prevB).
                        // This maximizes the expression.
                        ans = maxOf(ans, cntA - cntB - best[requiredStatus])
                    }
                }
            }
        }

        return ans
    }
}
