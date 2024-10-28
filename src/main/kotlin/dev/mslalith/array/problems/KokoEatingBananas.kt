package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.ceil

class KokoEatingBananas : TestCaseProblem<Pair<IntArray, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KokoEatingBananas().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3,6,7,11) to 8,
            output = 4
        ),
        TestCase(
            input = intArrayOf(30,11,23,4,20) to 5,
            output = 30
        ),
        TestCase(
            input = intArrayOf(30,11,23,4,20) to 6,
            output = 23
        ),
        TestCase(
            input = intArrayOf(312884470) to 312884469,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return minEatingSpeed(testCaseInput.first, testCaseInput.second)
    }

    private fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val max = piles.max()
        var left = 1
        var right = max

        while (left <= right) {
            val mid = left + (right - left) / 2
            val hours = piles.sumOf { ceil(it / mid.toDouble()) }

            if (hours > h) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return left
    }
}
