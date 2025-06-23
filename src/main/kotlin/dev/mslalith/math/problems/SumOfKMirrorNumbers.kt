package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class SumOfKMirrorNumbers : TestCaseProblem<Pair<Int, Int>, Long>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SumOfKMirrorNumbers().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Long>> = arrayOf(
        TestCase(
            input = 2 to 5,
            output = 25
        ),
        TestCase(
            input = 3 to 7,
            output = 499
        ),
        TestCase(
            input = 7 to 17,
            output = 20379000
        )
    )

    override fun solve(testCaseInput: Pair<Int, Int>): Long {
        return kMirror(testCaseInput.first, testCaseInput.second)
    }

    private fun kMirror(k: Int, n: Int): Long {

        fun createPalindrome(num: Long, odd: Boolean): Long {
            var x = num
            var y = num
            if (odd) x /= 10

            while (x > 0) {
                y = y * 10 + x % 10
                x /= 10
            }

            return y
        }

        fun isPalindrome(num: Long, base: Int): Boolean {
            val sb = StringBuilder()
            var x = num

            while (x > 0) {
                sb.append(((x % base) + '0'.code).toInt().toChar())
                x /= base.toLong()
            }

            var i = 0
            var j = sb.length - 1
            while (i < j) if (sb[i++] != sb[j--]) return false

            return true
        }


        var sum = 0L
        var count = 0
        var len = 1L

        while (count < n) {
            var i = len
            while (count < n && i < len * 10) {
                val p = createPalindrome(i, true)
                if (isPalindrome(p, k)) {
                    sum += p
                    count++
                }
                i++
            }

            i = len
            while (count < n && i < len * 10) {
                val p = createPalindrome(i, false)
                if (isPalindrome(p, k)) {
                    sum += p
                    count++
                }
                i++
            }

            len *= 10
        }

        return sum
    }
}
