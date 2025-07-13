package dev.mslalith.twopointers.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone

class MaximumMatchingOfPlayersWithTrainers : TestCaseProblem<Pair<IntArray, IntArray>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumMatchingOfPlayersWithTrainers().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, IntArray>, Int>> = arrayOf(
        TestCase(
            input = intArrayOf(4, 7, 9) to intArrayOf(8, 2, 5, 8),
            output = 2
        ),
        TestCase(
            input = intArrayOf(1, 1, 1) to intArrayOf(10),
            output = 1
        ),
        TestCase(
            input = intArrayOf(2, 1) to intArrayOf(2, 1, 2, 2, 3, 3, 2, 4, 1, 1, 4, 1, 3, 3, 4, 1, 3, 2, 3, 2, 2, 3, 1, 2, 4),
            output = 2
        )
    ).let { arrayOf(it.last()) }

    override fun solve(testCaseInput: Pair<IntArray, IntArray>): Int {
        return matchPlayersAndTrainers(testCaseInput.first.createClone(), testCaseInput.second.createClone())
    }

    private fun matchPlayersAndTrainers(players: IntArray, trainers: IntArray): Int {
        players.sort()
        trainers.sort()

        var i = 0
        var j = 0
        var count = 0

        while (i < players.size && j < trainers.size) {
            if (players[i] <= trainers[j]) {
                count++
                i++
            }
            j++
        }

        return count
    }
}
