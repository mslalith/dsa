package src.string.problems

import src.core.Problem
import src.core.TestCase

class GreatestCommonDivisorOfStrings : Problem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = GreatestCommonDivisorOfStrings().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "ABCABC" to "ABC",
            output = "ABC"
        ),
        TestCase(
            input = "ABABAB" to "ABAB",
            output = "AB"
        ),
        TestCase(
            input = "LEET" to "CODE",
            output = ""
        ),
        TestCase(
            input = "ABCDEF" to "ABC",
            output = ""
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return gcdOfStrings(testCaseInput.first, testCaseInput.second)
    }

    private fun gcdOfStrings(str1: String, str2: String): String {
        if ((str1 + str2) != (str2 + str1)) return ""
        val index = gcd(str1.length, str2.length)
        return str1.substring(0, index)
    }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
