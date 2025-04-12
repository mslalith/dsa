package dev.mslalith.math.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindTheCountOfGoodIntegers : TestCaseProblem<Pair<Int, Int>, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindTheCountOfGoodIntegers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Long>> = arrayOf(
        TestCase(
            input = 3 to 5,
            output = 27
        ),
        TestCase(
            input = 1 to 4,
            output = 2
        ),
        TestCase(
            input = 5 to 6,
            output = 2468
        ),
        TestCase(
            input = 10 to 1,
            output = 41457024
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): Long {
        return countGoodIntegers(testCaseInput.first, testCaseInput.second)
    }

    private fun countGoodIntegers(n: Int, k: Int): Long {
        val factorial = IntArray(11) { it }
        for (i in 2..10) factorial[i] = factorial[i - 1] * i

        var goodIntegersCount = 0L
        val processed = hashSetOf<Long>()

        fun countAllPermutations(frequency: IntArray, length: Int): Int {
            var count = factorial[length]
            for (i in 0..9) {
                if (frequency[i] == 0) continue
                count /= factorial[frequency[i]]
            }
            return count
        }

        fun isKPalindrome(numArray: CharArray): Boolean {
            return numArray.joinToString(separator = "").toLong() % k == 0L
        }

        fun allArrangements(numArray: CharArray): Int {
            val num = numArray.sorted().joinToString(separator = "").toLong()
            if (num in processed) return 0

            processed += num

            val frequency = IntArray(10)
            for (i in numArray) frequency[i - '0']++

            val totalPermutations = countAllPermutations(frequency, n)
            val invalidPermutations = if (frequency[0] == 0) 0 else {
                frequency[0]--
                countAllPermutations(frequency, n - 1)
            }

            return totalPermutations - invalidPermutations
        }

        fun generatePalindromes(pos: Int, numArray: CharArray) {
            if (pos >= (n + 1) / 2) {
                if (isKPalindrome(numArray)) goodIntegersCount += allArrangements(numArray)
                return
            }

            var start = if (pos == 0) '1' else '0'
            while (start <= '9') {
                numArray[pos] = start
                numArray[n - pos - 1] = start
                generatePalindromes(pos + 1, numArray)
                start++
            }
            numArray[pos] = ' '
        }

        val numArray = CharArray(n) { ' ' }
        generatePalindromes(0, numArray)

        return goodIntegersCount
    }
}
