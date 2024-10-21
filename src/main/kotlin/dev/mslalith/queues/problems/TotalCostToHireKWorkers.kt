package dev.mslalith.queues.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray
import java.util.*
import kotlin.math.min

class TotalCostToHireKWorkers : TestCaseProblem<TotalCostToHireKWorkersParams, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = TotalCostToHireKWorkers().runAll()
    }

    override fun getTestCases(): Array<TestCase<TotalCostToHireKWorkersParams, Long>> = arrayOf(
        TestCase(
            input = TotalCostToHireKWorkersParams(
                costs = intArrayOf(17, 12, 10, 2, 7, 2, 11, 20, 8),
                k = 3,
                candidates = 4
            ),
            output = 11
        ),
        TestCase(
            input = TotalCostToHireKWorkersParams(
                costs = intArrayOf(1, 2, 4, 1),
                k = 3,
                candidates = 3
            ),
            output = 4
        )
    )

    override fun solve(testCaseInput: TotalCostToHireKWorkersParams): Long {
        return totalCost(testCaseInput.costs, testCaseInput.k, testCaseInput.candidates)
    }

    private fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
        val n = costs.size
        val pq1 = PriorityQueue<Int>()
        val pq2 = PriorityQueue<Int>()

        var sum = 0L
        var start = 0
        var end = n - 1

        repeat(k) {
            while (pq1.size < candidates && start <= end) pq1.add(costs[start++])
            while (pq2.size < candidates && start <= end) pq2.add(costs[end--])

            sum += when {
                pq1.isNotEmpty() && pq2.isNotEmpty() -> if (pq1.peek() <= pq2.peek()) pq1.remove() else pq2.remove()
                pq1.isNotEmpty() -> pq1.remove()
                else -> pq2.remove()
            }
        }

        return sum
    }

    private fun totalCostNaive(costs: IntArray, k: Int, candidates: Int): Long {
        val n = costs.size
        var sum = 0L

        var i: Int
        var count: Int

        for (x in 1..k) {
            var leftMin = 0
            var rightMin = n - 1

            i = 0
            count = 0
            while (i < n && count < candidates) {
                if (costs[i] < costs[leftMin]) leftMin = i
                count++
                i++
            }

            i = n - 1
            count = 0
            while (i >= 0 && count < candidates) {
                if (costs[i] < costs[rightMin]) rightMin = i
                count++
                i--
            }

            if (costs[leftMin] == costs[rightMin]) {
                val index = min(leftMin, rightMin)
                sum += costs[index]
                costs[index] = Int.MAX_VALUE
            } else if (costs[leftMin] < costs[rightMin]) {
                sum += costs[leftMin]
                costs[leftMin] = Int.MAX_VALUE
            } else {
                sum += costs[rightMin]
                costs[rightMin] = Int.MAX_VALUE
            }
        }

        return sum
    }
}

data class TotalCostToHireKWorkersParams(
    val costs: IntArray,
    val k: Int,
    val candidates: Int
) {
    override fun toString(): String = """
            
            costs: ${stringFromArray(costs)}
            k: $k
            candidates: $candidates
        """.trimIndent()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TotalCostToHireKWorkersParams

        if (!costs.contentEquals(other.costs)) return false
        if (k != other.k) return false
        if (candidates != other.candidates) return false

        return true
    }

    override fun hashCode(): Int {
        var result = costs.contentHashCode()
        result = 31 * result + k
        result = 31 * result + candidates
        return result
    }
}
