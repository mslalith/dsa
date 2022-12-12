package src.math.problems

import src.core.Problem
import src.core.TestCase

class ReverseDigits : Problem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseDigits().run()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(123, 321),
        TestCase(89723478, 87432798)
    )

    override fun solve(testCaseInput: Int): Int {
        return reverseDigits(testCaseInput)
    }

    private fun reverseDigits(n: Int): Int {
        var x = n
        var rev = 0
        while (x > 0) {
            rev = rev * 10 + x % 10
            x /= 10
        }
        return rev
    }
}
