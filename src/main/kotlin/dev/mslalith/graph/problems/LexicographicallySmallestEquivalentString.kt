package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.LexicographicallySmallestEquivalentString.LexicographicallySmallestEquivalentStringParams

class LexicographicallySmallestEquivalentString : TestCaseProblem<LexicographicallySmallestEquivalentStringParams, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LexicographicallySmallestEquivalentString().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<LexicographicallySmallestEquivalentStringParams, String>> = arrayOf(
        TestCase(
            input = LexicographicallySmallestEquivalentStringParams(
                s1 = "parker",
                s2 = "morris",
                baseStr = "parser"
            ),
            output = "makkek"
        ),
        TestCase(
            input = LexicographicallySmallestEquivalentStringParams(
                s1 = "hello",
                s2 = "world",
                baseStr = "hold"
            ),
            output = "hdld"
        ),
        TestCase(
            input = LexicographicallySmallestEquivalentStringParams(
                s1 = "leetcode",
                s2 = "programs",
                baseStr = "sourcecode"
            ),
            output = "aauaaaaada"
        )
    )

    override fun solve(testCaseInput: LexicographicallySmallestEquivalentStringParams): String {
        return smallestEquivalentString(testCaseInput.s1, testCaseInput.s2, testCaseInput.baseStr)
    }

    private fun smallestEquivalentString(s1: String, s2: String, baseStr: String): String {
        val parent = IntArray(26) { it }

        fun find(u: Int): Int {
            if (parent[u] != u) parent[u] = find(parent[u])
            return parent[u]
        }

        fun union(u: Int, v: Int) {
            val pu = find(u)
            val pv = find(v)

            when {
                pu < pv -> parent[pv] = pu
                pu > pv -> parent[pu] = pv
            }
        }

        for (i in s1.indices) union(s1[i] - 'a', s2[i] - 'a')

        return buildString {
            for (ch in baseStr) {
                val parent = (find(ch - 'a') + 'a'.code).toChar()
                append(parent)
            }
        }
    }

    data class LexicographicallySmallestEquivalentStringParams(
        val s1: String,
        val s2: String,
        val baseStr: String
    ) {
        override fun toString(): String {
            return """
                
                s1: $s1
                s2: $s2
                baseStr: $baseStr
            """.trimIndent()
        }
    }
}
