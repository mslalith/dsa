package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.min

class MinimumRecolorsToGetKConsecutiveBlackBlocks : TestCaseProblem<Pair<String, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumRecolorsToGetKConsecutiveBlackBlocks().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "WBBWWBBWBW" to 7,
            output = 3
        ),
        TestCase(
            input = "WBWBBBW" to 2,
            output = 0
        )
    )
    
    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return minimumRecolors(testCaseInput.first, testCaseInput.second)
    }

    private fun minimumRecolors(blocks: String, k: Int): Int {
        val n = blocks.length
        var changed = 0
        var minChanged = Int.MAX_VALUE

        var left = 0
        var right = 0

        while (right < k) {
            if (blocks[right] == 'W') changed++
            right++
        }

        minChanged = min(minChanged, changed)

        while (right < n) {
            if (blocks[left++] == 'W') changed--
            if (blocks[right++] == 'W') changed++
            minChanged = min(minChanged, changed)
        }

        return minChanged
    }
}
