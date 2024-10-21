package dev.mslalith.dp.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class DominoAndTrominoTiling : TestCaseProblem<Int, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DominoAndTrominoTiling().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<Int, Int>> = arrayOf(
        TestCase(
            input = 1,
            output = 1
        ),
        TestCase(
            input = 2,
            output = 2
        ),
        TestCase(
            input = 3,
            output = 5
        ),
        TestCase(
            input = 4,
            output = 11
        ),
        TestCase(
            input = 5,
            output = 24
        ),
        TestCase(
            input = 30,
            output = 312342182
        )
    )
    
    override fun solve(testCaseInput: Int): Int {
        return numTilings(testCaseInput)
    }

    private fun numTilings(n: Int): Int {
        return when (n) {
            1 -> 1
            2 -> 2
            3 -> 5
            else -> {
                val mod = 1000000007
                val cache = Array(n + 1) { 0 }
                cache[0] = 0
                cache[1] = 1
                cache[2] = 2
                cache[3] = 5
                for (i in 4..n) {
                    val one = (cache[i - 1] * 2) % mod
                    val three = (cache[i - 3]) % mod
                    cache[i] = (one + three) % mod
                }
                cache[n]
            }
        }
    }

    private fun numTilingsRecursive(n: Int): Int {
        val cache = hashMapOf(
            1 to 1,
            2 to 2,
            3 to 5
        )

        fun numTilings(n: Int, cache: HashMap<Int, Int>): Int {
            if (n == 1 || n == 2) return cache.getValue(n)

            val mod = 1000000007
            return cache.getOrPut(n) {
                val ways = (numTilings(n - 1, cache) * 2) % mod + numTilings(n - 3, cache) % mod
                ways % mod
            }
        }

        return numTilings(n, cache)
    }
}
