package src.string.problems

import src.core.Problem
import src.core.TestCase

class ReverseStringII : Problem<ReverseStringIIParams, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseStringII().run()
    }

    override fun getTestCases(): Array<TestCase<ReverseStringIIParams, String>> = arrayOf(
//        TestCase(
//            input = ReverseStringIIParams(s = "abcdefg", k = 2),
//            output = "bacdfeg"
//        ),
        TestCase(
            input = ReverseStringIIParams(s = "abcd", k = 4),
            output = "dcba"
        )
    )

    override fun solve(testCaseInput: ReverseStringIIParams): String {
        return reverseStr(testCaseInput.s, testCaseInput.k)
    }

    private fun reverseStr(s: String, k: Int): String {
        val array = s.toCharArray()
        val n = s.length
        var start = 0
        var end = Math.min(n, k)
        while (start < n && end <= n) {
            val half = start + (end - start) / 2
            for (i in start until half) {
                val temp = array[i]
                array[i] = array[start + end - i - 1]
                array[start + end - i - 1] = temp
            }
            start = Math.min(n, end + k)
            end = Math.min(n, start + k)
        }
        return String(array)
    }
}

data class ReverseStringIIParams(
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