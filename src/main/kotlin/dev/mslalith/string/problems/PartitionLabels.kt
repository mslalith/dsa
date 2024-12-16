package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class PartitionLabels : TestCaseProblem<String, List<Int>>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PartitionLabels().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, List<Int>>> = arrayOf(
        TestCase(
            input = "ababcbacadefegdehijhklij",
            output = listOf(9, 7, 8)
        ),
        TestCase(
            input = "eccbbbbdec",
            output = listOf(10)
        )
    )
    
    override fun solve(testCaseInput: String): List<Int> {
        return partitionLabels(testCaseInput)
    }

    private fun partitionLabels(s: String): List<Int> {
        val lastOccurrenceArray = IntArray(26)
        for (i in s.indices) lastOccurrenceArray[s[i] - 'a'] = i

        val result = mutableListOf<Int>()
        var start = 0
        var end = 0

        for (i in s.indices) {
            end = max(end, lastOccurrenceArray[s[i] - 'a'])
            if (end == i) {
                result.add(i - start + 1)
                start = end + 1
            }
        }

        return result
    }
}
