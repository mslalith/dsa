package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MultiplyStrings : TestCaseProblem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MultiplyStrings().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "2" to "3",
            output = "6"
        ),
        TestCase(
            input = "123" to "456",
            output = "56088"
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return multiply(testCaseInput.first, testCaseInput.second)
    }

    private fun multiply(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") return "0"

        val result = StringBuilder()

        fun multiplyWith(num: String, digit: Int): String {
            var carry = 0
            val sb = StringBuilder()
            for (i in num.lastIndex downTo 0) {
                val prod = num[i].digitToInt() * digit + carry
                sb.insert(0, prod % 10)
                carry = prod / 10
            }
            if (carry != 0) sb.insert(0, carry)
            return sb.toString()
        }

        val list = mutableListOf<String>()
        for (j in num2.lastIndex downTo 0) {
            val num = num2[j].digitToInt()
            var m = multiplyWith(num1, num)
            m = if (num2.lastIndex - j > 0) m + "0".repeat(num2.lastIndex - j) else m
            list.add(m)
        }

        val maxLen = list.maxBy { it.length }.length
        val pList = list.map { it.padStart(maxLen, '0') }

        var carry = 0
        for (i in maxLen - 1 downTo 0) {
            val sum = pList.sumOf { it[i].digitToInt() } + carry
            result.insert(0, sum % 10)
            carry = sum / 10
        }
        if (carry != 0) result.insert(0, carry)

        return result.toString()
    }
}
