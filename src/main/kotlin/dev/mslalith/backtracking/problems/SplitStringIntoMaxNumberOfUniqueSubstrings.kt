package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import kotlin.math.max

class SplitStringIntoMaxNumberOfUniqueSubstrings : TestCaseProblem<String, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SplitStringIntoMaxNumberOfUniqueSubstrings().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "ababccc",
            output = 5
        ),
        TestCase(
            input = "aba",
            output = 2
        ),
        TestCase(
            input = "aa",
            output = 1
        ),
        TestCase(
            input = "addbsd",
            output = 5
        ),
        TestCase(
            input = "iedabkacb",
            output = 8
        ),
        TestCase(
            input = "dbbpaaaab",
            output = 6
        )
    )

    override fun solve(testCaseInput: String): Int {
        return maxUniqueSplit(testCaseInput)
    }

    private fun maxUniqueSplit(str: String): Int {
        val n = str.length
        if (n <= 1) return n

        val set = hashSetOf<String>()

        fun uniqueSplitCount(rest: String): Int {
            val size = rest.length
            if (size == 0) return 0

            var j = 0
            val sb = StringBuilder()
            var maximum = 0

            while (j < rest.length) {
                sb.append(rest[j])
                val split = sb.toString()
                if (!set.contains(split)) {
                    set.add(split)
                    maximum = max(maximum, 1 + uniqueSplitCount(rest.drop(sb.length)))
                    set.remove(split)
                }
                j++
            }

            return maximum
        }

        return uniqueSplitCount(str)
    }
}
