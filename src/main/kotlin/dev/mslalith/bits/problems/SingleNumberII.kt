package dev.mslalith.bits.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class SingleNumberII : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SingleNumberII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 2, 3, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(0, 1, 0, 1, 0, 1, 99),
            output = 99
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return singleNumber(testCaseInput)
    }

    private fun singleNumber(nums: IntArray): Int {
        var ones = 0
        var twos = 0
        for (num in nums) {
            ones = (ones xor num) and twos.inv()
            twos = (twos xor num) and ones.inv()
        }
        return ones
    }

    private fun singleNumberNaive(nums: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        return map.keys.first { map[it] == 1 }
    }
}
