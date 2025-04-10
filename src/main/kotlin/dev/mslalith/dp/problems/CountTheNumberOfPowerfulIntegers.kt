package dev.mslalith.dp.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.dp.problems.CountTheNumberOfPowerfulIntegers.CountTheNumberOfPowerfulIntegersParams
import kotlin.math.pow


class CountTheNumberOfPowerfulIntegers : TestCaseProblem<CountTheNumberOfPowerfulIntegersParams, Long>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CountTheNumberOfPowerfulIntegers().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<CountTheNumberOfPowerfulIntegersParams, Long>> = arrayOf(
        TestCase(
            input = CountTheNumberOfPowerfulIntegersParams(
                start = 1,
                finish = 6000,
                limit = 4,
                s = "124"
            ),
            output = 5
        ),
        TestCase(
            input = CountTheNumberOfPowerfulIntegersParams(
                start = 15,
                finish = 215,
                limit = 6,
                s = "10"
            ),
            output = 2
        ),
        TestCase(
            input = CountTheNumberOfPowerfulIntegersParams(
                start = 1000,
                finish = 2000,
                limit = 4,
                s = "3000"
            ),
            output = 0
        )
    )
    
    override fun solve(testCaseInput: CountTheNumberOfPowerfulIntegersParams): Long {
        return numberOfPowerfulInt(testCaseInput.start, testCaseInput.finish, testCaseInput.limit, testCaseInput.s)
    }

    private fun numberOfPowerfulInt(start: Long, finish: Long, limit: Int, s: String): Long {
        val suffix = s.toLong()
        if (suffix > finish) return 0

        val div = 10.0.pow(s.length).toLong()
        var startPrefix = start / div
        var finishPrefix = finish / div

        // adjust bounds
        if (start % div > suffix) startPrefix++
        if (finish % div >= suffix) finishPrefix++

        fun powerfulCountTill(num: Long): Long {
            if (num == 0L) return 0
            if (limit == 9) return num

            val numLen = num.toString().length
            var divisor = 10.0.pow(numLen).toLong()
            var res = 0L
            var x = num

            for (i in numLen downTo 0) {
                val d = x / divisor
                if (d > limit) return res + (limit + 1.0).pow(i + 1.0).toLong()

                res += d * (limit + 1.0).pow(i.toDouble()).toLong()

                x %= divisor
                divisor /= 10
            }

            return res
        }

        return powerfulCountTill(finishPrefix) - powerfulCountTill(startPrefix)
    }

    data class CountTheNumberOfPowerfulIntegersParams(
        val start: Long,
        val finish: Long,
        val limit: Int,
        val s: String
    ) {

        override fun toString(): String {
            return """
                
                start: $start
                finish: $finish
                limit: $limit
                s: $s
            """.trimIndent()
        }
    }
}
