package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

// TODO: revisit and understand
class CountGoodTripletsInArray : TestCaseProblem<Pair<IntArray, IntArray>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountGoodTripletsInArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 0, 1, 3) to intArrayOf(0, 1, 2, 3),
            output = 1
        ),
        TestCase(
            input = intArrayOf(4, 0, 1, 3, 2) to intArrayOf(4, 1, 0, 2, 3),
            output = 4
        ),
        TestCase(
            input = intArrayOf(13, 14, 10, 2, 12, 3, 9, 11, 15, 8, 4, 7, 0, 6, 5, 1) to intArrayOf(
                8,
                7,
                9,
                5,
                6,
                14,
                15,
                10,
                2,
                11,
                4,
                13,
                3,
                12,
                1,
                0
            ),
            output = 77
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Long {
        return goodTriplets(testCaseInput.first, testCaseInput.second)
    }

    private fun goodTriplets(nums1: IntArray, nums2: IntArray): Long {
        val n = nums1.size

        val nums2IndexMap = IntArray(n)
        for (i in 0 until n) nums2IndexMap[nums2[i]] = i

        val left = LongArray(n)
        val right = LongArray(n)

        var tree = FenwickTree(n + 2)
        for (i in 0 until n) {
            val idx = nums2IndexMap[nums1[i]]
            left[i] = tree.query(idx - 1).toLong()
            tree.update(idx, 1)
        }

        tree = FenwickTree(n + 2)
        for (i in (n - 1) downTo 0) {
            val idx = nums2IndexMap[nums1[i]]
            right[i] = (tree.query(n) - tree.query(idx)).toLong()
            tree.update(idx, 1)
        }

        return (0 until n).sumOf { left[it] * right[it] }
    }

    private fun goodTripletsNaive(nums1: IntArray, nums2: IntArray): Long {
        val n = nums1.size

        val nums2IndexMap = hashMapOf<Int, Int>()
        for (i in 0 until n) nums2IndexMap[nums2[i]] = i

        var count = 0L
        val set = hashSetOf<Int>()

        for (i in 1 until (n - 1)) {
            val num2Idx = nums2IndexMap.getValue(nums1[i])
            if (num2Idx == 0 || num2Idx == n - 1) continue

            for (j in 0 until i) set += nums1[j]
            val leftCommonCount = (0 until num2Idx).count { nums2[it] in set }
            set.clear()
            if (leftCommonCount == 0) continue

            for (j in (i + 1) until n) set += nums1[j]
            val rightCommonCount = ((num2Idx + 1) until n).count { nums2[it] in set }
            set.clear()
            if (rightCommonCount == 0) continue

            count += leftCommonCount * rightCommonCount
        }

        return count
    }
}

private class FenwickTree(size: Int) {
    private val bit: IntArray = IntArray(size)

    fun update(index: Int, value: Int) {
        var i = index
        i++
        while (i < bit.size) {
            bit[i] += value
            i += i and -i
        }
    }

    fun query(index: Int): Int {
        var i = index
        i++
        var sum = 0
        while (i > 0) {
            sum += bit[i]
            i -= i and -i
        }
        return sum
    }
}