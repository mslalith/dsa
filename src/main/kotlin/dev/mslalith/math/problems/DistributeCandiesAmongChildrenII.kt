package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class DistributeCandiesAmongChildrenII : TestCaseProblem<Pair<Int, Int>, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DistributeCandiesAmongChildrenII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Long>> = arrayOf(
        TestCase(
            input = 5 to 2,
            output = 3
        ),
        TestCase(
            input = 3 to 3,
            output = 10
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): Long {
        return distributeCandies(testCaseInput.first, testCaseInput.second)
    }

    private fun distributeCandies(n: Int, limit: Int): Long {
        var res = 0L

        for (i in 0..minOf(limit, n)) {
            if (n - i <= 2 * limit) {
                res += minOf(n - i, limit) - maxOf(0, n - i - limit) + 1
            }
        }

        return res
    }
}
