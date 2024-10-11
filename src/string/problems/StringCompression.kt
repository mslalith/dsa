package src.string.problems

import src.core.Problem
import src.core.TestCase
import java.util.Stack

class StringCompression : Problem<CharArray, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = StringCompression().run()
    }

    override fun getTestCases(): Array<TestCase<CharArray, Int>> = arrayOf(
        TestCase(
            input = charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c'),
            output = 6
        ),
        TestCase(
            input = charArrayOf('a'),
            output = 1
        ),
        TestCase(
            input = charArrayOf('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'),
            output = 4
        )
    )

    override fun solve(testCaseInput: CharArray): Int {
        return compress(testCaseInput)
    }

    private fun compress(chars: CharArray): Int {
        val n = chars.size
        if (n < 2) return n

        var char = chars[0]
        var count = 1
        var x = 0

        fun writeCount() {
            if (count == 1) return

            if (count < 10) chars[x++] = count.digitToChar() else {
                val stack = Stack<Int>()

                var a = count
                while (a != 0) {
                    stack.push(a % 10)
                    a /= 10
                }

                while (stack.isNotEmpty()) {
                    chars[x++] = stack.pop().digitToChar()
                }
            }
        }

        for (i in 1 until n) {
            if (char == chars[i]) {
                count++
            } else {
                chars[x++] = char
                writeCount()

                char = chars[i]
                count = 1
            }
        }
        chars[x++] = char
        writeCount()

        return x
    }

    private fun compressNaive(chars: CharArray): Int {
        val n = chars.size
        if (n < 2) return n

        var char = chars[0]
        var count = 1
        val sb = StringBuilder()

        for (i in 1 until n) {
            if (char == chars[i]) {
                count++
            } else {
                sb.append(char)
                if (count != 1) sb.append(count)

                char = chars[i]
                count = 1
            }
        }
        sb.append(char)
        if (count != 1) sb.append(count)

        for (i in sb.indices) {
            chars[i] = sb[i]
        }

        return sb.length
    }
}
