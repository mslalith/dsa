package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.dp.problems.EarliestAndLatestRoundsWherePlayersCompete.EarliestAndLatestRoundsWherePlayersCompeteParams

class EarliestAndLatestRoundsWherePlayersCompete : TestCaseProblem<EarliestAndLatestRoundsWherePlayersCompeteParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = EarliestAndLatestRoundsWherePlayersCompete().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<EarliestAndLatestRoundsWherePlayersCompeteParams, IntArray>> = arrayOf(
        TestCase(
            input = EarliestAndLatestRoundsWherePlayersCompeteParams(
                n = 11,
                firstPlayer = 2,
                secondPlayer = 4
            ),
            output = intArrayOf(3, 4)
        ),
        TestCase(
            input = EarliestAndLatestRoundsWherePlayersCompeteParams(
                n = 5,
                firstPlayer = 1,
                secondPlayer = 5
            ),
            output = intArrayOf(1, 1)
        )
    )

    override fun solve(testCaseInput: EarliestAndLatestRoundsWherePlayersCompeteParams): IntArray {
        return earliestAndLatest(testCaseInput.n, testCaseInput.firstPlayer, testCaseInput.secondPlayer)
    }

    private fun earliestAndLatest(n: Int, firstPlayer: Int, secondPlayer: Int): IntArray {
        var left = minOf(firstPlayer, secondPlayer)
        var right = maxOf(firstPlayer, secondPlayer)

        if (left + right == n + 1) return intArrayOf(1, 1)
        if (n == 3 || n == 4) return intArrayOf(2, 2)

        if (left - 1 > n - right) {
            val temp = n + 1 - left
            left = n + 1 - right
            right = temp
        }

        val nextRound = (n + 1) / 2
        var minRound = n
        var maxRound = 1

        if (right * 2 <= n + 1) {
            val preLeft = left - 1
            val midGap = right - left - 1
            for (i in 0..preLeft) {
                for (j in 0..midGap) {
                    val res = earliestAndLatest(nextRound, i + 1, i + j + 2)
                    minRound = minOf(minRound, 1 + res[0])
                    maxRound = maxOf(maxRound, 1 + res[1])
                }
            }
        } else {
            val mirrored = n + 1 - right
            val preLeft = left - 1
            val midGap = mirrored - left - 1
            val innerGap = right - mirrored - 1
            for (i in 0..preLeft) {
                for (j in 0..midGap) {
                    val res = earliestAndLatest(nextRound, i + 1, i + j + 1 + (innerGap + 1) / 2 + 1)
                    minRound = minOf(minRound, 1 + res[0])
                    maxRound = maxOf(maxRound, 1 + res[1])
                }
            }
        }

        return intArrayOf(minRound, maxRound)
    }

    data class EarliestAndLatestRoundsWherePlayersCompeteParams(
        val n: Int,
        val firstPlayer: Int,
        val secondPlayer: Int
    ) {
        override fun toString(): String {
            return """
                
                n: $n
                firstPlayer: $firstPlayer
                secondPlayer: $secondPlayer
            """.trimIndent()
        }
    }
}
