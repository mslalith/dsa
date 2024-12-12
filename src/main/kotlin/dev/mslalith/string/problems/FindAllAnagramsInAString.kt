package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class FindAllAnagramsInAString : TestCaseProblem<FindAllAnagramsInAStringParams, List<Int>>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindAllAnagramsInAString().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<FindAllAnagramsInAStringParams, List<Int>>> = arrayOf(
        TestCase(
            input = FindAllAnagramsInAStringParams(
                s = "cbaebabacd",
                p = "abc"
            ),
            output = listOf(0, 6)
        ),
        TestCase(
            input = FindAllAnagramsInAStringParams(
                s = "abab",
                p = "ab"
            ),
            output = listOf(0, 1, 2)
        )
    )
    
    override fun solve(testCaseInput: FindAllAnagramsInAStringParams): List<Int> {
        return findAnagrams(testCaseInput.s, testCaseInput.p)
    }

    private fun findAnagrams(s: String, p: String): List<Int> {
        val list = mutableListOf<Int>()

        val array = IntArray(26) { 0 }
        p.forEach { array[it - 'a']++ }

        for (i in s.indices) {
            array[s[i] - 'a']--
            if (i >= p.length) array[s[i - p.length] - 'a']++
            if (array.all { it == 0 }) list.add(i - p.length + 1)
        }

        return list
    }
}

data class FindAllAnagramsInAStringParams(
    val s: String,
    val p: String
) {
    override fun toString(): String {
        return """
            
            s: $s
            p: $p
        """.trimIndent()
    }
}
