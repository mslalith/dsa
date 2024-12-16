package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class DuplicateZeros : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DuplicateZeros().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 0, 2, 3, 0, 4, 5, 0),
            output = intArrayOf(1, 0, 0, 2, 3, 0, 0, 4)
        ),
        TestCase(
            input = intArrayOf(1, 2, 3),
            output = intArrayOf(1, 2, 3)
        ),
        TestCase(
            input = intArrayOf(0, 0, 0, 0, 0, 0, 0),
            output = intArrayOf(0, 0, 0, 0, 0, 0, 0)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        val clone = testCaseInput.createClone()
        duplicateZeros(clone)
        return clone
    }

    private fun duplicateZeros(arr: IntArray) {
        val n = arr.size

        var zeroCount = 0
        for (num in arr) if (num == 0) zeroCount++

        var left = n - 1
        var right = n + zeroCount - 1

        while (left < right) {
            if (arr[left] != 0) {
                if (right < n) arr[right] = arr[left]
            } else {
                if (right < n) arr[right] = arr[left]
                right--
                if (right < n) arr[right] = arr[left]
            }
            left--
            right--
        }
    }

    private fun duplicateZerosNaive(arr: IntArray) {
        val n = arr.size
        val result = IntArray(n)

        var i = 0
        var j = 0
        while (i < n) {
            val num = arr[j++]
            if (num != 0) result[i++] = num
            else {
                result[i++] = 0
                if (i < n) result[i++] = 0
            }
        }

        for (k in arr.indices) arr[k] = result[k]
    }
}
