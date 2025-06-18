package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import java.util.*
import kotlin.math.abs


class DivideArrayIntoArraysWithMaxDifference : TestCaseProblem<Pair<IntArray, Int>, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DivideArrayIntoArraysWithMaxDifference().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Array<IntArray>>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, 4, 8, 7, 9, 3, 5, 1) to 2,
            output = arrayOf(
                intArrayOf(1, 1, 3),
                intArrayOf(3, 4, 5),
                intArrayOf(7, 8, 9)
            )
        ),
        TestCase(
            input = intArrayOf(2, 4, 2, 2, 5, 2) to 2,
            output = arrayOf()
        ),
        TestCase(
            input = intArrayOf(4, 2, 9, 8, 2, 12, 7, 12, 10, 5, 8, 5, 5, 7, 9, 2, 5, 11) to 14,
            output = arrayOf(
                intArrayOf(2, 2, 12),
                intArrayOf(4, 8, 5),
                intArrayOf(5, 9, 7),
                intArrayOf(7, 8, 5),
                intArrayOf(5, 9, 10),
                intArrayOf(11, 12, 2)
            ),
            otherAcceptableOutputs = listOf(
                arrayOf(
                    intArrayOf(2, 2, 2),
                    intArrayOf(4, 5, 5),
                    intArrayOf(5, 5, 7),
                    intArrayOf(7, 8, 8),
                    intArrayOf(9, 9, 10),
                    intArrayOf(11, 12, 12)
                )
            )
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Array<IntArray> {
        return divideArray(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun divideArray(nums: IntArray, k: Int): Array<IntArray> {
        nums.sort()
        val n = nums.size

        val result = Array(n / 3) { IntArray(3) }
        var i = 0

        while (i < n) {
            val a = nums[i]
            val b = nums[i + 1]
            val c = nums[i + 2]

            if (c - a > k) return emptyArray()

            result[i / 3] = intArrayOf(a, b, c)
            i += 3
        }


        return result
    }
}
