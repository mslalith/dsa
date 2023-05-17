package src.string.problems

import src.core.Problem
import src.core.TestCase

class ValidAnagram : Problem<Pair<String, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ValidAnagram().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<String, String>, Boolean>> = arrayOf(
        TestCase(
            input = "anagram" to "nagaram",
            output = true
        ),
        TestCase(
            input = "rat" to "car",
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<String, String>): Boolean {
        return isAnagram(s = testCaseInput.first, t = testCaseInput.second)
    }

    private fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        val charArray = IntArray(size = 26)
        s.forEachIndexed { i, ch ->
            charArray[ch - 'a']++
            charArray[t[i] - 'a']--
        }
        return charArray.all { it == 0 }
    }
}
