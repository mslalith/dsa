package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import java.util.*

class LastStoneWeight : Problem<IntArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LastStoneWeight().run()
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
            val x = queue.poll()
            if (x != null && y != null) {
                if (x < y) queue.add(y - x)
            } else return y
        }

        return 0
    }
}