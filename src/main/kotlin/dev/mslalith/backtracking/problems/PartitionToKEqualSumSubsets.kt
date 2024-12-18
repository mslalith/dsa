package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class PartitionToKEqualSumSubsets : TestCaseProblem<Pair<IntArray, Int>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PartitionToKEqualSumSubsets().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 3, 2, 3, 5, 2, 1) to 4,
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 4) to 3,
            output = false
        ),
        TestCase(
            input = intArrayOf(4, 16, 5, 3, 10, 4, 4, 4, 10) to 3,
            output = true
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Boolean {
        return canPartitionKSubsets(testCaseInput.first.createClone(), testCaseInput.second)
    }

    private fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        val n = nums.size
        nums.sort()

        val totalSum = nums.sum()
        if (k <= 0 || totalSum % k != 0) return false

        val target = totalSum / k
        val used = BooleanArray(n)

        fun findCanPartitionKSubsets(i: Int, currSum: Int, k: Int): Boolean {
            if (k == 1) return true

            if (currSum == target) return findCanPartitionKSubsets(0, 0, k - 1)

            for (j in i until n) {
                if (used[j]) continue
                if (currSum + nums[j] > target) continue

                if (j > 0 && nums[j] == nums[j - 1] && !used[j - 1]) continue

                used[j] = true
                if (findCanPartitionKSubsets(j + 1, currSum + nums[j], k)) return true
                used[j] = false
            }

            return false
        }

        return findCanPartitionKSubsets(0, 0, k)
    }
}
