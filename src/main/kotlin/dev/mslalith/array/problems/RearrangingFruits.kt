package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class RearrangingFruits : TestCaseProblem<Pair<IntArray, IntArray>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RearrangingFruits().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Long>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 2, 2, 2) to intArrayOf(1, 4, 1, 2),
            output = 1
        ),
        TestCase(
            input = intArrayOf(2, 3, 4, 1) to intArrayOf(3, 2, 5, 1),
            output = -1
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Long {
        return minCost(testCaseInput.first, testCaseInput.second)
    }

    private fun minCost(basket1: IntArray, basket2: IntArray): Long {
        val n = basket1.size

        val map1 = hashMapOf<Int, Int>()
        val map2 = hashMapOf<Int, Int>()
        var minVal = Int.MAX_VALUE

        for (i in 0..<n) {
            map1[basket1[i]] = map1.getOrDefault(basket1[i], 0) + 1
            map2[basket2[i]] = map2.getOrDefault(basket2[i], 0) + 1
            minVal = minOf(minVal, basket1[i])
            minVal = minOf(minVal, basket2[i])
        }

        val swapList1 = mutableListOf<Int>()
        for ((key, c1) in map1) {
            val c2 = map2.getOrDefault(key, 0)
            if ((c1 + c2) % 2 == 1) return -1

            if (c1 > c2) {
                var addCnt = (c1 - c2) / 2
                while (addCnt-- > 0) swapList1 += key
            }
        }

        val swapList2 = mutableListOf<Int>()
        for ((key, c2) in map2) {
            val c1 = map1.getOrDefault(key, 0)
            if ((c1 + c2) % 2 == 1) return -1

            if (c2 > c1) {
                var addCnt = (c2 - c1) / 2
                while (addCnt-- > 0) swapList2 += key
            }
        }

        swapList1.sort()
        swapList2.sortDescending()

        var res = 0L
        for (i in swapList1.indices) {
            res += minOf(
                2 * minVal,
                minOf(swapList1[i], swapList2[i])
            ).toLong()
        }

        return res
    }
}
