package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class ProductOfArrayExceptSelf : Problem<IntArray, IntArray>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ProductOfArrayExceptSelf().run()
    }
    
    override fun getTestCases(): Array<TestCase<IntArray, IntArray>> = arrayOf(
        TestCase(
            input = intArrayOf(1,2,3,4),
            output = intArrayOf(24,12,8,6)
        ),
        TestCase(
            input = intArrayOf(-1,1,0,-3,3),
            output = intArrayOf(0,0,9,0,0)
        )
    )
    
    override fun solve(testCaseInput: IntArray): IntArray {
        return productExceptSelf(testCaseInput)
    }

    private fun productExceptSelf(nums: IntArray): IntArray {
        var product = 1
        val suffixProduct = IntArray(nums.size)
        for (i in nums.lastIndex downTo 0) {
            suffixProduct[i] = product
            product *= nums[i]
        }

        var prefixProduct = 1
        for (i in nums.indices) {
            suffixProduct[i] = prefixProduct * suffixProduct[i]
            prefixProduct *= nums[i]
        }

        return suffixProduct
    }

    private fun productExceptSelfNaive(nums: IntArray): IntArray {
        var product = 1
        val prefixProduct = IntArray(nums.size)
        for (i in nums.indices) {
            prefixProduct[i] = product
            product *= nums[i]
        }

        product = 1
        val suffixProduct = IntArray(nums.size)
        for (i in nums.lastIndex downTo 0) {
            suffixProduct[i] = product
            product *= nums[i]
        }

        val result = IntArray(nums.size)
        for (i in nums.indices) {
            result[i] = prefixProduct[i] * suffixProduct[i]
        }

        return result
    }
}
