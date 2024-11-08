package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class AddStrings : TestCaseProblem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AddStrings().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "11" to "123",
            output = "134"
        ),
        TestCase(
            input = "456" to "77",
            output = "533"
        ),
        TestCase(
            input = "0" to "0",
            output = "0"
        ),
        TestCase(
            input = "1" to "9",
            output = "10"
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return addStrings(testCaseInput.first, testCaseInput.second)
    }

    private fun addStrings(num1: String, num2: String): String {
        var i = num1.length - 1
        var j = num2.length - 1

        val sb = StringBuilder()
        var carry = 0

        while (i >= 0 && j >= 0) {
            val sum = num1[i].digitToInt() + num2[j].digitToInt() + carry
            sb.insert(0, sum % 10)
            carry = sum / 10
            i--
            j--
        }

        while (i >= 0) {
            val sum = num1[i].digitToInt() + carry
            sb.insert(0, sum % 10)
            carry = sum / 10
            i--
        }

        while (j >= 0) {
            val sum = num2[j].digitToInt() + carry
            sb.insert(0, sum % 10)
            carry = sum / 10
            j--
        }

        if (carry != 0) sb.insert(0, carry)

        return sb.toString()
    }
}
