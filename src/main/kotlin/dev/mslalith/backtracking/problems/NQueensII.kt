package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class NQueensII : TestCaseProblem<Int, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NQueensII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 4,
            output = 2
        ),
        TestCase(
            input = 1,
            output = 1
        )
    )
    
    override fun solve(testCaseInput: Int): Int {
        return totalNQueens(testCaseInput)
    }

    private fun totalNQueens(n: Int): Int {
        val trackQueen = linkedSetOf<Int>()

        fun rowColToNum(row: Int, col: Int): Int = (row * n) + col

        fun isValid(row: Int, col: Int): Boolean {
            var r = row
            var c = col

            while (c >= 0) {
                if (rowColToNum(r, c) in trackQueen) return false
                c--
            }

            r = row
            c = col
            while (r >= 0 && c >= 0) {
                if (rowColToNum(r, c) in trackQueen) return false
                r--
                c--
            }

            r = row
            c = col
            while (c >= 0 && r < n) {
                if (rowColToNum(r, c) in trackQueen) return false
                r++
                c--
            }

            return true
        }

        fun findNQueens(col: Int): Int {
            if (col == n) return 1

            var count = 0

            for (row in 0 until n) {
                if (isValid(row, col)) {
                    val num = rowColToNum(row, col)
                    trackQueen += num
                    count += findNQueens(col + 1)
                    trackQueen -= num
                }
            }

            return count
        }

        return findNQueens(0)
    }
}
