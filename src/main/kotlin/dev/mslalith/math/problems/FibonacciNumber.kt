package dev.mslalith.math.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class FibonacciNumber : Problem<Int, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FibonacciNumber().run()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(input = 2, output = 1),
        TestCase(input = 3, output = 2),
        TestCase(input = 4, output = 3),
    )
    
    override fun solve(testCaseInput: Int): Int {
        return fib(testCaseInput)
    }

    private fun fib(n: Int): Int {
        if (n == 0 || n == 1) return n

        val array = intArrayOf(1, 1, 0)
        for (i in 2..n) {
            array[0] = array[1] + array[2]
            array[2] = array[1]
            array[1] = array[0]
        }
        return array[0]
    }
}