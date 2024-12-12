package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class BackspaceStringCompare : TestCaseProblem<BackspaceStringCompareParams, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BackspaceStringCompare().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<BackspaceStringCompareParams, Boolean>> = arrayOf(
        TestCase(
            input = BackspaceStringCompareParams(s = "ab#c", t = "ad#c"),
            output = true
        ),
        TestCase(
            input = BackspaceStringCompareParams(s = "ab##", t = "c#d#"),
            output = true
        ),
        TestCase(
            input = BackspaceStringCompareParams(s = "a#c", t = "b"),
            output = false
        )
    )
    
    override fun solve(testCaseInput: BackspaceStringCompareParams): Boolean {
        return backspaceCompare(testCaseInput.s, testCaseInput.t)
    }

    private fun backspaceCompare(s: String, t: String): Boolean {
        return backspaceCompare(s) == backspaceCompare(t)
    }

    private fun backspaceCompare(str: String): String {
        val sb = StringBuilder()
        for (ch in str) {
            if (ch == '#') {
                if (sb.isNotEmpty()) sb.deleteCharAt(sb.lastIndex)
            } else {
                sb.append(ch)
            }
        }
        return sb.toString()
    }
}

data class BackspaceStringCompareParams(
    val s: String,
    val t: String
) {
    override fun toString(): String {
        return """
            
            s: $s
            t: $t
        """.trimIndent()
    }
}
