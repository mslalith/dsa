package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import kotlin.math.max

class MaxConsecutiveOnesIII : TestCaseProblem<Pair<IntArray, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaxConsecutiveOnesIII().runAll()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0) to 2,
            output = 6
        ),
        TestCase(
            input = intArrayOf(0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1) to 3,
            output = 10
        ),
        TestCase(
            input = intArrayOf(0,0,1,1,1,0,0) to 0,
            output = 3
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Int {
        return longestOnes(testCaseInput.first, testCaseInput.second)
    }

    private fun longestOnes(nums: IntArray, k: Int): Int {
        val n = nums.size
        var left = 0
        var right = 0

        var count = 0
        var extra = k
        var maxCount = 0
        val flipped = hashSetOf<Int>()

        while (right < n) {
            while (right < n) {
                if (nums[right] == 1) {
                    count++
                    right++
                } else if (nums[right] == 0 && extra > 0) {
                    flipped.add(right)
                    extra--
                    count++
                    right++
                } else break
            }
            maxCount = max(maxCount, count)

            while (left < right) {
                if (nums[left] == 1) {
                    left++
                    count--
                }
                else if (nums[left] == 0 && flipped.contains(left)) {
                    flipped.remove(left)
                    count--
                    extra++
                    left++
                    break
                } else break
            }

            if (extra == 0 && left >= right) {
                right++
                left = right
            }
        }

        return maxCount
    }
}
