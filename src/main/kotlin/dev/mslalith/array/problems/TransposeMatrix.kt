package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class TransposeMatrix : TestCaseProblem<Array<IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TransposeMatrix().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            output = arrayOf(
                intArrayOf(1, 4, 7),
                intArrayOf(2, 5, 8),
                intArrayOf(3, 6, 9)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
            ),
            output = arrayOf(
                intArrayOf(1, 4),
                intArrayOf(2, 5),
                intArrayOf(3, 6)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        return transpose(testCaseInput)
    }

    private fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        val m = matrix.size
        val n = matrix[0].size

        val result = Array(n) { IntArray(m) }

        for (i in 0 until m) {
            for (j in 0 until n) {
                result[j][i] = matrix[i][j]
            }
        }

        return result
    }
}
