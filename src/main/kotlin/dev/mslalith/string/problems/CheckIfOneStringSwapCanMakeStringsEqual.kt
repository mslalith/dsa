package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CheckIfOneStringSwapCanMakeStringsEqual : TestCaseProblem<Pair<String, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CheckIfOneStringSwapCanMakeStringsEqual().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, String>, Boolean>> = arrayOf(
        TestCase(
            input = "bank" to "kanb",
            output = true
        ),
        TestCase(
            input = "attack" to "defend",
            output = false
        ),
        TestCase(
            input = "kelb" to "kelb",
            output = true
        ),
        TestCase(
            input = "yf" to "yy",
            output = false
        ),
        TestCase(
            input = "bankb" to "kannb",
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<String, String>): Boolean {
        return areAlmostEqual(testCaseInput.first, testCaseInput.second)
    }

    private fun areAlmostEqual(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false
        if (s1 == s2) return true

        val sb2 = StringBuilder(s2)

        var mismatchIndex = -1

        for (i in s1.indices) {
            if (s1[i] != sb2[i]) {
                when (mismatchIndex) {
                    -1 -> mismatchIndex = i
                    else -> {
                        // already seen one mismatch before
                        val temp = sb2[i]
                        sb2[i] = sb2[mismatchIndex]
                        sb2[mismatchIndex] = temp

                        return s1 == sb2.toString()
                    }
                }
            }
        }

        return false
    }
}
