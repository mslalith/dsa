package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray

class MergeSortedArray : TestCaseProblem<MergeSortedArrayParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MergeSortedArray().runAll()
    }

    override fun getTestCases(): Array<TestCase<MergeSortedArrayParams, IntArray>> = arrayOf(
        TestCase(
            input = MergeSortedArrayParams(
                nums1 = intArrayOf(1, 2, 3, 0, 0, 0),
                m = 3,
                nums2 = intArrayOf(2, 5, 6),
                n = 3
            ),
            output = intArrayOf(1, 2, 2, 3, 5, 6)
        ),
        TestCase(
            input = MergeSortedArrayParams(
                nums1 = intArrayOf(1),
                m = 1,
                nums2 = intArrayOf(),
                n = 0
            ),
            output = intArrayOf(1)
        ),
        TestCase(
            input = MergeSortedArrayParams(
                nums1 = intArrayOf(0),
                m = 0,
                nums2 = intArrayOf(1),
                n = 1
            ),
            output = intArrayOf(1)
        ),
        TestCase(
            input = MergeSortedArrayParams(
                nums1 = intArrayOf(4, 5, 6, 0, 0, 0),
                m = 3,
                nums2 = intArrayOf(1, 2, 3),
                n = 3
            ),
            output = intArrayOf(1, 2, 3, 4, 5, 6)
        )
    )

    override fun solve(testCaseInput: MergeSortedArrayParams): IntArray {
        merge(testCaseInput.nums1, testCaseInput.m, testCaseInput.nums2, testCaseInput.n)
        return testCaseInput.nums1
    }

    private fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var i = m - 1
        var j = n - 1
        var k = m + n - 1

        while (i >= 0 && j >= 0) {
            nums1[k--] = if (nums1[i] > nums2[j]) nums1[i--] else nums2[j--]
        }

        while (j >= 0) nums1[k--] = nums2[j--]
    }
}

data class MergeSortedArrayParams(
    val nums1: IntArray,
    val m: Int,
    val nums2: IntArray,
    val n: Int
) {
    override fun toString(): String = """
            
            nums1: ${stringFromArray(nums1)}
            nums2: ${stringFromArray(nums2)}
            m: $m
            n: $n
        """.trimIndent()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MergeSortedArrayParams

        if (!nums1.contentEquals(other.nums1)) return false
        if (m != other.m) return false
        if (!nums2.contentEquals(other.nums2)) return false
        if (n != other.n) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nums1.contentHashCode()
        result = 31 * result + m
        result = 31 * result + nums2.contentHashCode()
        result = 31 * result + n
        return result
    }
}
