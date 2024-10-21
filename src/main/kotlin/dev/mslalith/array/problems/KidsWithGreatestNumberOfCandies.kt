package dev.mslalith.array.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class KidsWithGreatestNumberOfCandies : Problem<Pair<IntArray, Int>, List<Boolean>>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KidsWithGreatestNumberOfCandies().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, List<Boolean>>> = arrayOf(
        TestCase(
            input = intArrayOf(2,3,5,1,3) to 3,
            output = listOf(true,true,true,false,true)
        ),
        TestCase(
            input = intArrayOf(4,2,1,1,2) to 1,
            output = listOf(true,false,false,false,false)
        ),
        TestCase(
            input = intArrayOf(12,1,12) to 10,
            output = listOf(true,false,true)
        )
    )
    
    override fun solve(testCaseInput: Pair<IntArray, Int>): List<Boolean> {
        return kidsWithCandies(testCaseInput.first, testCaseInput.second)
    }

    private fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        val max = candies.max()
        return candies.map { (it + extraCandies) >= max }
    }
}
