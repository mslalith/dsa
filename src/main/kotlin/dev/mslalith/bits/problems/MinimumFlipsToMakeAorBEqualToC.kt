package dev.mslalith.bits.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class MinimumFlipsToMakeAorBEqualToC : TestCaseProblem<MinimumFlipsToMakeAorBEqualToCParams, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumFlipsToMakeAorBEqualToC().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<MinimumFlipsToMakeAorBEqualToCParams, Int>> = arrayOf(
        TestCase(
            input = MinimumFlipsToMakeAorBEqualToCParams(2, 6, 5),
            output = 3
        ),
        TestCase(
            input = MinimumFlipsToMakeAorBEqualToCParams(4, 2, 7),
            output = 1
        ),
        TestCase(
            input = MinimumFlipsToMakeAorBEqualToCParams(1, 2, 3),
            output = 0
        ),
        TestCase(
            input = MinimumFlipsToMakeAorBEqualToCParams(8, 3, 5),
            output = 3
        )
    )
    
    override fun solve(testCaseInput: MinimumFlipsToMakeAorBEqualToCParams): Int {
        return minFlips(testCaseInput.a, testCaseInput.b, testCaseInput.c)
    }

    private fun minFlips(a: Int, b: Int, c: Int): Int {
        var ec = a or b
        if (ec == c) return 0

        var x = a
        var y = b
        var z = c
        var count = 0

        while (true) {
            val s = z and 1

            if ((ec and 1) != s) {
                if (s == 0) {
                    if ((x and 1) == 1) count++
                    if ((y and 1) == 1) count++
                } else {
                    count++
                }
            }

            ec = ec shr 1
            x = x shr 1
            y = y shr 1
            z = z shr 1

            if (ec == 0 && z == 0) break
        }

        return count
    }
}

data class MinimumFlipsToMakeAorBEqualToCParams(
    val a: Int,
    val b: Int,
    val c: Int
) {
    override fun toString(): String {
        return """
            
            a: $a
            b: $b
            c: $c
        """.trimIndent()
    }
}
