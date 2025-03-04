package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CheckIfNumberIsSumOfPowersOfThree : TestCaseProblem<Int, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CheckIfNumberIsSumOfPowersOfThree().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(
            input = 12,
            output = true
        ),
        TestCase(
            input = 91,
            output = true
        ),
        TestCase(
            input = 21,
            output = false
        )
    )
    
    override fun solve(testCaseInput: Int): Boolean {
        return checkPowersOfThree(testCaseInput)
    }

    private fun checkPowersOfThree(n: Int): Boolean {
        var num = n

        while (num > 0) {
            if (num % 3 > 1) return false
            num /= 3
        }

        return true
    }
}
