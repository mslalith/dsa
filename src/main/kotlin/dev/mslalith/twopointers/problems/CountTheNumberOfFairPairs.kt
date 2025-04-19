package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.twopointers.problems.CountTheNumberOfFairPairs.CountTheNumberOfFairPairsParams
import dev.mslalith.utils.createClone
import dev.mslalith.utils.stringFromArray

class CountTheNumberOfFairPairs : TestCaseProblem<CountTheNumberOfFairPairsParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountTheNumberOfFairPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CountTheNumberOfFairPairsParams, Long>> = arrayOf(
        TestCase(
            input = CountTheNumberOfFairPairsParams(
                nums = intArrayOf(0, 1, 7, 4, 4, 5),
                lower = 3,
                upper = 6
            ),
            output = 6
        ),
        TestCase(
            input = CountTheNumberOfFairPairsParams(
                nums = intArrayOf(1, 7, 9, 2, 5),
                lower = 11,
                upper = 11
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: CountTheNumberOfFairPairsParams): Long {
        return countFairPairs(testCaseInput.nums.createClone(), testCaseInput.lower, testCaseInput.upper)
    }

    private fun countFairPairs(nums: IntArray, lower: Int, upper: Int): Long {
        val n = nums.size
        nums.sort()

        fun lowerBound(start: Int, end: Int, target: Int): Int {
            var low = start
            var high = end

            while (low < high) {
                val mid = low + (high - low) / 2
                when {
                    nums[mid] < target -> low = mid + 1
                    else -> high = mid
                }
            }

            return low
        }

        fun upperBound(start: Int, end: Int, target: Int): Int {
            var low = start
            var high = end

            while (low < high) {
                val mid = low + (high - low) / 2
                when {
                    nums[mid] <= target -> low = mid + 1
                    else -> high = mid
                }
            }

            return low
        }

        return (0 until (n - 1)).sumOf {
            val low = lowerBound(it + 1, n, lower - nums[it])
            val high = upperBound(it + 1, n, upper - nums[it])
            (high - low).toLong()
        }
    }

    private fun countFairPairsNaive(nums: IntArray, lower: Int, upper: Int): Long {
        val n = nums.size

        var count = 0L

        for (i in 0 until n) {
            for (j in (i + 1) until n) {
                val sum = nums[i] + nums[j]
                if (sum in lower..upper) count++
            }
        }

        return count
    }

    data class CountTheNumberOfFairPairsParams(
        val nums: IntArray,
        val lower: Int,
        val upper: Int
    ) {
        override fun toString(): String {
            return """
                nums: ${stringFromArray(nums)}
                lower: $lower
                upper: $upper
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CountTheNumberOfFairPairsParams

            if (lower != other.lower) return false
            if (upper != other.upper) return false
            if (!nums.contentEquals(other.nums)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = lower
            result = 31 * result + upper
            result = 31 * result + nums.contentHashCode()
            return result
        }
    }
}
