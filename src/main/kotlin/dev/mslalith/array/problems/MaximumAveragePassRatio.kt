package dev.mslalith.array.problems

import dev.mslalith.array.problems.MaximumAveragePassRatio.MaximumAveragePassRatioParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray
import java.util.*

class MaximumAveragePassRatio : TestCaseProblem<MaximumAveragePassRatioParams, Double>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumAveragePassRatio().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MaximumAveragePassRatioParams, Double>> = arrayOf(
        TestCase(
            input = MaximumAveragePassRatioParams(
                classes = arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3, 5),
                    intArrayOf(2, 2)
                ),
                extraStudents = 2
            ),
            output = 0.78333
        ),
        TestCase(
            input = MaximumAveragePassRatioParams(
                classes = arrayOf(
                    intArrayOf(2, 4),
                    intArrayOf(3, 9),
                    intArrayOf(4, 5),
                    intArrayOf(2, 10)
                ),
                extraStudents = 4
            ),
            output = 0.53485
        )
    )

    override fun solve(testCaseInput: MaximumAveragePassRatioParams): Double {
        return maxAverageRatio(testCaseInput.classes, testCaseInput.extraStudents)
    }

    private fun maxAverageRatio(classes: Array<IntArray>, extraStudents: Int): Double {

        fun gain(pass: Int, total: Int): Double = (pass + 1.0) / (total + 1) - pass.toDouble() / total

        val pq = PriorityQueue<IntArray> { a, b ->
            gain(b[0], b[1]).compareTo(gain(a[0], a[1]))
        }

        pq.addAll(classes)

        var extra = extraStudents
        while (extra-- > 0) {
            val top = pq.poll()
            top[0]++
            top[1]++
            pq.add(top)
        }

        return pq.sumOf { it[0] / it[1].toDouble() } / classes.size
    }

    data class MaximumAveragePassRatioParams(
        val classes: Array<IntArray>,
        val extraStudents: Int
    ) {
        override fun toString(): String {
            return """
                
                classes: ${stringFromArray(classes)}
                extraStudents: $extraStudents
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MaximumAveragePassRatioParams

            if (extraStudents != other.extraStudents) return false
            if (!classes.contentDeepEquals(other.classes)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = extraStudents
            result = 31 * result + classes.contentDeepHashCode()
            return result
        }
    }
}
