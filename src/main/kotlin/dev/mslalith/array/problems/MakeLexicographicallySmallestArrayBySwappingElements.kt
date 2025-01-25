package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import java.util.*


class MakeLexicographicallySmallestArrayBySwappingElements : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MakeLexicographicallySmallestArrayBySwappingElements().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 5, 3, 9, 8) to 2,
            output = intArrayOf(1, 3, 5, 8, 9)
        ),
        TestCase(
            input = intArrayOf(1, 7, 6, 18, 2, 1) to 3,
            output = intArrayOf(1, 6, 7, 18, 1, 2)
        ),
        TestCase(
            input = intArrayOf(1, 7, 28, 19, 10) to 3,
            output = intArrayOf(1, 7, 28, 19, 10)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return lexicographicallySmallestArray(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun lexicographicallySmallestArray(nums: IntArray, limit: Int): IntArray {
        val n = nums.size
        val sortedNums = nums.sorted()

        val indexToQueue = hashMapOf<Int, Queue<Int>>()
        val numToIndex = hashMapOf<Int, Int>()

        var index = 0
        indexToQueue[index] = LinkedList<Int>().apply { add(sortedNums[0]) }
        numToIndex[sortedNums[0]] = index

        for (i in 1 until n) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                index++
                indexToQueue[index] = LinkedList()
            }
            indexToQueue[index]?.add(sortedNums[i])
            numToIndex[sortedNums[i]] = index
        }

        return IntArray(n) {
            val ind = numToIndex.getValue(nums[it])
            indexToQueue.getValue(ind).poll()
        }
    }
}
