package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LengthOfLongestVShapedDiagonalSegment : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LengthOfLongestVShapedDiagonalSegment().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(2, 2, 1, 2, 2),
                intArrayOf(2, 0, 2, 2, 0),
                intArrayOf(2, 0, 1, 1, 0),
                intArrayOf(1, 0, 2, 2, 2),
                intArrayOf(2, 0, 0, 2, 2)
            ),
            output = 5
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(2, 2, 2, 2, 2),
                intArrayOf(2, 0, 2, 2, 0),
                intArrayOf(2, 0, 1, 1, 0),
                intArrayOf(1, 0, 2, 2, 2),
                intArrayOf(2, 0, 0, 2, 2)
            ),
            output = 4
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return lenOfVDiagonal(testCaseInput)
    }

    private fun lenOfVDiagonal(grid: Array<IntArray>): Int {
        val cache = Array(grid.size) {
            Array(grid[0].size) {
                Array(4) { IntArray(2) { -1 } }
            }
        }


        fun dfs(i: Int, j: Int, dir: Int, used: Int, prev: Int): Int {
            if (i < 0 || i >= grid.size || j < 0 || j >= grid[0].size) return 0
            if (grid[i][j] != 2 - prev) return 0

            if (cache[i][j][dir][used] != -1) return cache[i][j][dir][used]

            var maxi = 0

            if (dir == 0) {
                maxi = maxOf(maxi, dfs(i - 1, j - 1, 0, used, 2 - prev))
                if (used == 0) maxi = maxOf(maxi, dfs(i - 1, j + 1, 1, 1, 2 - prev))
            }
            if (dir == 1) {
                maxi = maxOf(maxi, dfs(i - 1, j + 1, 1, used, 2 - prev))
                if (used == 0) maxi = maxOf(maxi, dfs(i + 1, j + 1, 3, 1, 2 - prev))
            }
            if (dir == 2) {
                maxi = maxOf(maxi, dfs(i + 1, j - 1, 2, used, 2 - prev))
                if (used == 0) maxi = maxOf(maxi, dfs(i - 1, j - 1, 0, 1, 2 - prev))
            }
            if (dir == 3) {
                maxi = maxOf(maxi, dfs(i + 1, j + 1, 3, used, 2 - prev))
                if (used == 0) maxi = maxOf(maxi, dfs(i + 1, j - 1, 2, 1, 2 - prev))
            }

            cache[i][j][dir][used] = 1 + maxi
            return cache[i][j][dir][used]
        }

        var res = 0

        for (i in 0..<grid.size) {
            for (j in 0..<grid[i].size) {
                if (grid[i][j] == 1) {
                    res = maxOf(res, 1 + dfs(i - 1, j - 1, 0, 0, 0))
                    res = maxOf(res, 1 + dfs(i - 1, j + 1, 1, 0, 0))
                    res = maxOf(res, 1 + dfs(i + 1, j - 1, 2, 0, 0))
                    res = maxOf(res, 1 + dfs(i + 1, j + 1, 3, 0, 0))
                }
            }
        }

        return res
    }
}
