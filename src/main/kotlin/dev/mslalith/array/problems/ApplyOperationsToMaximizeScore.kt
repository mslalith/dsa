package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.*
import kotlin.math.min

class ApplyOperationsToMaximizeScore : TestCaseProblem<Pair<List<Int>, Int>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ApplyOperationsToMaximizeScore().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<List<Int>, Int>, Int>> = arrayOf(
        TestCase(
            input = listOf(8, 3, 9, 3, 8) to 2,
            output = 81
        ),
        TestCase(
            input = listOf(19, 12, 14, 6, 10, 18) to 3,
            output = 4788
        )
    )

    override fun solve(testCaseInput: Pair<List<Int>, Int>): Int {
        return maximumScore(testCaseInput.first, testCaseInput.second)
    }

    private fun maximumScore(nums: List<Int>, k: Int): Int {
        val n = nums.size
        val mod = 1_000_000_000 + 7

        fun primeScore(num: Int): Int {
            var score = 0

            var x = num
            var i = 2

            while (i * i <= x) {
                if (x % i == 0) {
                    score++
                    while (x % i == 0) x /= i
                }
                i++
            }

            if (x > 1) score++
            return score
        }

        val primeScores = IntArray(n) { primeScore(nums[it]) }
        val left = IntArray(n) { -1 }
        val right = IntArray(n) { n }
        val stack = Stack<Int>()

        for (i in 0 until n) {
            while (stack.isNotEmpty() && primeScores[stack.peek()] < primeScores[i]) stack.pop()
            if (stack.isNotEmpty()) left[i] = stack.peek()
            stack.push(i)
        }

        stack.clear()
        for (i in (n - 1) downTo 0) {
            while (stack.isNotEmpty() && primeScores[stack.peek()] <= primeScores[i]) stack.pop()
            if (stack.isNotEmpty()) right[i] = stack.peek()
            stack.push(i)
        }

        fun power(num: Long, times: Long): Long {
            var res = 1L
            var base = num
            var exponent = times

            while (exponent > 0) {
                if (exponent % 2 == 1L) res = (res * base) % mod
                base = (base * base) % mod
                exponent /= 2
            }

            return res
        }


        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->
            when {
                b.second == a.second -> a.first - b.first
                else -> b.second - a.second
            }
        }
        for (i in 0 until n) pq.add(i to nums[i])

        var score = 1L
        var remainingK = k.toLong()

        while (remainingK > 0) {
            val (index, num) = pq.poll()
            val operations = min(remainingK, 1L * (index - left[index]) * (right[index] - index))
            score = ((score * power(num.toLong(), operations)) % mod)
            remainingK -= operations
        }

        return score.toInt()
    }
}
