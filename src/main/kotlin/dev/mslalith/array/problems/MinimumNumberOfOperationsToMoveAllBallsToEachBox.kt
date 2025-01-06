package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.abs

class MinimumNumberOfOperationsToMoveAllBallsToEachBox : TestCaseProblem<String, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumNumberOfOperationsToMoveAllBallsToEachBox().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, IntArray>> = arrayOf(
        TestCase(
            input = "110",
            output = intArrayOf(1, 1, 3)
        ),
        TestCase(
            input = "001011",
            output = intArrayOf(11, 8, 5, 4, 3, 4)
        )
    )

    override fun solve(testCaseInput: String): IntArray {
        return minOperations(testCaseInput)
    }

    private fun minOperations(boxes: String): IntArray {
        val n = boxes.length
        if (n == 1) return intArrayOf(0)

        val prefixShifts = IntArray(n)
        var prefixOnes = if (boxes[0] == '1') 1 else 0
        for (i in 1 until n) {
            prefixShifts[i] = prefixShifts[i - 1] + prefixOnes
            if (boxes[i] == '1') prefixOnes++
        }

        val suffixShifts = IntArray(n)
        var suffixOnes = if (boxes[n - 1] == '1') 1 else 0
        for (i in (n - 2) downTo 0) {
            suffixShifts[i] = suffixShifts[i + 1] + suffixOnes
            if (boxes[i] == '1') suffixOnes++
        }

        val result = IntArray(n)
        for (i in 0 until n) result[i] = prefixShifts[i] + suffixShifts[i]
        return result
    }

    private fun minOperationsBrute(boxes: String): IntArray {
        val n = boxes.length
        if (n == 1) return intArrayOf(0)

        val result = IntArray(n)

        for (i in 0 until n) {
            var shifts = 0

            for (j in 0 until n) {
                if (i == j) continue
                if (boxes[j] == '1') shifts += abs(i - j)
            }

            result[i] = shifts
        }

        return result
    }
}
