package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class LetterTilePossibilities : TestCaseProblem<String, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LetterTilePossibilities().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<String, Int>> = arrayOf(
        TestCase(
            input = "AAB",
            output = 8
        ),
        TestCase(
            input = "AAABBC",
            output = 188
        ),
        TestCase(
            input = "V",
            output = 1
        )
    )
    
    override fun solve(testCaseInput: String): Int {
        return numTilePossibilities(testCaseInput)
    }

    private fun numTilePossibilities(tiles: String): Int {
        val charFrequency = IntArray(26)
        for (ch in tiles) charFrequency[ch - 'A']++

        fun dfs(): Int {
            var count = 0

            for (i in charFrequency.indices) {
                if (charFrequency[i] == 0) continue

                count++
                charFrequency[i]--
                count += dfs()
                charFrequency[i]++
            }

            return count
        }

        return dfs()
    }

    private fun numTilePossibilitiesNaive(tiles: String): Int {
        val set = hashSetOf<String>()
        val usedIndexes = hashSetOf<Int>()

        fun permute(path: StringBuilder) {
            set += path.toString()

            for (i in tiles.indices) {
                if (i in usedIndexes) continue

                usedIndexes += i
                path.append(tiles[i])

                permute(path)

                usedIndexes -= i
                path.deleteAt(path.lastIndex)
            }
        }

        permute(StringBuilder())

        // ignore empty string
        return set.size - 1
    }
}
