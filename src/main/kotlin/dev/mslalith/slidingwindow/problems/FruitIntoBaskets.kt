package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FruitIntoBaskets : TestCaseProblem<IntArray, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FruitIntoBaskets().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 1),
            output = 3
        ),
        TestCase(
            input = intArrayOf(0, 1, 2, 2),
            output = 3
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 2, 2),
            output = 4
        )
    )
    
    override fun solve(testCaseInput: IntArray): Int {
        return totalFruit(testCaseInput)
    }

    private fun totalFruit(fruits: IntArray): Int {
        val map = hashMapOf<Int, Int>()

        var start = 0
        var end = 0
        var maxLen = 0

        while (end < fruits.size) {
            map[fruits[end]] = map.getOrDefault(fruits[end], 0) + 1

            while (map.size >= 3) {
                map[fruits[start]] = map.getValue(fruits[start]) - 1
                if (map.get(fruits[start]) == 0) map.remove(fruits[start])
                start++
            }

            val currLen = end - start + 1
            maxLen = maxOf(maxLen, currLen)
            end++
        }

        return maxLen
    }
}
