package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindTheTownJudge : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindTheTownJudge().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(1, 2)
            ),
            output = 2
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 3)
            ),
            output = 3
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(1, 3),
                intArrayOf(2, 3),
                intArrayOf(3, 1)
            ),
            output = -1
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3)
            ),
            output = -1
        ),
        TestCase(
            input = 1 to arrayOf(),
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return findJudge(testCaseInput.first, testCaseInput.second)
    }

    private fun findJudge(n: Int, trust: Array<IntArray>): Int {
        if (trust.isEmpty()) return if (n == 1) 1 else -1

        val inDegree = IntArray(n + 1)
        val outDegree = IntArray(n + 1)

        trust.forEach {
            inDegree[it[1]]++
            outDegree[it[0]]++
        }

        for (i in 1..n) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) return i
        }

        return -1
    }
}
