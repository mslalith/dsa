package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class Search2DMatrixII : TestCaseProblem<Pair<Array<IntArray>, Int>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Search2DMatrixII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, Int>, Boolean>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 4, 7, 11, 15),
                intArrayOf(2, 5, 8, 12, 19),
                intArrayOf(3, 6, 9, 16, 22),
                intArrayOf(10, 13, 14, 17, 24),
                intArrayOf(18, 21, 23, 26, 30)
            ) to 5,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 4, 7, 11, 15),
                intArrayOf(2, 5, 8, 12, 19),
                intArrayOf(3, 6, 9, 16, 22),
                intArrayOf(10, 13, 14, 17, 24),
                intArrayOf(18, 21, 23, 26, 30)
            ) to 20,
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, Int>): Boolean {
        return searchMatrix(testCaseInput.first, testCaseInput.second)
    }

    private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size

        var row = 0
        var col = n - 1

        while (row < m && col >= 0) {
            val num = matrix[row][col]
            when {
                target < num -> col--
                target > num -> row++
                else -> return true
            }
        }

        return false
    }
}
