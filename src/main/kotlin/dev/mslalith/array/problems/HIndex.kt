package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.min

class HIndex : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = HIndex().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3, 0, 6, 1, 5),
            output = 3
        ),
        TestCase(
            input = intArrayOf(1, 3, 1),
            output = 1
        ),
        TestCase(
            input = intArrayOf(11, 15),
            output = 2
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return hIndex(testCaseInput.clone())
    }

    private fun hIndex(citations: IntArray): Int {
        val n = citations.size
        if (n == 1) return if (citations[0] == 0) 0 else 1

        val citationsCountArray = IntArray(n + 1)

        for (citation in citations) citationsCountArray[min(n, citation)]++

        var i = n
        var papers = citationsCountArray[n]

        while (papers < i) papers += citationsCountArray[--i]

        return i
    }
}
