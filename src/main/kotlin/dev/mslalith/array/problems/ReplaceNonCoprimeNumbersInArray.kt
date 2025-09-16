package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ReplaceNonCoprimeNumbersInArray : TestCaseProblem<IntArray, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReplaceNonCoprimeNumbersInArray().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IntArray, List<Int>>> = arrayOf(
        TestCase(
            input = intArrayOf(6, 4, 3, 2, 7, 6, 2),
            output = listOf(12, 7, 6)
        ),
        TestCase(
            input = intArrayOf(2, 2, 1, 1, 3, 3, 3),
            output = listOf(2, 1, 1, 3)
        )
    )

    override fun solve(testCaseInput: IntArray): List<Int> {
        return replaceNonCoprimes(testCaseInput)
    }

    private fun replaceNonCoprimes(nums: IntArray): List<Int> {
        val stack = IntArray(nums.size)
        var top = -1

        fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

        for (num in nums) {
            var num = num
            while (top != -1) {
                val x = gcd(stack[top], num)
                if (x == 1) break
                num *= stack[top--] / x
            }

            stack[++top] = num
        }

        return stack.take(top + 1)
    }
}