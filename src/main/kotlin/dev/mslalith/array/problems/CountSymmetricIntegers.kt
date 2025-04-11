package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountSymmetricIntegers : TestCaseProblem<Pair<Int, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountSymmetricIntegers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 1 to 100,
            output = 9
        ),
        TestCase(
            input = 1200 to 1230,
            output = 4
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return countSymmetricIntegers(testCaseInput.first, testCaseInput.second)
    }

    private fun countSymmetricIntegers(low: Int, high: Int): Int {
        return (low..high).count { num ->
            val numStr = num.toString().toCharArray()
            val n = numStr.size

            if (n % 2 == 1) return@count false

            val half = n / 2
            val firstHalf = (0 until half).sumOf { numStr[it].digitToInt() }
            val secondHalf = (half until n).sumOf { numStr[it].digitToInt() }

            firstHalf == secondHalf
        }
    }
}
