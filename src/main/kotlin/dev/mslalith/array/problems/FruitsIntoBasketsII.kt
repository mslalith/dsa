package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FruitsIntoBasketsII : TestCaseProblem<Pair<IntArray, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FruitsIntoBasketsII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 2, 5) to intArrayOf(3, 5, 4),
            output = 1
        ),
        TestCase(
            input = intArrayOf(3, 6, 1) to intArrayOf(6, 4, 7),
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Int {
        return numOfUnplacedFruits(testCaseInput.first, testCaseInput.second)
    }

    private fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
        val n = fruits.size
        var filled = 0

        for (i in 0..<n) {
            for (j in 0..<n) {
                if (fruits[i] <= baskets[j]) {
                    filled++
                    baskets[j] = -1
                    break
                }
            }
        }

        return n - filled
    }
}
