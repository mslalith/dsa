package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.Stack

class CountSubmatricesWithAllOnes : TestCaseProblem<Array<IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountSubmatricesWithAllOnes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Int>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 0)
            ),
            output = 13
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1, 1, 0),
                intArrayOf(0, 1, 1, 1),
                intArrayOf(1, 1, 1, 0)
            ),
            output = 24
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Int {
        return numSubmat(testCaseInput)
    }

    private fun numSubmat(mat: Array<IntArray>): Int {
        val r = mat.size
        val c = mat[0].size
        val h = IntArray(c)

        fun count(h: IntArray): Int {
            val n = h.size
            val sum = IntArray(n)
            val st = Stack<Int>()
            var res = 0

            for (i in 0..<n) {
                while (st.isNotEmpty() && h[st.peek()] >= h[i]) st.pop()

                if (st.isNotEmpty()) {
                    val p = st.peek()
                    sum[i] = sum[p] + h[i] * (i - p)
                } else {
                    sum[i] = h[i] * (i + 1)
                }

                st.push(i)
                res += sum[i]
            }

            return res
        }

        var ans = 0

        for (i in 0..<r) {
            for (j in 0..<c) {
                h[j] = if (mat[i][j] == 0) 0 else h[j] + 1
            }
            ans += count(h)
        }

        return ans
    }

}