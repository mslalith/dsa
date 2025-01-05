package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ShiftingLettersII : TestCaseProblem<Pair<String, Array<IntArray>>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ShiftingLettersII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, Array<IntArray>>, String>> = arrayOf(
        TestCase(
            input = "abc" to arrayOf(
                intArrayOf(0, 1, 0),
                intArrayOf(1, 2, 1),
                intArrayOf(0, 2, 1)
            ),
            output = "ace"
        ),
        TestCase(
            input = "dztz" to arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(1, 1, 1)
            ),
            output = "catz"
        )
    )

    override fun solve(testCaseInput: Pair<String, Array<IntArray>>): String {
        return shiftingLetters(testCaseInput.first, testCaseInput.second)
    }

    private fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
        val n = s.length
        val sb = StringBuilder(s)
        val prefixSum = IntArray(n + 1)

        for ((start, end, direction) in shifts) {
            if (direction == 0) {
                prefixSum[start]--
                prefixSum[end + 1]++
            } else {
                prefixSum[start]++
                prefixSum[end + 1]--
            }
        }

        for (i in 1..n) prefixSum[i] += prefixSum[i - 1]

        for (i in 0 until n) {
            val shift = ((prefixSum[i] % 26) + 26) % 26
            val nextChar = (s[i] - 'a' + shift) % 26
            sb[i] = (nextChar + 'a'.code).toChar()
        }

        return sb.toString()
    }

    private fun shiftingLettersBrute(s: String, shifts: Array<IntArray>): String {
        val n = shifts.size
        val sb = StringBuilder(s)

        for (i in 0 until n) {
            val (start, end, direction) = shifts[i]

            for (j in start..end) {
                val ch = sb[j]
                sb[j] = if (direction == 0) {
                    if (ch == 'a') 'z' else ch - 1
                } else {
                    if (ch == 'z') 'a' else ch + 1
                }
            }
        }

        return sb.toString()
    }
}
