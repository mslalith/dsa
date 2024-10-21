package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import kotlin.math.max

class LongestRepeatingCharacterReplacement : Problem<LongestRepeatingCharacterReplacementParams, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LongestRepeatingCharacterReplacement().run()
    }
    
    override fun getTestCases(): Array<TestCase<LongestRepeatingCharacterReplacementParams, Int>> = arrayOf(
        TestCase(
            input = LongestRepeatingCharacterReplacementParams(
                s = "ABAB",
                k = 2
            ),
            output = 4
        ),
        TestCase(
            input = LongestRepeatingCharacterReplacementParams(
                s = "AABABBA",
                k = 1
            ),
            output = 4
        ),
        TestCase(
            input = LongestRepeatingCharacterReplacementParams(
                s = "AAAA",
                k = 2
            ),
            output = 4
        )
    )
    
    override fun solve(testCaseInput: LongestRepeatingCharacterReplacementParams): Int {
        return characterReplacement(testCaseInput.s, testCaseInput.k)
    }

    private fun characterReplacement(s: String, k: Int): Int {
        val array = IntArray(26) { 0 }
        var frequencyCount = 0
        var left = 0
        var max = 0

        for (right in s.indices) {
            array[s[right] - 'A']++
            frequencyCount = max(frequencyCount, array[s[right] - 'A'])

            val lettersChanged = (right - left + 1) - frequencyCount
            if (lettersChanged > k) {
                array[s[left] - 'A']--
                left++
            }
            max = max(max, right - left + 1)
        }

        return max
    }
}

data class LongestRepeatingCharacterReplacementParams(
    val s: String,
    val k: Int
) {
    override fun toString(): String {
        return """
            
            s: $s
            k: $k
        """.trimIndent()
    }
}