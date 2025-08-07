package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class FindMaximumNumberOfFruitsCollected : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMaximumNumberOfFruitsCollected().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 8, 7),
                intArrayOf(9, 10, 11, 12),
                intArrayOf(13, 14, 15, 16)
            ),
            output = 100
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 1)
            ),
            output = 4
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return maxCollectedFruits(testCaseInput)
    }

    private fun maxCollectedFruits(fruits: Array<IntArray>): Int {
        val n = fruits.size
        var res = 0

        for (i in 0..<n) res += fruits[i][i]

        for (pass in 0..1) {
            if (pass == 1) {
                for (i in 0..<n) {
                    for (j in i + 1..<n) {
                        val tmp = fruits[i][j]
                        fruits[i][j] = fruits[j][i]
                        fruits[j][i] = tmp
                    }
                }
            }

            var curr = IntArray(n)
            var prev = IntArray(n) { -1 }
            prev[n - 1] = fruits[0][n - 1]

            for (row in 1..<n - 1) {
                curr.fill(-1)

                for (i in 0..<n) {
                    if (prev[i] < 0) continue

                    if (i > 0) curr[i - 1] = maxOf(curr[i - 1], prev[i] + fruits[row][i - 1])
                    if (i < n - 1) curr[i + 1] = maxOf(curr[i + 1], prev[i] + fruits[row][i + 1])
                    curr[i] = maxOf(curr[i], prev[i] + fruits[row][i])
                }

                val tmp = prev
                prev = curr
                curr = tmp
            }

            res += prev[n - 1]
        }

        return res
    }
}
