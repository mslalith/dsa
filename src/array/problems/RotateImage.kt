package src.array.problems

import src.core.Problem
import src.core.TestCase
import src.utils.createClone

class RotateImage : Problem<Array<IntArray>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RotateImage().run()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            output = arrayOf(
                intArrayOf(7, 4, 1),
                intArrayOf(8, 5, 2),
                intArrayOf(9, 6, 3)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(5, 1, 9, 11),
                intArrayOf(2, 4, 8, 10),
                intArrayOf(13, 3, 6, 7),
                intArrayOf(15, 14, 12, 16)
            ),
            output = arrayOf(
                intArrayOf(15, 13, 2, 5),
                intArrayOf(14, 3, 4, 1),
                intArrayOf(12, 6, 8, 9),
                intArrayOf(16, 7, 10, 11)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray> {
        val clone = testCaseInput.createClone()
        rotate(clone)
        return clone
    }

    private fun rotate(matrix: Array<IntArray>) {
        val n = matrix.size
        if (n == 1) return

        for (i in 0 until n) {
            for (j in 0 until i) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }


        for (i in 0 until n) {
            for (j in 0 until (n / 2)) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[i][n - j - 1]
                matrix[i][n - j - 1] = temp
            }
        }
    }

    private fun rotateNaive(matrix: Array<IntArray>) {
        val n = matrix.size
        if (n == 1) return

        val result = Array(n) { IntArray(n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                result[j][n - i - 1] = matrix[i][j]
            }
        }


        for (i in 0 until n) {
            for (j in 0 until n) {
                matrix[i][j] = result[i][j]
            }
        }
    }
}
