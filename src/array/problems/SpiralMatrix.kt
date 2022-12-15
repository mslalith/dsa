package src.array.problems

import src.core.Problem
import src.core.TestCase

class SpiralMatrix : Problem<Array<IntArray>, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SpiralMatrix().run()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, List<Int>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            output = listOf(1, 2, 3, 6, 9, 8, 7, 4, 5)
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12)
            ),
            output = listOf(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): List<Int> {
        return spiralOrder(testCaseInput)
    }

    private fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        if (matrix.isEmpty()) return emptyList()

        var left = 0
        var right = matrix[0].size - 1
        var top = 0
        var bottom = matrix.size - 1

        val list = mutableListOf<Int>()

        while (left <= right || top <= bottom) {
            if (top <= bottom) {
                for (i in left..right) {
                    list.add(matrix[top][i])
                }
                top++
            }

            if (left <= right) {
                for (i in top..bottom) {
                    list.add(matrix[i][right])
                }
                right--
            }

            if (top <= bottom) {
                for (i in right downTo left) {
                    list.add(matrix[bottom][i])
                }
                bottom--
            }

            if (left <= right) {
                for (i in bottom downTo top) {
                    list.add(matrix[i][left])
                }
                left++
            }
        }

        return list
    }
}