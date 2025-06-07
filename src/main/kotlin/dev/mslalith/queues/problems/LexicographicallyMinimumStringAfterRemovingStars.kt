package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.PriorityQueue

class LexicographicallyMinimumStringAfterRemovingStars : TestCaseProblem<String, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LexicographicallyMinimumStringAfterRemovingStars().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "aaba*",
            output = "aab"
        ),
        TestCase(
            input = "abc",
            output = "abc"
        ),
        TestCase(
            input = "d*c",
            output = "c"
        )
    )

    override fun solve(testCaseInput: String): String {
        return clearStars(testCaseInput)
    }

    private fun clearStars(s: String): String {
        val pq = PriorityQueue<Pair<Char, Int>> { a, b ->
            if (a.first != b.first) a.first - b.first else b.second - a.second
        }

        val sArray = s.toCharArray()
        var starCount = 0

        for (i in s.indices) {
            val ch = s[i]
            if (ch == '*') {
                if (pq.isNotEmpty()) sArray[pq.poll().second] = '-'
                sArray[i] = '-'
                starCount++
            } else {
                pq.add(ch to i)
            }
        }

        if (starCount == 0) return s

        return buildString { for (ch in sArray) if (ch != '-') append(ch) }
    }
}
