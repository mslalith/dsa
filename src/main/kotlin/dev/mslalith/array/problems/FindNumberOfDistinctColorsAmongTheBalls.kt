package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindNumberOfDistinctColorsAmongTheBalls : TestCaseProblem<Pair<Int, Array<IntArray>>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindNumberOfDistinctColorsAmongTheBalls().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, IntArray>> = arrayOf(
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(1, 4),
                intArrayOf(2, 5),
                intArrayOf(1, 3),
                intArrayOf(3, 4)
            ),
            output = intArrayOf(1, 2, 2, 3)
        ),
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 2),
                intArrayOf(3, 4),
                intArrayOf(4, 5)
            ),
            output = intArrayOf(1, 2, 2, 3, 4)
        ),
        TestCase(
            input = 1 to arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 4),
                intArrayOf(1, 1),
                intArrayOf(1, 4),
                intArrayOf(1, 1)
            ),
            output = intArrayOf(1, 2, 1, 2, 1)
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): IntArray {
        return queryResults(testCaseInput.first, testCaseInput.second)
    }

    private fun queryResults(limit: Int, queries: Array<IntArray>): IntArray {
        val result = IntArray(queries.size)
        val ballToColorMap = hashMapOf<Int, Int>()
        val colorCountMap = hashMapOf<Int, Int>()

        for (i in queries.indices) {
            val (ball, color) = queries[i]

            if (ball in ballToColorMap) {
                val prevColor = ballToColorMap.getValue(ball)
                colorCountMap[prevColor] = colorCountMap.getValue(prevColor) - 1
                if (colorCountMap[prevColor] == 0) colorCountMap -= prevColor
            }

            ballToColorMap[ball] = color
            colorCountMap[color] = colorCountMap.getOrDefault(color, 0) + 1

            result[i] = colorCountMap.size
        }

        return result
    }
}
