package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray
import java.util.*

class FindKPairsWithSmallestSums : TestCaseProblem<FindKPairsWithSmallestSumsParams, List<List<Int>>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindKPairsWithSmallestSums().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FindKPairsWithSmallestSumsParams, List<List<Int>>>> = arrayOf(
        TestCase(
            input = FindKPairsWithSmallestSumsParams(
                nums1 = intArrayOf(1, 7, 11),
                nums2 = intArrayOf(2, 4, 6),
                k = 3
            ),
            output = listOf(
                listOf(1, 2),
                listOf(1, 4),
                listOf(1, 6)
            )
        ),
        TestCase(
            input = FindKPairsWithSmallestSumsParams(
                nums1 = intArrayOf(1, 1, 2),
                nums2 = intArrayOf(1, 2, 3),
                k = 2
            ),
            output = listOf(
                listOf(1, 1),
                listOf(1, 1)
            )
        ),
        TestCase(
            input = FindKPairsWithSmallestSumsParams(
                nums1 = intArrayOf(1, 2, 4, 5, 6),
                nums2 = intArrayOf(3, 5, 7, 9),
                k = 3
            ),
            output = listOf(
                listOf(1, 3),
                listOf(2, 3),
                listOf(1, 5)
            )
        )
    )

    override fun solve(testCaseInput: FindKPairsWithSmallestSumsParams): List<List<Int>> {
        return kSmallestPairs(testCaseInput.nums1, testCaseInput.nums2, testCaseInput.k)
    }

    private fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val queue = PriorityQueue<Pair<Int, Int>>(k) { a, b ->
            a.first - b.first
        }

        for (num in nums1) queue.add((num + nums2[0]) to 0)

        val result = mutableListOf<List<Int>>()

        var i = 0
        while (i < k && queue.isNotEmpty()) {
            val (sum, index) = queue.poll()

            val num = sum - nums2[index]
            result.add(listOf(num, nums2[index]))

            val next = index + 1
            if (next < nums2.size) {
                queue.add((num + nums2[next]) to next)
            }
            i++
        }

        return result
    }

    private fun kSmallestPairsNaive(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val queue = PriorityQueue<Pair<Int, Int>>(k) { a, b ->
            (a.first + a.second) - (b.first + b.second)
        }

        for (i in nums1.indices) {
            for (j in nums2.indices) {
                queue.add(nums1[i] to nums2[j])
            }
        }

        val result = mutableListOf<List<Int>>()

        for (i in 0 until k) {
            if (queue.isEmpty()) break
            val (a, b) = queue.poll()
            result.add(listOf(a, b))
        }

        return result
    }
}

data class FindKPairsWithSmallestSumsParams(
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

        other as FindKPairsWithSmallestSumsParams

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
