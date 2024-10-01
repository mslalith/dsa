package src.string.problems

import src.core.Problem
import src.core.TestCase

class MaximumNumberOfVowelsInSubstringOfGivenLength : Problem<Pair<String, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumNumberOfVowelsInSubstringOfGivenLength().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Int>> = arrayOf(
        TestCase(
            input = "abciiidef" to 3,
            output = 3
        ),
        TestCase(
            input = "aeiou" to 2,
            output = 2
        ),
        TestCase(
            input = "leetcode" to 3,
            output = 2
        ),
        TestCase(
            input = "abcdefiii" to 3,
            output = 3
        ),
    )
    
    override fun solve(testCaseInput: Pair<String, Int>): Int {
        return maxVowels(testCaseInput.first, testCaseInput.second)
    }

    private fun maxVowels(s: String, k: Int): Int {
        var maxCount = 0

        var left = 0
        var right = 0
        var count = 0

        while (right < s.length) {
            if ((right - left) < k) {
                if (isVowel(s[right])) {
                    count++
                    if (count > maxCount) maxCount = count
                }
                right++
            } else {
                if (isVowel(s[left])) count--
                left++
            }
        }

        return maxCount
    }

    private fun maxVowelsNaive(s: String, k: Int): Int {
        var maxCount = 0
        s.windowed(k)
            .forEach { subStr ->
            val count = subStr.count { isVowel(it) }
            if (count > maxCount) maxCount = count
        }
        return maxCount
    }

    private fun isVowel(ch: Char): Boolean = when (ch) {
        'a', 'e', 'i', 'o', 'u' -> true
        else -> false
    }
}
