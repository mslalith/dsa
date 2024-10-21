package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class AddBinary : Problem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AddBinary().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "11" to "1",
            output = "100"
        ),
        TestCase(
            input = "1010" to "1011",
            output = "10101"
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return addBinary(testCaseInput.first, testCaseInput.second)
    }

    private fun addBinary(a: String, b: String): String {
        val sb = StringBuilder()

        var carry = "0"

        val (x, y) = when {
            a.length == b.length -> a to b
            a.length < b.length -> a.padStart(b.length, '0') to b
            else -> a to b.padStart(a.length, '0')
        }

        for (i in x.lastIndex downTo 0) {
            val k = carry + x[i] + y[i]
            when (k) {
                "000" -> {
                    carry = "0"
                    sb.insert(0, '0')
                }
                "111" -> {
                    carry = "1"
                    sb.insert(0, '1')
                }
                "110", "101", "011" -> {
                    carry = "1"
                    sb.insert(0, '0')
                }
                else -> {
                    carry = "0"
                    sb.insert(0, '1')
                }
            }
        }

        if (carry == "1") sb.insert(0, '1')

        return sb.toString()
    }
}
