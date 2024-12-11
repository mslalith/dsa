package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue

class TopKFrequentElements : TestCaseProblem<Pair<IntArray, Int>, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TopKFrequentElements().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 1, 2, 2, 3) to 2,
            output = intArrayOf(1, 2)
        ),
        TestCase(
            input = intArrayOf(1) to 1,
            output = intArrayOf(1)
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): IntArray {
        return topKFrequent(testCaseInput.first, testCaseInput.second)
    }

    private fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequencyMap = hashMapOf<Int, Int>()
        for (num in nums) frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1

        // (value, frequency)
        val pq = PriorityQueue<Pair<Int, Int>> { a, b -> b.second - a.second }
        val result = IntArray(k)

        for ((key, value) in frequencyMap) pq.add(key to value)

        for (i in 0 until k) result[i] = pq.poll().first

        return result
    }
}
