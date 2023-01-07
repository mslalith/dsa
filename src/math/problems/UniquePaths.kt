package src.math.problems

import src.core.Problem
import src.core.TestCase

class UniquePaths : Problem<UniquePathsParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = UniquePaths().run()
    }

    override fun getTestCases(): Array<TestCase<UniquePathsParams, Int>> = arrayOf(
        TestCase(
            input = UniquePathsParams(m = 3, n = 2),
            output = 3
        ),
        TestCase(
            input = UniquePathsParams(m = 3, n = 7),
            output = 28
        )
    )

    override fun solve(testCaseInput: UniquePathsParams): Int {
        return uniquePaths(testCaseInput.m, testCaseInput.n)
    }

    private fun uniquePaths(m: Int, n: Int): Int {
        val cache = hashMapOf<String, Int>()
        return uniquePaths(0, 0, m - 1, n - 1, cache)
    }

    private fun uniquePaths(x: Int, y: Int, m: Int, n: Int, cache: HashMap<String, Int>): Int {
        if (x == m && y == n) return 1
        if (x > m || y > n) return 0
        val rightKey = "${x + 1}-$y"
        val downKey = "$x-${y + 1}"
        val right = if (cache.containsKey(rightKey)) cache.getValue(rightKey) else {
            uniquePaths(x + 1, y, m, n, cache).also { cache[rightKey] = it }
        }
        val down = if (cache.containsKey(downKey)) cache.getValue(downKey) else {
            uniquePaths(x, y + 1, m, n, cache).also { cache[downKey] = it }
        }
        return right + down
    }
}

data class UniquePathsParams(
    val m: Int,
    val n: Int
) {
    override fun toString(): String {
        return """
            
            m: $m
            n: $n
        """.trimIndent()
    }
}