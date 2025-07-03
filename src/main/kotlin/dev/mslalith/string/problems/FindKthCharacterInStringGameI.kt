package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class FindKthCharacterInStringGameI : TestCaseProblem<Int, Char>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindKthCharacterInStringGameI().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Char>> = arrayOf(
        TestCase(
            input = 5,
            output = 'b'
        ),
        TestCase(
            input = 10,
            output = 'c'
        )
    )
    
    override fun solve(testCaseInput: Int): Char {
        return kthCharacterOptimal(testCaseInput)
    }

    private fun kthCharacterOptimal(k: Int): Char = 'a' + Integer.bitCount(k - 1)

    private fun kthCharacter(k: Int): Char {
        val sb = StringBuilder("a")

        while (sb.length < k) {
            val size = sb.length
            for (i in 0..<size) {
                val num = ((sb[i] - 'a') + 1) % 26
                val next = (num + 'a'.code).toChar()
                sb.append(next)
            }
        }

        return sb[k - 1]
    }
}
