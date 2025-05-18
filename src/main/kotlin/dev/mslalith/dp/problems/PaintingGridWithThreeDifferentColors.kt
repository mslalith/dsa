package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem


class PaintingGridWithThreeDifferentColors : TestCaseProblem<Pair<Int, Int>, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PaintingGridWithThreeDifferentColors().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Int>, Int>> = arrayOf(
        TestCase(
            input = 1 to 1,
            output = 3
        ),
        TestCase(
            input = 1 to 2,
            output = 6
        ),
        TestCase(
            input = 5 to 5,
            output = 580986
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Int>): Int {
        return colorTheGrid(testCaseInput.first, testCaseInput.second)
    }

    private fun colorTheGrid(m: Int, n: Int): Int {
        val mod = 1_000_000_000 + 7
        val totalColors = 3

        val states = mutableListOf<IntArray>()

        fun generateStates(current: IntArray, row: Int) {
            if (row == m) {
                states.add(current.clone())
                return
            }

            for (color in 1..totalColors) {
                if (row > 0 && current[row - 1] == color) continue
                current[row] = color
                generateStates(current, row + 1)
            }
        }

        fun isCompatible(a: IntArray, b: IntArray): Boolean {
            for (i in 0 until a.size) if (a[i] == b[i]) return false
            return true
        }

        generateStates(IntArray(m) { 0 }, 0)

        val transitions = hashMapOf<String, MutableList<String>>()
        for (a in states) {
            val keyA = a.contentToString()
            transitions.putIfAbsent(keyA, mutableListOf())

            for (b in states) {
                if (isCompatible(a, b)) transitions.getValue(keyA).add(b.contentToString())
            }
        }

        var dp = hashMapOf<String, Int>()
        for (state in states) dp[state.contentToString()] = 1


        for (col in 1..<n) {
            val newDp = hashMapOf<String, Int>()
            for (prev in dp.keys) {
                val count = dp.getValue(prev)
                for (next in transitions.getValue(prev)) {
                    newDp[next] = (newDp.getOrDefault(next, 0) + count) % mod
                }
            }

            dp = newDp
        }

        var result = 0
        for (value in dp.values) result = (result + value) % mod
        return result
    }
}
