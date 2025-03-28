package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import java.util.*

class LastStoneWeight : TestCaseProblem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LastStoneWeight().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 7, 4, 1, 8, 1),
            output = 1
        ),
        TestCase(
            input = intArrayOf(1),
            output = 1
        )
    )

    override fun solve(testCaseInput: IntArray): Int {
        return lastStoneWeight(testCaseInput)
    }

    private fun lastStoneWeight(stones: IntArray): Int {
        val queue = PriorityQueue<Int>(Collections.reverseOrder())
        stones.forEach { queue.add(it) }

        while (queue.isNotEmpty()) {
            val y = queue.poll()
            if (queue.isEmpty()) return y

            val x = queue.poll()
            if (x < y) queue.add(y - x)
        }

        return 0
    }
}
