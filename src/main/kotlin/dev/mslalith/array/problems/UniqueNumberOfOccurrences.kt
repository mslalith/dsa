package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UniqueNumberOfOccurrences : TestCaseProblem<IntArray, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UniqueNumberOfOccurrences().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 2, 1, 1, 3),
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2),
            output = false
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return uniqueOccurrences(testCaseInput)
    }

    private fun uniqueOccurrences(arr: IntArray): Boolean {
        val hashMap = hashMapOf<Int, Int>()
        arr.forEach { hashMap[it] = hashMap.getOrDefault(it, 0) + 1 }
        return hashMap.values.size == hashMap.values.toHashSet().size
    }
}
