package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LongestBinarySubsequenceLessThanOrEqualToK : TestCaseProblem<Pair<String, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestBinarySubsequenceLessThanOrEqualToK().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "1001010" to 5,
            output = 5
        ),
        TestCase(
            input = "00101001" to 1,
            output = 6
        )
    )
    
    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return longestSubsequence(testCaseInput.first, testCaseInput.second)
    }

    private fun longestSubsequence(s: String, k: Int): Int {
        val zeros = s.count { it == '0' }
        var ones = 0

        var sum = 0L
        var pow = 1L

        for (i in s.lastIndex downTo 0) {
            if (sum + pow > k) continue

            if (s[i] == '1') {
                sum += pow
                ones++
            }

            pow = pow shl 1
        }

        return zeros + ones
    }

    private fun longestSubsequenceNaive(s: String, k: Int): Int {
        val binary = StringBuilder()

        var i = s.length - 1
        while (i >= 0) {
            binary.insert(0, s[i])
            val num = binary.toString().toLong(2)
            if (num > k) {
                binary.deleteAt(0)
                break
            }
            i--
        }

        while (i >= 0) if (s[i--] == '0') binary.insert(0, '0')

        return binary.length
    }
}
