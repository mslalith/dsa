package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class CanPlaceFlowers : TestCaseProblem<Pair<IntArray, Int>, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CanPlaceFlowers().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1,0,0,0,1) to 1,
            output = true
        ),
        TestCase(
            input = intArrayOf(1,0,0,0,1) to 2,
            output = false
        ),
        TestCase(
            input = intArrayOf(1,0,0,0,0,1) to 2,
            output = false
        ),
        TestCase(
            input = intArrayOf(0,0,1,0,0) to 1,
            output = true
        )
    )
    
    override fun solve(testCaseInput: Pair<IntArray, Int>): Boolean {
        return canPlaceFlowers(testCaseInput.first, testCaseInput.second)
    }

    private fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        if (n == 0) return true
        if (flowerbed.size == 1) return (flowerbed[0] + n) <= 1

        var rem = n
        var i = 0
        while (i < flowerbed.size) {
            if (flowerbed[i] == 1) i += 2
            else {
                val nextValue = if (i < flowerbed.size - 1) flowerbed[i + 1] else 0
                if (flowerbed[i] == 0 && nextValue == 0) {
                    flowerbed[i] = 1
                    i += 2
                    rem--
                } else i += 3
            }
            if (rem == 0) return true
        }

        return false
    }
}
