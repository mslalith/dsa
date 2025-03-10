package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CountOfSubstringsContainingEveryVowelAndKConsonantsII : TestCaseProblem<Pair<String, Int>, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountOfSubstringsContainingEveryVowelAndKConsonantsII().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, Int>, Long>> = arrayOf(
        TestCase(
            input = "aeioqq" to 1,
            output = 0L
        ),
        TestCase(
            input = "aeiou" to 0,
            output = 1L
        ),
        TestCase(
            input = "ieaouqqieaouqq" to 1,
            output = 3L
        ),
        TestCase(
            input = "iqeaouqi" to 2,
            output = 3L
        )
    )
//        .let { arrayOf(it.last()) }
    
    override fun solve(testCaseInput: Pair<String, Int>): Long {
        return countOfSubstrings(testCaseInput.first, testCaseInput.second)
    }

    private fun countOfSubstrings(word: String, k: Int): Long {
        val n = word.length
        if (n < 5) return 0

        val vowelMap = hashMapOf<Char, Int>()
        var left = 0
        var count = 0L
        var consonantCount = 0
        var extraLeft = 0

        fun Char.isVowel(): Boolean = when (this) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }

        for (right in 0 until n) {
            val rightChar = word[right]

            when {
                rightChar.isVowel() -> vowelMap[rightChar] = vowelMap.getOrDefault(rightChar, 0) + 1
                else -> consonantCount++
            }

            // Shrink window if consonant count exceeds k
            while (consonantCount > k) {
                val leftChar = word[left]
                if (leftChar.isVowel()) {
                    val value = vowelMap.getValue(leftChar)
                    if (value > 1) vowelMap[leftChar] = value - 1 else vowelMap.remove(leftChar)
                } else {
                    consonantCount--
                }
                left++
                extraLeft = 0
            }

            // Adjust left pointer to remove extra vowels
            while (vowelMap.size == 5 && consonantCount == k && left < right && word[left].isVowel() && vowelMap.getValue(word[left]) > 1) {
                val leftChar = word[left]
                vowelMap[leftChar] = vowelMap.getValue(leftChar) - 1
                extraLeft++
                left++
            }

            // Count valid substrings
            if (consonantCount == k && vowelMap.size == 5) {
                count += 1 + extraLeft
            }
        }

        return count
    }
}
