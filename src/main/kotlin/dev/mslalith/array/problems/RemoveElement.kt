package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray

class RemoveElement : TestCaseProblem<RemoveElementParams, RemoveElementOutput>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveElement().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<RemoveElementParams, RemoveElementOutput>> = arrayOf(
        TestCase(
            input = RemoveElementParams(nums = intArrayOf(3, 2, 2, 3), value = 3),
            output = RemoveElementOutput(nums = intArrayOf(2, 2, 2, 3), length = 2)
        ),
        TestCase(
            input = RemoveElementParams(nums = intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), value = 2),
            output = RemoveElementOutput(nums = intArrayOf(0, 1, 3, 0, 4, 0, 4, 2), length = 5)
        )
    )

    override fun solve(testCaseInput: RemoveElementParams): RemoveElementOutput {
        val nums = testCaseInput.nums.clone()
        val result = removeElement(nums, testCaseInput.value)
        return RemoveElementOutput(nums, result)
    }

    private fun removeElement(nums: IntArray, value: Int): Int {
        if (nums.isEmpty()) return 0

        var insert = 0
        var i = 0
        while (i < nums.size) {
            while (i < nums.size && nums[i] == value) i++
            if (i >= nums.size) return insert

            nums[insert++] = nums[i++]
        }
        return insert
    }
}

data class RemoveElementParams(
    val nums: IntArray,
    val value: Int
) {
    override fun toString(): String {
        return """
            
            nums: ${stringFromArray(nums)}
            value: $value
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RemoveElementParams

        if (!nums.contentEquals(other.nums)) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nums.contentHashCode()
        result = 31 * result + value
        return result
    }
}

data class RemoveElementOutput(
    val nums: IntArray,
    val length: Int
) {
    override fun toString(): String {
        return "(${stringFromArray(nums)}, $length)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RemoveElementOutput

        if (!nums.contentEquals(other.nums)) return false
        if (length != other.length) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nums.contentHashCode()
        result = 31 * result + length
        return result
    }
}