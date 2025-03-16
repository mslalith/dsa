package dev.mslalith.binarysearch.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.sqrt

class MinimumTimeToRepairCars : TestCaseProblem<Pair<IntArray, Int>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumTimeToRepairCars().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 2, 3, 1) to 10,
            output = 16
        ),
        TestCase(
            input = intArrayOf(5, 1, 8) to 6,
            output = 16
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Long {
        return repairCars(testCaseInput.first, testCaseInput.second)
    }

    private fun repairCars(ranks: IntArray, cars: Int): Long {

        fun canRepair(time: Long): Boolean {
            var totalCarsRepaired = 0

            for (rank in ranks) {
                totalCarsRepaired += sqrt(time / rank.toDouble()).toInt()
                if (totalCarsRepaired >= cars) return true
            }

            return false
        }

        var left = 0L
        var right = ranks.max().toLong() * cars * cars

        while (left < right) {
            val mid = left + (right - left) / 2
            if (canRepair(mid)) right = mid
            else left = mid + 1
        }

        return left
    }
}