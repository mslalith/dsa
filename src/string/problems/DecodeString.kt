package src.string.problems

import src.core.Problem
import src.core.TestCase

class DecodeString : Problem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DecodeString().run()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "3[a]2[bc]",
            output = "aaabcbc"
        ),
        TestCase(
            input = "3[a2[c]]",
            output = "accaccacc"
        ),
        TestCase(
            input = "2[abc]3[cd]ef",
            output = "abcabccdcdcdef"
        ),
        TestCase(
            input = "100[leetcode]",
            output = "leetcode".repeat(100)
        )
    )

    override fun solve(testCaseInput: String): String {
        return decodeString(testCaseInput)
    }

    private fun decodeString(s: String): String {
        val sb = StringBuilder()

        var i = 0
        while (i < s.length) {
            var count = 0
            while (i < s.length && s[i] in '0'..'9') {
                count = (count * 10) + s[i].digitToInt()
                i++
            }

            if (s[i] == '[') {
                i++
                var opens = 0
                val openIndex = i
                while (i < s.length) {
                    if (s[i] == '[') opens++
                    else if (s[i] == ']') {
                        if (opens == 0) break
                        else opens--
                    }
                    i++
                }
                val pattern = s.substring(openIndex, i)
                val pat = if (pattern.contains('[')) decodeString(pattern) else pattern
                repeat(count) { sb.append(pat) }
            } else sb.append(s[i])
            i++
        }

        return sb.toString()
    }
}