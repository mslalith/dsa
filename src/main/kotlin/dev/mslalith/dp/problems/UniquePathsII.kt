package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UniquePathsII : TestCaseProblem<Array<IntArray>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UniquePathsII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(0, 0, 0)
            ),
            output = 2
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 0)
            ),
            output = 1
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1)
            ),
            output = 0
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): Int {
        return uniquePathsWithObstacles(testCaseInput)
    }

    private fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size - 1
        val n = obstacleGrid[0].size - 1

        if (obstacleGrid[m][n] == 1) return 0

        val cache = hashMapOf<String, Int>()

        fun uniquePaths(x: Int, y: Int): Int {
            if (x == m && y == n) return 1
            if (x > m || y > n) return 0
            if (obstacleGrid[x][y] == 1) return 0

            val rightKey = "${x + 1}-$y"
            val downKey = "$x-${y + 1}"

            val right = if (cache.containsKey(rightKey)) cache.getValue(rightKey) else {
                uniquePaths(x + 1, y).also { cache[rightKey] = it }
            }
            val down = if (cache.containsKey(downKey)) cache.getValue(downKey) else {
                uniquePaths(x, y + 1).also { cache[downKey] = it }
            }
            return right + down
        }

        return uniquePaths(0, 0)
    }
}
