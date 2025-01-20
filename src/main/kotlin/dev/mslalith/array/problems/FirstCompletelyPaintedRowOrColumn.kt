package dev.mslalith.array.problems

import dev.mslalith.array.problems.FirstCompletelyPaintedRowOrColumn.FirstCompletelyPaintedRowOrColumnParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray

class FirstCompletelyPaintedRowOrColumn : TestCaseProblem<FirstCompletelyPaintedRowOrColumnParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FirstCompletelyPaintedRowOrColumn().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FirstCompletelyPaintedRowOrColumnParams, Int>> = arrayOf(
        TestCase(
            input = FirstCompletelyPaintedRowOrColumnParams(
                arr = intArrayOf(1, 3, 4, 2),
                mat = arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(2, 3)
                )
            ),
            output = 2
        ),
        TestCase(
            input = FirstCompletelyPaintedRowOrColumnParams(
                arr = intArrayOf(2, 8, 7, 4, 1, 3, 5, 6, 9),
                mat = arrayOf(
                    intArrayOf(3, 2, 5),
                    intArrayOf(1, 4, 6),
                    intArrayOf(7, 8, 9)
                )
            ),
            output = 3
        ),
        TestCase(
            input = FirstCompletelyPaintedRowOrColumnParams(
                arr = intArrayOf(1, 4, 5, 2, 6, 3),
                mat = arrayOf(
                    intArrayOf(4, 3, 5),
                    intArrayOf(1, 2, 6)
                )
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: FirstCompletelyPaintedRowOrColumnParams): Int {
        return firstCompleteIndex(testCaseInput.arr, testCaseInput.mat)
    }

    private fun firstCompleteIndex(arr: IntArray, mat: Array<IntArray>): Int {
        val m = mat.size
        val n = mat[0].size
        if (m == 1 && n == 1) return 0

        val valueToRowColMap = hashMapOf<Int, Pair<Int, Int>>()
        for (i in 0 until m) {
            for (j in 0 until n) valueToRowColMap[mat[i][j]] = i to j
        }

        val rowFillSize = IntArray(m)
        val colFillSize = IntArray(n)

        for (i in arr.indices) {
            val (row, col) = valueToRowColMap.getValue(arr[i])
            rowFillSize[row]++
            colFillSize[col]++

            if (rowFillSize[row] == n) return i
            if (colFillSize[col] == m) return i
        }

        return -1
    }

    data class FirstCompletelyPaintedRowOrColumnParams(
        val arr: IntArray,
        val mat: Array<IntArray>
    ) {
        override fun toString(): String {
            return """
                
                arr: ${stringFromArray(arr)}
                mat: ${stringFromArray(mat)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FirstCompletelyPaintedRowOrColumnParams

            if (!arr.contentEquals(other.arr)) return false
            if (!mat.contentDeepEquals(other.mat)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = arr.contentHashCode()
            result = 31 * result + mat.contentDeepHashCode()
            return result
        }
    }
}
