package src.math.problems

import src.core.Problem
import src.core.TestCase

class HappyNumber : Problem<Int, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = HappyNumber().run()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(
            input = 19,
            output = true
        ),
        TestCase(
            input = 2,
            output = false
        )
    )
    
    override fun solve(testCaseInput: Int): Boolean {
        return isHappy(testCaseInput)
    }

    private fun isHappy(n: Int): Boolean {
        val set = hashSetOf<Int>()
        var x = digitSquareSum(n)
        while (!set.contains(x)) {
            if (x == 1) return true
            set.add(x)
            x = digitSquareSum(x)
        }
        return false
    }

    private fun digitSquareSum(n: Int): Int {
        var sum = 0
        var x = n
        while (x != 0) {
            sum += (x % 10) * (x % 10)
            x /= 10
        }
        return sum
    }
}
