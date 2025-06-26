package dev.mslalith.array.problems

import dev.mslalith.array.problems.KthSmallestProductOfTwoSortedArrays.KthSmallestProductOfTwoSortedArraysParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray

class KthSmallestProductOfTwoSortedArrays : TestCaseProblem<KthSmallestProductOfTwoSortedArraysParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KthSmallestProductOfTwoSortedArrays().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<KthSmallestProductOfTwoSortedArraysParams, Long>> = arrayOf(
        TestCase(
            input = KthSmallestProductOfTwoSortedArraysParams(
                nums1 = intArrayOf(2, 5),
                nums2 = intArrayOf(3, 4),
                k = 2
            ),
            output = 8
        ),
        TestCase(
            input = KthSmallestProductOfTwoSortedArraysParams(
                nums1 = intArrayOf(-4, -2, 0, 3),
                nums2 = intArrayOf(2, 4),
                k = 6
            ),
            output = 0
        ),
        TestCase(
            input = KthSmallestProductOfTwoSortedArraysParams(
                nums1 = intArrayOf(-2, -1, 0, 1, 2),
                nums2 = intArrayOf(-3, -1, 2, 4, 5),
                k = 3
            ),
            output = -6
        )
    )

    override fun solve(testCaseInput: KthSmallestProductOfTwoSortedArraysParams): Long {
        return kthSmallestProduct(testCaseInput.nums1, testCaseInput.nums2, testCaseInput.k)
    }

    private fun kthSmallestProduct(nums1: IntArray, nums2: IntArray, k: Long): Long {

        fun countProducts(target: Long): Long {
            var count = 0L

            for (num1 in nums1) {
                if (num1 == 0) {
                    if (target >= 0) count += nums2.size
                    continue
                }

                var low = 0
                var high = nums2.size

                while (low < high) {
                    val mid = low + (high - low) / 2
                    val product = num1.toLong() * nums2[mid]
                    if (product <= target) {
                        if (num1 > 0) low = mid + 1
                        else high = mid
                    } else {
                        if (num1 > 0) high = mid
                        else low = mid + 1
                    }
                }

                count += if (num1 > 0) low else nums2.size - low
            }

            return count
        }

        var left = -10_000_000_000L
        var right = 10_000_000_000L

        while (left < right) {
            val mid = left + (right - left) / 2
            if (countProducts(mid) < k) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }

    data class KthSmallestProductOfTwoSortedArraysParams(
        val nums1: IntArray,
        val nums2: IntArray,
        val k: Long
    ) {
        override fun toString(): String {
            return """
                
                num1: ${stringFromArray(nums1)}
                num2: ${stringFromArray(nums2)}
                k: $k
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as KthSmallestProductOfTwoSortedArraysParams

            if (k != other.k) return false
            if (!nums1.contentEquals(other.nums1)) return false
            if (!nums2.contentEquals(other.nums2)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = k.hashCode()
            result = 31 * result + nums1.contentHashCode()
            result = 31 * result + nums2.contentHashCode()
            return result
        }
    }
}
