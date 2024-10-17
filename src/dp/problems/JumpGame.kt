package src.dp.problems

import src.core.Problem
import src.core.TestCase
import kotlin.math.max

class JumpGame : Problem<IntArray, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = JumpGame().run()
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