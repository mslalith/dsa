package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CompareVersionNumbers : TestCaseProblem<Pair<String, String>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CompareVersionNumbers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, Int>> = arrayOf(
        TestCase(
            input = "1.2" to "1.10",
            output = -1
        ),
        TestCase(
            input = "1.01" to "1.001",
            output = 0
        ),
        TestCase(
            input = "1.0" to "1.0.0.0",
            output = 0
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): Int {
        return compareVersion(testCaseInput.first, testCaseInput.second)
    }

    private fun compareVersion(version1: String, version2: String): Int {
        var i = 0
        var j = 0

        while (i < version1.length || j < version2.length) {
            var num1 = 0
            while (i < version1.length && version1[i] != '.') num1 = (num1 * 10) + (version1[i++] - '0')

            var num2 = 0
            while (j < version2.length && version2[j] != '.') num2 = (num2 * 10) + (version2[j++] - '0')

            if (num1 < num2) return -1
            if (num1 > num2) return 1

            i++
            j++
        }

        return 0
    }

    private fun compareVersionNaive(version1: String, version2: String): Int {
        val v1Revisions = version1.split(".")
        val v2Revisions = version2.split(".")

        for (i in 0..<maxOf(v1Revisions.size, v2Revisions.size)) {
            val v1 = v1Revisions.getOrNull(i)?.toInt() ?: 0
            val v2 = v2Revisions.getOrNull(i)?.toInt() ?: 0
            if (v1 == v2) continue
            return v1.compareTo(v2)
        }

        return 0
    }
}
