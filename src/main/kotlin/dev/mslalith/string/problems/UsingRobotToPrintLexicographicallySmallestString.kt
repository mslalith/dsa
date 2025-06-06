package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class UsingRobotToPrintLexicographicallySmallestString : TestCaseProblem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UsingRobotToPrintLexicographicallySmallestString().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "zza",
            output = "azz"
        ),
        TestCase(
            input = "bac",
            output = "abc"
        ),
        TestCase(
            input = "bdda",
            output = "addb"
        )
    )

    override fun solve(testCaseInput: String): String {
        return robotWithString(testCaseInput)
    }

    private fun robotWithString(s: String): String {
        val freq = IntArray(26)
        for (ch in s) freq[ch - 'a']++

        val p = StringBuilder()
        val t = StringBuilder()
        var smallest = 0

        for (ch in s) {
            t.append(ch)
            freq[ch - 'a']--

            while (smallest < 25 && freq[smallest] == 0) smallest++

            while (t.isNotEmpty() && smallest >= t.last() - 'a') {
                p.append(t.last())
                t.deleteAt(t.lastIndex)
            }
        }

        return p.toString()
    }
}
