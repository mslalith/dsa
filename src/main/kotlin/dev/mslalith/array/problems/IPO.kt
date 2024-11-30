package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray
import java.util.*


class IPO : TestCaseProblem<IPOParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IPO().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IPOParams, Int>> = arrayOf(
        TestCase(
            input = IPOParams(
                k = 2,
                w = 0,
                profits = intArrayOf(1, 2, 3),
                capital = intArrayOf(0, 1, 1)
            ),
            output = 4
        ),
        TestCase(
            input = IPOParams(
                k = 3,
                w = 0,
                profits = intArrayOf(1, 2, 3),
                capital = intArrayOf(0, 1, 2)
            ),
            output = 6
        ),
        TestCase(
            input = IPOParams(
                k = 1,
                w = 2,
                profits = intArrayOf(1, 2, 3),
                capital = intArrayOf(1, 1, 2)
            ),
            output = 5
        ),
        TestCase(
            input = IPOParams(
                k = 3,
                w = 0,
                profits = intArrayOf(1, 2, 3),
                capital = intArrayOf(0, 1, 3)
            ),
            output = 6
        ),
        TestCase(
            input = IPOParams(
                k = 11,
                w = 11,
                profits = intArrayOf(1, 2, 3),
                capital = intArrayOf(11, 12, 13)
            ),
            output = 17
        ),
        TestCase(
            input = IPOParams(
                k = 1,
                w = 0,
                profits = intArrayOf(1, 2, 3),
                capital = intArrayOf(1, 1, 2)
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: IPOParams): Int {
        return findMaximizedCapital(testCaseInput.k, testCaseInput.w, testCaseInput.profits, testCaseInput.capital)
    }

    private fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val n = profits.size

        // (capital, profit)
        val projects = PriorityQueue<Pair<Int, Int>> { a, b -> a.first - b.first }
        val profitQueue = PriorityQueue<Int> { a, b -> b - a }
        for (i in 0 until n) projects.add(capital[i] to profits[i])

        var currCap = w

        for (i in 0 until k) {
            while (projects.isNotEmpty() && projects.peek().first <= currCap) {
                profitQueue.add(projects.poll().second)
            }

            if (profitQueue.isEmpty()) break

            currCap += profitQueue.poll()
        }

        return currCap
    }
}

data class IPOParams(
    val k: Int,
    val w: Int,
    val profits: IntArray,
    val capital: IntArray
) {
    override fun toString(): String {
        return """
            
            k: $k
            w: $w
            profits: ${stringFromArray(profits)}
            capital: ${stringFromArray(capital)}
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IPOParams

        if (k != other.k) return false
        if (w != other.w) return false
        if (!profits.contentEquals(other.profits)) return false
        if (!capital.contentEquals(other.capital)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = k
        result = 31 * result + w
        result = 31 * result + profits.contentHashCode()
        result = 31 * result + capital.contentHashCode()
        return result
    }
}
