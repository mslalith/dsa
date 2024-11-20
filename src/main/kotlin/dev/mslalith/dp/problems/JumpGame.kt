package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max
import kotlin.math.min

class JumpGame : TestCaseProblem<IntArray, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = JumpGame().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<IntArray, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(2, 3, 1, 1, 4),
            output = true
        ),
        TestCase(
            input = intArrayOf(3, 2, 1, 0, 4),
            output = false
        ),
        TestCase(
            input = intArrayOf(0),
            output = true
        ),
        TestCase(
            input = intArrayOf(2, 0, 0),
            output = true
        )
    )

    override fun solve(testCaseInput: IntArray): Boolean {
        return canJump(testCaseInput)
    }

    private fun canJump(nums: IntArray): Boolean {
        var jumpTill = 0

        for (i in nums.indices) {
            if (jumpTill < i) return false
            jumpTill = max(jumpTill, i + nums[i])
        }

        return true
    }

    private fun canJumpDp(nums: IntArray): Boolean {
        val n = nums.size
        val dp = IntArray(n)
        dp[n - 1] = 1

        for (i in (n - 2) downTo 0) {
            val furthest = min(n, i + nums[i])
            var canJump = 0
            for (j in (i + 1)..furthest) {
                if (dp[j] == 1) {
                    canJump = 1
                    break
                }
            }

            dp[i] = canJump
        }

        return dp[0] == 1
    }

    private fun canJumpRecursive(nums: IntArray): Boolean {
        val n = nums.size
        val dp = IntArray(n) { -1 }

        fun findCanJump(i: Int): Boolean {
            if (i >= n - 1) return true
            if (dp[i] != -1) return dp[i] == 1

            val furthest = min(n, i + nums[i])
            var canJump = 0
            for (j in (i + 1)..furthest) {
                if (findCanJump(j)) {
                    canJump = 1
                    break
                }
            }

            dp[i] = canJump
            return dp[i] == 1
        }

        return findCanJump(0)
    }

    private fun canJumpAttempt(nums: IntArray): Boolean {
        if (nums.size == 1) return true

        val jumpIndexes = hashSetOf(0)

        for (i in nums.indices) {
            if (nums[i] != 0 && jumpIndexes.contains(i)) jumpIndexes.addAll((i + 1)..(i + nums[i]))
            if (jumpIndexes.contains(nums.size - 1)) return true
        }

        return jumpIndexes.contains(nums.size - 1)
    }
}
