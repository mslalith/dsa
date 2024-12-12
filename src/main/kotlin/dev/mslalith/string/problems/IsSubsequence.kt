package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class IsSubsequence: TestCaseProblem<IsSubsequenceParams, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsSubsequence().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IsSubsequenceParams, Boolean>> = arrayOf(
        TestCase(
            input = IsSubsequenceParams(s = "abc", t = "ahbgdc"),
            output = true
        ),
        TestCase(
            input = IsSubsequenceParams(s = "axc", t = "ahbgdc"),
            output = false
        ),
        TestCase(
            input = IsSubsequenceParams(s = "", t = "ahbgdc"),
            output = true
        ),
        TestCase(
            input = IsSubsequenceParams(s = "b", t = "abc"),
            output = true
        )
    )

    override fun solve(testCaseInput: IsSubsequenceParams): Boolean {
        return isSubsequence(testCaseInput.s, testCaseInput.t)
    }

    private fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        if (s.length > t.length) return false

        var left = 0

        for (i in t.indices) {
            if (s[left] == t[i]) left++
            if (left == s.length) return true
        }

        return left == s.length
    }
}

data class IsSubsequenceParams(
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
