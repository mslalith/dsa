package dev.mslalith.bits.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class NeighboringBitwiseXOR : TestCaseProblem<IntArray, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NeighboringBitwiseXOR().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 0),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 1),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 0),
            output = false
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return doesValidArrayExist(testCaseInput)
    }

    private fun doesValidArrayExist(derived: IntArray): Boolean {
        return derived.fold(0) { acc, num -> acc xor num } == 0
    }
}
