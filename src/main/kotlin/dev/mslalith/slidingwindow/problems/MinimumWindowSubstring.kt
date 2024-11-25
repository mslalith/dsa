package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class MinimumWindowSubstring : TestCaseProblem<Pair<String, String>, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumWindowSubstring().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, String>> = arrayOf(
        TestCase(
            input = "ADOBECODEBANC" to "ABC",
            output = "BANC"
        ),
        TestCase(
            input = "a" to "a",
            output = "a"
        ),
        TestCase(
            input = "a" to "aa",
            output = ""
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): String {
        return minWindow(testCaseInput.first, testCaseInput.second)
    }

    private fun minWindow(s: String, t: String): String {
        if (s == t) return s

        val m = s.length
        val n = t.length

        if (m < n) return ""

        val charToCountMap = linkedMapOf<Char, Int>()
        for (ch in t) charToCountMap[ch] = charToCountMap.getOrDefault(ch, 0) + 1

        var left = 0
        var right = 0
        var remaining = n
        var startIndex = 0
        var minLen = Int.MAX_VALUE

        while (right < m) {
            val rChar = s[right]
            // char found, reduce remaining
            if (rChar in charToCountMap && charToCountMap.getValue(rChar) > 0) remaining--

            // consume char by reducing count
            charToCountMap[rChar] = charToCountMap.getOrDefault(rChar, 0) - 1
            right++

            // found all the chars
            while (remaining == 0) {
                // update minimum
                if (right - left < minLen) {
                    startIndex = left
                    minLen = right - left
                }

                val lChar = s[left]
                // count < 0 indicates we have more than required lChar in the window
                // count == 0 indicates we have exactly same amount of chars in the window
                // so after losing lChar, we would require 1 more char
                if (charToCountMap[lChar] == 0) remaining++

                // as we are losing lChar, add it back to the map (leave the consuming char)
                charToCountMap[lChar] = charToCountMap.getOrDefault(lChar, 0) + 1
                left++
            }
        }

        return if (minLen == Int.MAX_VALUE) "" else s.substring(startIndex, startIndex + minLen)
    }
}
