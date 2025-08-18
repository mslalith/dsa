package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.abs


class TwentyFourGame : TestCaseProblem<IntArray, Boolean>() {

    companion object Companion {
        @JvmStatic
        fun main(args: Array<String>) = TwentyFourGame().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 1, 8, 7),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2, 1, 2),
            output = false
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return judgePoint24(testCaseInput)
    }

    private fun judgePoint24(cards: IntArray): Boolean {
        val eps = 1e-6
        val nums = mutableListOf<Double>()
        for (n in cards) nums += n.toDouble()

        fun allPossible(a: Double, b: Double): MutableList<Double> {
            val res = mutableListOf(
                a + b,
                a - b,
                b - a,
                a * b
            )

            if (abs(b) > eps) res += a / b
            if (abs(a) > eps) res += b / a

            return res
        }

        fun dfs(list: MutableList<Double>): Boolean {
            if (list.size == 1) return abs(list[0] - 24.0) < eps

            for (i in list.indices) {
                for (j in list.indices) {
                    if (i == j) continue

                    val next = mutableListOf<Double>()

                    for (k in list.indices) {
                        if (k != i && k != j) next += list[k]
                    }

                    for (value in allPossible(list[i], list[j])) {
                        next += value
                        if (dfs(next)) return true
                        next.removeLast()
                    }
                }
            }

            return false
        }

        return dfs(nums)
    }
}
