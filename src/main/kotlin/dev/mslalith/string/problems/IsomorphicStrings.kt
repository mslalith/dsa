package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class IsomorphicStrings : TestCaseProblem<IsomorphicStringsParams, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = IsomorphicStrings().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<IsomorphicStringsParams, Boolean>> = arrayOf(
        TestCase(
            input = IsomorphicStringsParams(s = "egg", t = "add"),
            output = true
        ),
        TestCase(
            input = IsomorphicStringsParams(s = "foo", t = "bar"),
            output = false
        ),
        TestCase(
            input = IsomorphicStringsParams(s = "paper", t = "title"),
            output = true
        ),
        TestCase(
            input = IsomorphicStringsParams(s = "badc", t = "baba"),
            output = false
        )
    )

    override fun solve(testCaseInput: IsomorphicStringsParams): Boolean {
        return isIsomorphic(testCaseInput.s, testCaseInput.t)
    }

    private fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val map1 = mutableMapOf<Char, Int>()
        val map2 = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            if (map1[s[i]] != map2[t[i]]) return false
            map1[s[i]] = i
            map2[t[i]] = i
        }

        return true
    }
}

data class IsomorphicStringsParams(
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