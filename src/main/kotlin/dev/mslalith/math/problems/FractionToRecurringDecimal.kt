package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.Array
import kotlin.Int
import kotlin.Pair
import kotlin.String
import kotlin.arrayOf
import kotlin.math.abs
import kotlin.to


class FractionToRecurringDecimal : TestCaseProblem<Pair<Int, Int>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FractionToRecurringDecimal().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, String>> = arrayOf(
        TestCase(
            input = 1 to 2,
            output = "0.5"
        ),
        TestCase(
            input = 2 to 1,
            output = "2"
        ),
        TestCase(
            input = 4 to 333,
            output = "0.(012)"
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): String {
        return fractionToDecimal(testCaseInput.first, testCaseInput.second)
    }

    private fun fractionToDecimal(numerator: Int, denominator: Int): String {
        if (numerator == 0) return "0"

        val fraction = StringBuilder()
        if ((numerator < 0) xor (denominator < 0)) fraction.append("-")

        val dividend = abs(numerator.toLong())
        val divisor = abs(denominator.toLong())
        fraction.append(dividend / divisor)

        var remainder = dividend % divisor
        if (remainder == 0L) return fraction.toString()

        fraction.append(".")
        val map = hashMapOf<Long, Int>()
        while (remainder != 0L) {
            if (remainder in map) {
                fraction.insert(map[remainder]!!, "(")
                fraction.append(")")
                break
            }
            map[remainder] = fraction.length
            remainder *= 10

            fraction.append(remainder / divisor)
            remainder %= divisor
        }

        return fraction.toString()
    }
}
