package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.createClone

class SetMatrixZeroes : TestCaseProblem<Array<IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SetMatrixZeroes().runAll()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1)
            ),
            output = arrayOf(
                intArrayOf(1, 0, 1),
                intArrayOf(0, 0, 0),
                intArrayOf(1, 0, 1)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1, 2, 0),
                intArrayOf(3, 4, 5, 2),
                intArrayOf(1, 3, 1, 5)
            ),
            output = arrayOf(
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 4, 5, 0),
                intArrayOf(0, 3, 1, 0)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        val clone = testCaseInput.createClone()
        setZeroes(clone)
        return clone
    }

    private fun setZeroes(matrix: Array<IntArray>) {
        val rows = hashSetOf<Int>()
        val cols = hashSetOf<Int>()

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) {
                    rows.add(i)
                    cols.add(j)
                }
            }
        }

        for (i in rows) {
            for (j in matrix[i].indices) matrix[i][j] = 0
        }

        for (j in cols) {
            for (row in matrix) row[j] = 0
        }
    }
}
