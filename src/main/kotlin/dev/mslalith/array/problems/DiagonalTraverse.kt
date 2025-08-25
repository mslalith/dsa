package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class DiagonalTraverse : TestCaseProblem<Array<IntArray>, IntArray>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DiagonalTraverse().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<IntArray>, IntArray>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            output = intArrayOf(1, 2, 4, 7, 5, 3, 6, 8, 9)
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            ),
            output = intArrayOf(1, 2, 3, 4)
        )
    )
    
    override fun solve(testCaseInput: Array<IntArray>): IntArray {
        return findDiagonalOrder(testCaseInput)
    }

    private fun findDiagonalOrder(mat: Array<IntArray>): IntArray {
        if (mat.isEmpty()) return IntArray(0)

        val m = mat.size
        val n = mat[0].size
        val result = IntArray(m * n)

        var row = 0
        var col = 0

        for (i in 0..<m * n) {
            result[i] = mat[row][col]

            if ((row + col) % 2 == 0) when {
                col == n - 1 -> row++
                row == 0 -> col++
                else -> {
                    row--
                    col++
                }
            } else when {
                row == m - 1 -> col++
                col == 0 -> row++
                else -> {
                    row++
                    col--
                }
            }
        }

        return result
    }
}
