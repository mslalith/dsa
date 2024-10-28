package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class Search2DMatrix : TestCaseProblem<Pair<Array<IntArray>, Int>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Search2DMatrix().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Array<IntArray>, Int>, Boolean>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3, 5, 7),
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 3,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3, 5, 7),
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 13,
            output = false
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 7,
            output = false
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 70,
            output = false
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 33,
            output = false
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 34,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 60)
            ) to 60,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1)
            ) to 1,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3)
            ) to 3,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3, 5, 7),
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 50)
            ) to 11,
            output = true
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 3, 5, 7),
                intArrayOf(10, 11, 16, 20),
                intArrayOf(23, 30, 34, 50)
            ) to 20,
            output = true
        )
    )

    override fun solve(testCaseInput: Pair<Array<IntArray>, Int>): Boolean {
        return searchMatrix(testCaseInput.first, testCaseInput.second)
    }

    private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size

        if (m == 1 && n == 1) return matrix[0][0] == target

        var start = 0
        var end = m - 1

        while (start < end) {
            val mid = start + (end - start) / 2
            when {
                matrix[mid][0] == target -> return true
                matrix[mid][0] > target -> end = max(0, mid - 1)
                else -> when {
                    matrix[mid][n - 1] == target -> return true
                    matrix[mid][n - 1] < target -> start = min(m - 1, mid + 1)
                    else -> {
                        end = mid
                        break
                    }
                }
            }
        }


        val row = end
        start = 0
        end = n - 1

        while (start < end) {
            val mid = start + (end - start) / 2
            when {
                matrix[row][mid] == target -> return true
                matrix[row][mid] < target -> start = min(n - 1, mid + 1)
                else -> end = max(0, mid - 1)
            }
        }

        return matrix[row][end] == target
    }

    private fun searchMatrixNaive(matrix: Array<IntArray>, target: Int): Boolean {
        var i = 0
        var j = matrix[0].size - 1

        while (i < matrix.size && j >= 0) {
            when {
                target < matrix[i][j] -> j--
                target > matrix[i][j] -> i++
                else -> return true
            }
        }

        return false
    }
}
