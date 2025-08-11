package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ReorderedPowerOf2 : TestCaseProblem<Int, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReorderedPowerOf2().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Boolean>> = arrayOf(
        TestCase(
            input = 1,
            output = true
        ),
        TestCase(
            input = 10,
            output = false
        )
    )
    
    override fun solve(testCaseInput: Int): Boolean {
        return reorderedPowerOf2(testCaseInput)
    }

    private fun reorderedPowerOf2(n: Int): Boolean {

        fun sortedString(x: Int): String {
            val arr = x.toString().toCharArray()
            arr.sort()
            return String(arr)
        }

        val target = sortedString(n)
        for (i in 0..30) {
            if (sortedString(1 shl i) == target) return true
        }

        return false
    }

}
