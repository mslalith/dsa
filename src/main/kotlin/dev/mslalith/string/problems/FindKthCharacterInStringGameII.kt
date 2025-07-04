package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class FindKthCharacterInStringGameII : TestCaseProblem<Pair<Long, IntArray>, Char>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindKthCharacterInStringGameII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Long, IntArray>, Char>> = arrayOf(
        TestCase(
            input = 5L to intArrayOf(0, 0, 0),
            output = 'a'
        ),
        TestCase(
            input = 10L to intArrayOf(0, 1, 0, 1),
            output = 'b'
        )
    )

    override fun solve(testCaseInput: Pair<Long, IntArray>): Char {
        return kthCharacter(testCaseInput.first, testCaseInput.second)
    }

    fun kthCharacter(k: Long, operations: IntArray): Char {
        val lengths = mutableListOf<Long>()
        var len = 1L

        for (op in operations) {
            len *= 2
            lengths.add(len)
            if (len >= k) break
        }

        var shift = 0
        var remaining = k

        for (i in lengths.lastIndex downTo 0) {
            val half = lengths[i] / 2

            if (remaining > half) {
                remaining -= half
                if (operations[i] == 1) shift++
            }
        }


        return ((shift % 26) + 'a'.code).toChar()
    }

    fun kthCharacterNaive(k: Long, operations: IntArray): Char {
        val sb = StringBuilder("a")

        for (operation in operations) {
            when (operation) {
                0 -> sb.append(sb.toString())
                1 -> {
                    val size = sb.length
                    for (i in 0..<size) {
                        val num = ((sb[i] - 'a') + 1) % 26
                        val next = (num + 'a'.code).toChar()
                        sb.append(next)
                    }
                }
            }

            if (sb.length >= k) break
        }

        return sb[k.toInt() - 1]
    }
}
