package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class DailyTemperatures : TestCaseProblem<IntArray, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DailyTemperatures().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(73, 74, 75, 71, 69, 72, 76, 73),
            output = intArrayOf(1, 1, 4, 2, 1, 1, 0, 0)
        ),
        TestCase(
            input = intArrayOf(30, 40, 50, 60),
            output = intArrayOf(1, 1, 1, 0)
        ),
        TestCase(
            input = intArrayOf(30, 60, 90),
            output = intArrayOf(1, 1, 0)
        )
    )

    override fun solve(testCaseInput: IntArray): IntArray {
        return dailyTemperatures(testCaseInput)
    }

    private fun dailyTemperatures(temperatures: IntArray): IntArray {
        if (temperatures.isEmpty()) return intArrayOf()

        val n = temperatures.size
        val stack = Stack<Int>()
        val ans = IntArray(n) { 0 }

        for (i in (n - 1) downTo 0) {
            while (stack.isNotEmpty() && temperatures[i] >= temperatures[stack.peek()]) stack.pop()
            if (stack.isNotEmpty()) ans[i] = stack.peek() - i
            stack.push(i)
        }

        return ans
    }

    private fun dailyTemperaturesNaive(temperatures: IntArray): IntArray {
        val ans = IntArray(temperatures.size) { 0 }

        temperatures.forEachIndexed { index, temp ->
            for (i in (index + 1) until temperatures.size) {
                if (temperatures[i] > temp) {
                    ans[index] = i - index
                    break
                }
            }
        }

        return ans
    }
}
