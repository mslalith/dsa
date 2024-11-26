package dev.mslalith.stacks.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*

class NextGreaterElementI : TestCaseProblem<Pair<IntArray, IntArray>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = NextGreaterElementI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 1, 2) to intArrayOf(1, 3, 4, 2),
            output = intArrayOf(-1, 3, -1)
        ),
        TestCase(
            input = intArrayOf(2, 4) to intArrayOf(1, 2, 3, 4),
            output = intArrayOf(3, -1)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): IntArray {
        return nextGreaterElement(testCaseInput.first, testCaseInput.second)
    }

    private fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
        // num -> index
        val map = hashMapOf<Int, Int>()
        for (i in nums2.indices) map[nums2[i]] = i

        fun nge(array: IntArray): IntArray {
            val n = array.size
            val stack = Stack<Int>()
            val nge = IntArray(n)

            for (i in (n - 1) downTo 0) {
                val num = array[i]
                while (stack.isNotEmpty() && stack.peek() <= num) stack.pop()
                nge[i] = if (stack.isEmpty()) -1 else stack.peek()
                stack.push(array[i])
            }

            return nge
        }

        val result = IntArray(nums1.size) { -1 }
        val nge = nge(nums2)

        for (i in nums1.indices) {
            val num = nums1[i]
            if (num in map) {
                val index = map.getValue(num)
                result[i] = nge[index]
            }
        }

        return result
    }
}
