package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SqrtOfX : TestCaseProblem<Int, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SqrtOfX().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 4,
            output = 2
        ),
        TestCase(
            input = 8,
            output = 2
        ),
        TestCase(
            input = 2147395600,
            output = 46340
        )
    )
    
    override fun solve(testCaseInput: Int): Int {
        return mySqrt(testCaseInput)
    }

    private fun mySqrt(x: Int): Int {
        if (x <= 0) return 0

        var i = 1L
        var square = i * i

        while (square <= x) {
            i++
            square = i * i
        }

        return (i - 1).toInt()
    }
}
