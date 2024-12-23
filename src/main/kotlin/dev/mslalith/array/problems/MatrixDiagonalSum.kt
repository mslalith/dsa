package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MatrixDiagonalSum : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MatrixDiagonalSum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            output = 25
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1)
            ),
            output = 8
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(5)
            ),
            output = 5
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return diagonalSum(testCaseInput)
    }

    private fun diagonalSum(mat: Array<IntArray>): Int {
        val n = mat.size

        var sum = 0

        for (col in 0 until n) {
            sum += mat[col][col]
            val row = n - 1 - col
            if (row != col) sum += mat[row][col]
        }

        return sum
    }

    private fun diagonalSumNaive(mat: Array<IntArray>): Int {
        val n = mat.size

        val diagIndexes = hashSetOf<String>()
        var diagSum = 0

        for (i in 0 until n) {
            diagSum += mat[i][i]
            diagIndexes += "$i:$i"
        }
        for (col in 0 until n) {
            val row = n - col - 1
            if ("$row:$col" !in diagIndexes) diagSum += mat[row][col]
        }

        return diagSum
    }
}
