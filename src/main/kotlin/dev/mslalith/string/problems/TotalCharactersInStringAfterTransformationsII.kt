package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.string.problems.TotalCharactersInStringAfterTransformationsII.TotalCharactersInStringAfterTransformationsIIParams

class TotalCharactersInStringAfterTransformationsII : TestCaseProblem<TotalCharactersInStringAfterTransformationsIIParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TotalCharactersInStringAfterTransformationsII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<TotalCharactersInStringAfterTransformationsIIParams, Int>> = arrayOf(
        TestCase(
            input = TotalCharactersInStringAfterTransformationsIIParams(
                s = "abcyy",
                t = 2,
                nums = listOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2),
            ),
            output = 7
        ),
        TestCase(
            input = TotalCharactersInStringAfterTransformationsIIParams(
                s = "azbk",
                t = 1,
                nums = listOf(2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2),
            ),
            output = 8
        )
    )

    override fun solve(testCaseInput: TotalCharactersInStringAfterTransformationsIIParams): Int {
        return lengthAfterTransformations(testCaseInput.s, testCaseInput.t, testCaseInput.nums)
    }

    fun lengthAfterTransformations(s: String, t: Int, nums: List<Int>): Int {
        val n = 26
        val mod = 1_000_000_000 + 7

        fun multiplyMatrices(a: Array<LongArray>, b: Array<LongArray>): Array<LongArray> {
            val rowsA = a.size
            val colsA = a[0].size
            val colsB = b[0].size
            val result = Array(rowsA) { LongArray(colsB) }

            for (i in 0 until rowsA) {
                for (j in 0 until colsB) {
                    var sum = 0L
                    for (k in 0 until colsA) {
                        sum = (sum + (a[i][k] * b[k][j]) % mod) % mod
                    }
                    result[i][j] = sum
                }
            }

            return result
        }

        fun powerMatrix(matrix: Array<LongArray>, exponent: Long): Array<LongArray> {
            var matrix = matrix
            var exp = exponent

            var result = Array(n) { LongArray(n) }
            for (i in 0 until n) result[i][i] = 1

            while (exp > 0) {
                if ((exp and 1L) == 1L) result = multiplyMatrices(result, matrix)
                matrix = multiplyMatrices(matrix, matrix)
                exp = exp shr 1
            }

            return result
        }

        var transform = Array(n) { LongArray(n) }
        for (i in 0 until n) {
            for (shift in 0 until nums[i]) {
                transform[i][(i + 1 + shift) % 26]++
            }
        }

        transform = powerMatrix(transform, t.toLong())

        var freq = Array(1) { LongArray(n) }
        for (ch in s.toCharArray()) freq[0][ch - 'a']++

        freq = multiplyMatrices(freq, transform)

        var total = 0L
        for (cnt in freq[0]) total = (total + cnt) % mod
        return total.toInt()
    }

    data class TotalCharactersInStringAfterTransformationsIIParams(
        val s: String,
        val t: Int,
        val nums: List<Int>
    ) {
        override fun toString(): String {
            return """
                
                s: $s
                t: $t
                nums: $nums
            """.trimIndent()
        }
    }
}
