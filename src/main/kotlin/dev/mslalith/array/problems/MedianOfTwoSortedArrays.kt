package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class MedianOfTwoSortedArrays : TestCaseProblem<Pair<IntArray, IntArray>, Double>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MedianOfTwoSortedArrays().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Double>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 3) to intArrayOf(2),
            output = 2.0
        ),
        TestCase(
            input = intArrayOf(1, 2) to intArrayOf(3, 4),
            output = 2.5
        ),
        TestCase(
            input = intArrayOf() to intArrayOf(1),
            output = 1.0
        )
    )
    
    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Double {
        return findMedianSortedArrays(testCaseInput.first, testCaseInput.second)
    }

    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size

        val medianIndex = (m + n) / 2
        val isMedianSingle = (m + n) % 2 == 1

        var left = 0
        var right = 0
        var size = -1

        var last = 0
        var prev = 0

        while (left < m || right < n) {
            if (size == medianIndex) {
                return if (isMedianSingle) prev.toDouble() else (last + prev) / 2.0
            }

            if (left < m && right < n) {
                if (nums1[left] <= nums2[right]) {
                    size++
                    last = prev
                    prev = nums1[left]
                    left++
                } else {
                    size++
                    last = prev
                    prev = nums2[right]
                    right++
                }
            } else if (left < m) {
                size++
                last = prev
                prev = nums1[left]
                left++
            } else {
                size++
                last = prev
                prev = nums2[right]
                right++
            }
        }

        if (size == medianIndex) {
            return if (isMedianSingle) prev.toDouble() else (last + prev) / 2.0
        }

        return 0.0
    }

    private fun findMedianSortedArraysNaive(nums1: IntArray, nums2: IntArray): Double {
        val m = nums1.size
        val n = nums2.size

        val combined = IntArray(m + n)

        var insertIndex = 0
        for (num in nums1) combined[insertIndex++] = num
        for (num in nums2) combined[insertIndex++] = num

        combined.sort()

        return if (combined.size % 2 == 0) {
            val index = combined.size / 2
            (combined[index] + combined[index - 1]) / 2.0
        } else {
            combined[combined.size / 2].toDouble()
        }
    }
}
