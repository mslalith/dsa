package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class BitwiseORsOfSubarrays : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BitwiseORsOfSubarrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(0),
            output = 1
        ),
        TestCase(
            input = intArrayOf(1, 1, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(1, 2, 4),
            output = 6
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return subarrayBitwiseORs(testCaseInput)
    }

    private fun subarrayBitwiseORs(arr: IntArray): Int {
        val resultOrs = hashSetOf<Int>()
        var currentOrs = hashSetOf<Int>()

        for (x in arr) {
            val nextOrs = hashSetOf<Int>()
            nextOrs += x

            for (y in currentOrs) nextOrs += x or y

            resultOrs.addAll(nextOrs)
            currentOrs = nextOrs
        }

        return resultOrs.size
    }
}
