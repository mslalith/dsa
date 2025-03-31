package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class PutMarblesInBags : TestCaseProblem<Pair<IntArray, Int>, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PutMarblesInBags().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(1,3,5,1) to 2,
            output = 4
        ),
        TestCase(
            input = intArrayOf(1, 3) to 2,
            output = 0
        )
    )
    
    override fun solve(testCaseInput: Pair<IntArray, Int>): Long {
        return putMarbles(testCaseInput.first, testCaseInput.second)
    }

    private fun putMarbles(weights: IntArray, k: Int): Long {
        if (k == 1) return 0

        val n = weights.size
        val pairSums = IntArray(n - 1) { weights[it] + weights[it + 1] }
        pairSums.sort()

        var minScore = 0L
        var maxScore = 0L

        for (i in 0 until k - 1) {
            minScore += pairSums[i]
            maxScore += pairSums[pairSums.lastIndex - i]
        }

        return maxScore - minScore
    }
}
