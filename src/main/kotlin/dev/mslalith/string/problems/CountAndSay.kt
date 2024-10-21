package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class CountAndSay : TestCaseProblem<Int, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountAndSay().runAll()
    }

    override fun getTestCases(): Array<TestCase<Int, String>> = arrayOf(
        TestCase(input = 1, output = "1"),
        TestCase(input = 4, output = "1211")
    )

    override fun solve(testCaseInput: Int): String {
        return countAndSay(testCaseInput)
    }

    private fun countAndSay(n: Int): String {
        if (n == 1) return "1"

        var lastSay = "1"
        val currentSay = StringBuilder()
        repeat(times = n - 1) {
            var lastChar = lastSay[0]
            var count = 0
            currentSay.clear()

            lastSay.forEach { ch ->
                if (ch == lastChar) count++ else {
                    currentSay.append(count).append(lastChar)
                    count = 1
                    lastChar = ch
                }
            }
            currentSay.append(count).append(lastChar)
            lastSay = currentSay.toString()
        }

        return lastSay
    }
}