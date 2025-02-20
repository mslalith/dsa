package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindUniqueBinaryString : TestCaseProblem<Array<String>, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindUniqueBinaryString().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<String>, String>> = arrayOf(
        TestCase(
            input = arrayOf("01", "10"),
            output = "11",
            otherAcceptableOutputs = listOf("00")
        ),
        TestCase(
            input = arrayOf("00", "01"),
            output = "11",
            otherAcceptableOutputs = listOf("10")
        ),
        TestCase(
            input = arrayOf("111", "011", "001"),
            output = "101",
            otherAcceptableOutputs = listOf("000", "010", "100", "110")
        ),
        TestCase(
            input = arrayOf("000", "001", "110"),
            output = "010",
            otherAcceptableOutputs = listOf("111")
        ),
        TestCase(
            input = arrayOf("0"),
            output = "1"
        ),
        TestCase(
            input = arrayOf(
                "0000000111", "0000001001", "0000000100", "0000000001", "0000000010", "1111111111", "0000000101", "0000000000", "0000001000", "0000000110"
            ),
            output = "0000000011",
            otherAcceptableOutputs = listOf("1111101111")
        )
    )

    override fun solve(testCaseInput: Array<String>): String {
        return findDifferentBinaryString(testCaseInput)
    }

    private fun findDifferentBinaryString(nums: Array<String>): String {
        val sb = StringBuilder()

        for (i in nums.indices) {
            val bit = if (nums[i][i] == '1') '0' else '1'
            sb.append(bit)
        }

        return sb.toString()
    }

    private fun findDifferentBinaryStringNaive(nums: Array<String>): String {
        val n = nums.size

        val set = nums.toHashSet()

        fun dfs(sb: StringBuilder): String {
            if (sb.length == n) {
                val str = sb.toString()
                return if (str !in set) str else ""
            }

            sb.append('0')
            var str = dfs(sb)
            sb.deleteAt(sb.lastIndex)
            if (str.isNotEmpty()) return str

            sb.append('1')
            str = dfs(sb)
            sb.deleteAt(sb.lastIndex)

            return str
        }

        return dfs(StringBuilder())
    }
}
