package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class IntegerToRoman : TestCaseProblem<Int, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IntegerToRoman().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, String>> = arrayOf(
        TestCase(input = 3, output = "III"),
        TestCase(input = 58, output = "LVIII"),
        TestCase(input = 1994, output = "MCMXCIV")
    )

    override fun solve(testCaseInput: Int): String {
        return intToRoman(testCaseInput)
    }

    private fun intToRoman(num: Int): String {
        val mapping = linkedMapOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
        )

        val sb = StringBuilder()
        var n = num

        mapping.forEach { (value, roman) ->
            while (n >= value) {
                sb.append(roman)
                n -= value
            }
        }

        return sb.toString()
    }
}