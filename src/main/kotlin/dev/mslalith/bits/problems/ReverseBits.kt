package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ReverseBits : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseBits().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 43261596,   // 00000010100101000001111010011100
            output = 964176192  // 00111001011110000010100101000000
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return reverseBits(testCaseInput)
    }

    private fun reverseBits(n: Int): Int {
        var x = n
        var rev = 0
        for (i in 0 until 32) {
            rev = rev shl 1
            if (x and 1 == 1) rev++
            x = x shr 1
        }
        return rev
    }
}
