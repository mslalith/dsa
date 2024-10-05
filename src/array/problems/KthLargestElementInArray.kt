package src.array.problems

import src.core.Problem
import src.core.TestCase
import java.util.PriorityQueue

class KthLargestElementInArray : Problem<Pair<IntArray, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KthLargestElementInArray().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(3,2,1,5,6,4) to 2,
            output = 5
        ),
        TestCase(
            input = intArrayOf(3,2,3,1,2,4,5,5,6) to 4,
            output = 4
        ),
        TestCase(
            input = intArrayOf(2,1) to 1,
            output = 2
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return findKthLargest(testCaseInput.first, testCaseInput.second)
    }

    private fun findKthLargest(nums: IntArray, k: Int): Int {
        val queue = PriorityQueue<Int>(Comparator.reverseOrder())
        nums.forEach { queue.add(it) }

        var num = queue.remove()
        repeat(k - 1) {
            num = queue.remove()
        }
        return num
    }

    private fun findKthLargestNaive(nums: IntArray, k: Int): Int {
        nums.sort()
        return nums[nums.size - k]
    }
}
