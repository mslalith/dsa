package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase

class FruitsIntoBasketsIII : dev.mslalith.core.problem.TestCaseProblem<Pair<IntArray, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FruitsIntoBasketsIII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 2, 5) to intArrayOf(3, 5, 4),
            output = 1
        ),
        TestCase(
            input = intArrayOf(3, 6, 1) to intArrayOf(6, 4, 7),
            output = 0
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Int {
        return numOfUnplacedFruits(testCaseInput.first, testCaseInput.second)
    }

    private fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
        val n = baskets.size

        var a = 1
        while (a <= n) a = a shl 1

        val segTree = IntArray(a shl 1)
        for (i in 0..<n) segTree[a + i] = baskets[i]
        for (i in a - 1 downTo 1) segTree[i] = maxOf(segTree[2 * i], segTree[2 * i + 1])

        var count = 0

        for (i in 0..<n) {
            val x = fruits[i]
            var index = 1
            if (segTree[index] < x) {
                count++
                continue
            }

            while (index < a) {
                if (segTree[index * 2] >= x) index *= 2
                else index = index * 2 + 1
            }

            segTree[index] = -1
            while (index > 1) {
                index = index shr 1
                segTree[index] = maxOf(segTree[2 * index], segTree[2 * index + 1])
            }
        }

        return count
    }
}
