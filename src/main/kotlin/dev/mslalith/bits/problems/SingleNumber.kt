package dev.mslalith.bits.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class SingleNumber : TestCaseProblem<IntArray, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SingleNumber().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2,2,1),
            output = 1
        ),
        TestCase(
            input = intArrayOf(4,1,2,1,2),
            output = 4
        )
    )
    
    override fun solve(testCaseInput: IntArray): Int {
        return singleNumber(testCaseInput)
    }

    private fun singleNumber(nums: IntArray): Int {
        return nums.reduce { acc, i -> acc xor i }
    }

    private fun singleNumberNaive(nums: IntArray): Int {
        val set = hashSetOf<Int>()
        nums.forEach {
            if (set.contains(it)) set.remove(it) else set.add(it)
        }
        return set.first()
    }
}
