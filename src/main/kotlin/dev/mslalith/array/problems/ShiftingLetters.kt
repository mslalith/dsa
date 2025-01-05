package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class ShiftingLetters : TestCaseProblem<Pair<String, IntArray>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ShiftingLetters().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, IntArray>, String>> = arrayOf(
        TestCase(
            input = "abc" to intArrayOf(3, 5, 9),
            output = "rpl"
        ),
        TestCase(
            input = "aaa" to intArrayOf(1, 2, 3),
            output = "gfd"
        ),
        TestCase(
            input = "bad" to intArrayOf(10, 20, 30),
            output = "jyh"
        ),
        TestCase(
            input = "mkgfzkkuxownxvfvxasy" to intArrayOf(505870226, 437526072, 266740649, 224336793, 532917782, 311122363, 567754492, 595798950, 81520022, 684110326, 137742843, 275267355, 856903962, 148291585, 919054234, 467541837, 622939912, 116899933, 983296461, 536563513),
            output = "wqqwlcjnkphhsyvrkdod"
        )
    )

    override fun solve(testCaseInput: Pair<String, IntArray>): String {
        return shiftingLetters(testCaseInput.first, testCaseInput.second)
    }

    private fun shiftingLetters(s: String, shifts: IntArray): String {
        val n = shifts.size
        val sb = StringBuilder(s)

        var suffixSum = 0L

        for (i in (n - 1) downTo 0) {
            suffixSum += shifts[i]
            val nextChar = (s[i] - 'a' + (suffixSum % 26)) % 26
            sb[i] = (nextChar + 'a'.code).toInt().toChar()
        }

        return sb.toString()
    }
}
