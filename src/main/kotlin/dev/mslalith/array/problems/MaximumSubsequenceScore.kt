package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray
import java.util.*
import kotlin.math.max


class MaximumSubsequenceScore : Problem<MaximumSubsequenceScoreParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumSubsequenceScore().run()
    }

    override fun getTestCases(): Array<TestCase<MaximumSubsequenceScoreParams, Long>> = arrayOf(
        TestCase(
            input = MaximumSubsequenceScoreParams(
                nums1 = intArrayOf(1, 3, 3, 2),
                nums2 = intArrayOf(2, 1, 3, 4),
                k = 3
            ),
            output = 12
        ),
        TestCase(
            input = MaximumSubsequenceScoreParams(
                nums1 = intArrayOf(4, 2, 3, 1, 1),
                nums2 = intArrayOf(7, 5, 10, 9, 6),
                k = 1
            ),
            output = 30
        ),
        TestCase(
            input = MaximumSubsequenceScoreParams(
                nums1 = intArrayOf(2, 1, 14, 12),
                nums2 = intArrayOf(11, 7, 13, 6),
                k = 3
            ),
            output = 168
        )
    )

    override fun solve(testCaseInput: MaximumSubsequenceScoreParams): Long {
        return maxScore(testCaseInput.nums1, testCaseInput.nums2, testCaseInput.k)
    }

    private fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val array = Array(nums1.size) { nums1[it] to nums2[it] }
        array.sortWith { one, two -> two.second.compareTo(one.second) }

        val queue = PriorityQueue<Int>()
        var ans = 0L
        var total = 0L

        for (i in array.indices) {
            total += array[i].first
            queue.add(array[i].first)

            if (i >= k) total -= queue.remove().toLong()
            if (i >= k - 1) ans = max(ans, total * array[i].second)
        }

        return ans
    }
}

data class MaximumSubsequenceScoreParams(
    val nums1: IntArray,
    val nums2: IntArray,
    val k: Int
) {
    override fun toString(): String {
        return """
            
            nums1: ${stringFromArray(nums1)}
            nums2: ${stringFromArray(nums2)}
            k: $k
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MaximumSubsequenceScoreParams

        if (!nums1.contentEquals(other.nums1)) return false
        if (!nums2.contentEquals(other.nums2)) return false
        if (k != other.k) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nums1.contentHashCode()
        result = 31 * result + nums2.contentHashCode()
        result = 31 * result + k
        return result
    }
}
