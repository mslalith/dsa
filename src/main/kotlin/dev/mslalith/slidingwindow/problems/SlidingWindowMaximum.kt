package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SlidingWindowMaximum : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SlidingWindowMaximum().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7) to 3,
            output = intArrayOf(3, 3, 5, 5, 6, 7)
        ),
        TestCase(
            input = intArrayOf(1) to 1,
            output = intArrayOf(1)
        ),
        TestCase(
            input = intArrayOf(1, -1) to 1,
            output = intArrayOf(1, -1)
        ),
        TestCase(
            input = intArrayOf(7, 2, 4) to 2,
            output = intArrayOf(7, 4)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return maxSlidingWindow(testCaseInput.first, testCaseInput.second)
    }

    private fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        if (n == 1) return nums
        if (k == 1) return nums

        val deque = ArrayDeque<Int>()
        val result = IntArray(n - k + 1)
        var insert = 0

        for (i in 0 until n) {
            // keep elements within the window by removing out of bound values
            if (deque.isNotEmpty() && deque.first() <= i - k) deque.removeFirst()

            // keep decreasing values
            while (deque.isNotEmpty() && nums[deque.last()] <= nums[i]) deque.removeLast()
            deque.addLast(i)

            if (i >= k - 1) result[insert++] = nums[deque.first()]
        }

        return result
    }
}
