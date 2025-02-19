package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class KthLexicographicalStringOfAllHappyStringsOfLengthN : TestCaseProblem<Pair<Int, Int>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KthLexicographicalStringOfAllHappyStringsOfLengthN().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, String>> = arrayOf(
        TestCase(
            input = 1 to 3,
            output = "c"
        ),
        TestCase(
            input = 1 to 4,
            output = ""
        ),
        TestCase(
            input = 3 to 9,
            output = "cab"
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): String {
        return getHappyString(testCaseInput.first, testCaseInput.second)
    }

    private fun getHappyString(n: Int, k: Int): String {
        val size = 3 * (1 shl (n - 1))
        if (size < k) return ""

        val chars = charArrayOf('a', 'b', 'c')
        var count = 0

        fun nextHappyString(pos: Int, current: StringBuilder): String {
            if (pos == n) {
                count++
                return if (count == k) current.toString() else ""
            }

            for (i in chars.indices) {
                if (current.isNotEmpty() && current[current.lastIndex] == chars[i]) continue
                current.append(chars[i])

                val nextHappyString = nextHappyString(pos + 1, current)
                if (nextHappyString.isNotEmpty()) return nextHappyString

                current.deleteAt(current.lastIndex)
            }

            return ""
        }

        return nextHappyString(0, StringBuilder())
    }
}
