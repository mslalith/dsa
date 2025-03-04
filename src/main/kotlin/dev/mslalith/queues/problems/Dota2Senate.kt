package dev.mslalith.queues.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class Dota2Senate : TestCaseProblem<String, String>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = Dota2Senate().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, String>> = arrayOf(
        TestCase(
            input = "RD",
            output = "Radiant"
        ),
        TestCase(
            input = "RDD",
            output = "Dire"
        ),
        TestCase(
            input = "DRRDRDRDRRRDD",
            output = "Radiant"
        )
    )
    
    override fun solve(testCaseInput: String): String {
        return predictPartyVictory(testCaseInput)
    }

    private fun predictPartyVictory(senate: String): String {
        if (senate.isEmpty()) return ""

        var n = senate.length
        val radiantQueue = ArrayDeque<Int>()
        val direQueue = ArrayDeque<Int>()

        for (i in senate.indices) {
            if (senate[i] == 'R') radiantQueue.add(i) else direQueue.add(i)
        }

        while (radiantQueue.isNotEmpty() && direQueue.isNotEmpty()) {
            if (radiantQueue.first() < direQueue.first()) {
                radiantQueue.add(n++)
            } else {
                direQueue.add(n++)
            }
            radiantQueue.removeFirst()
            direQueue.removeFirst()
        }

        return if (radiantQueue.isNotEmpty()) "Radiant" else "Dire"
    }
}
