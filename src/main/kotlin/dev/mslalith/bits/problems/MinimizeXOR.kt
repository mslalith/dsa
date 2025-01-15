package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimizeXOR : TestCaseProblem<Pair<Int, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimizeXOR().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 3 to 5,
            output = 3
        ),
        TestCase(
            input = 1 to 12,
            output = 3
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return minimizeXor(testCaseInput.first, testCaseInput.second)
    }

    private fun minimizeXor(num1: Int, num2: Int): Int {
        var a = Integer.bitCount(num1)
        val b = Integer.bitCount(num2)

        var res = num1

        for (i in 0 until 32) {
            val ithBit = num1 and (1 shl i)
            if (a > b && ithBit > 0) {
                res = res xor (1 shl i)
                a--
            }
            if (a < b && ithBit == 0) {
                res = res xor (1 shl i)
                a++
            }
        }

        return res
    }
}
