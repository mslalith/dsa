package dev.mslalith.prefixsum.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.prefixsum.problems.CountOfInterestingSubarrays.CountOfInterestingSubarraysParams


class CountOfInterestingSubarrays : TestCaseProblem<CountOfInterestingSubarraysParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountOfInterestingSubarrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CountOfInterestingSubarraysParams, Long>> = arrayOf(
        TestCase(
            input = CountOfInterestingSubarraysParams(
                nums = listOf(3, 2, 4),
                modulo = 2,
                k = 1
            ),
            output = 3
        ),
        TestCase(
            input = CountOfInterestingSubarraysParams(
                nums = listOf(3, 1, 9, 6),
                modulo = 3,
                k = 0
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: CountOfInterestingSubarraysParams): Long {
        return countInterestingSubarrays(testCaseInput.nums, testCaseInput.modulo, testCaseInput.k)
    }

    private fun countInterestingSubarrays(nums: List<Int>, modulo: Int, k: Int): Long {
        var interestingSubarrays = 0L
        var prefixCount = 0L

        val freqMap = HashMap<Long, Long>()
        freqMap[0] = 1

        for (num in nums) {
            if (num % modulo == k) prefixCount++

            prefixCount %= modulo
            val key = (prefixCount - k + modulo) % modulo
            if (freqMap.containsKey(key)) interestingSubarrays += freqMap.get(key)!!

            freqMap[prefixCount] = freqMap.getOrDefault(prefixCount, 0L) + 1
        }

        return interestingSubarrays
    }

    private fun countInterestingSubarraysNaive(nums: List<Int>, modulo: Int, k: Int): Long {
        val n = nums.size
        var interestingCount = 0L

        for (i in 0 until n) {
            var count = 0
            for (j in i until n) {
                if (nums[j] % modulo == k) count++
                if (count % modulo == k) interestingCount++
            }
        }

        return interestingCount
    }

    data class CountOfInterestingSubarraysParams(
        val nums: List<Int>,
        val modulo: Int,
        val k: Int
    ) {
        override fun toString(): String {
            return """
                nums: $nums
                modulo: $modulo
                k: $k
            """.trimIndent()
        }
    }
}
