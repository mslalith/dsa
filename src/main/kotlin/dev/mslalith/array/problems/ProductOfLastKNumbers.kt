package dev.mslalith.array.problems

import dev.mslalith.array.problems.ProductOfLastKNumbers.ProductOfLastKNumbersType
import dev.mslalith.array.problems.ProductOfLastKNumbers.ProductOfLastKNumbersType.Add
import dev.mslalith.array.problems.ProductOfLastKNumbers.ProductOfLastKNumbersType.GetProduct
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ProductOfLastKNumbers : TestCaseProblem<List<ProductOfLastKNumbersType>, IntArray>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ProductOfLastKNumbers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<List<ProductOfLastKNumbersType>, IntArray>> = arrayOf(
        TestCase(
            input = listOf(
                Add(num = 3),
                Add(num = 0),
                Add(num = 2),
                Add(num = 5),
                Add(num = 4),
                GetProduct(k = 2),
                GetProduct(k = 3),
                GetProduct(k = 4),
                Add(num = 8),
                GetProduct(k = 2)
            ),
            output = intArrayOf(20, 40, 0, 32)
        )
    )
    
    override fun solve(testCaseInput: List<ProductOfLastKNumbersType>): IntArray {
        val productOfNumbers = ProductOfNumbers()
        return testCaseInput.mapNotNull {
            when (it) {
                is GetProduct -> productOfNumbers.getProduct(it.k)
                is Add -> {
                    productOfNumbers.add(it.num)
                    null
                }
            }
        }.toIntArray()
    }

    private class ProductOfNumbers {

        private val nums = mutableListOf(1)

        fun add(num: Int) {
            if (num == 0) {
                nums.clear()
                nums += 1
            } else {
                nums += nums.last() * num
            }
        }

        fun getProduct(k: Int): Int {
            if (nums.size <= k) return 0
            return nums.last() / nums[nums.size - k - 1]
        }
    }

    sealed class ProductOfLastKNumbersType {
        data class Add(val num: Int) : ProductOfLastKNumbersType()
        data class GetProduct(val k: Int) : ProductOfLastKNumbersType()
    }
}
