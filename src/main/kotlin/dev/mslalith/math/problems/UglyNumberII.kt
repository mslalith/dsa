package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue
import kotlin.math.min

class UglyNumberII : TestCaseProblem<Int, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UglyNumberII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 10,
            output = 12
        ),
        TestCase(
            input = 1,
            output = 1
        ),
        TestCase(
            input = 1407,
            output = 536870912
        )
    )

    override fun solve(testCaseInput: Int): Int {
        return nthUglyNumber(testCaseInput)
    }

    private fun nthUglyNumber(n: Int): Int {
        if (n < 7) return n

        val uglyNumbers = IntArray(n)
        uglyNumbers[0] = 1

        var index2 = 0
        var index3 = 0
        var index5 = 0
        var nextUglyWith2 = 2
        var nextUglyWith3 = 3
        var nextUglyWith5 = 5

        for (i in 1 until n) {
            val nextUgly = min(nextUglyWith2, min(nextUglyWith3, nextUglyWith5))
            uglyNumbers[i] = nextUgly

            if (nextUgly == nextUglyWith2) {
                index2++
                nextUglyWith2 = uglyNumbers[index2] * 2
            }
            if (nextUgly == nextUglyWith3) {
                index3++
                nextUglyWith3 = uglyNumbers[index3] * 3
            }
            if (nextUgly == nextUglyWith5) {
                index5++
                nextUglyWith5 = uglyNumbers[index5] * 5
            }
        }

        return uglyNumbers[n - 1]
    }

    private fun nthUglyNumberBetter(n: Int): Int {
        if (n < 7) return n

        val factors = intArrayOf(2, 3, 5)
        val seen = hashSetOf<Long>()
        val pq = PriorityQueue<Long>()
        pq.add(1)

        for (i in 1 until n) {
            val num = pq.poll()
            for (factor in factors) {
                val next = num * factor
                if (seen.add(next)) pq.add(next)
            }
        }

        return pq.poll().toInt()
    }

    private fun nthUglyNumberBrute(n: Int): Int {
        if (n < 7) return n

        fun isUgly(n: Int): Boolean {
            if (n < 1) return false
            if (n < 2) return true

            var i = n
            while (i % 2 == 0) i /= 2
            while (i % 3 == 0) i /= 3
            while (i % 5 == 0) i /= 5

            return i == 1
        }

        var nth = 6
        var i = 7

        while (nth < n) {
            while (!isUgly(i)) i++
            nth++
            i++
        }

        return i - 1
    }
}
