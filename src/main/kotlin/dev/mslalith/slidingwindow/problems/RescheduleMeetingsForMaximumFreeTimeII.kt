package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.slidingwindow.problems.RescheduleMeetingsForMaximumFreeTimeII.RescheduleMeetingsForMaximumFreeTimeIIParams
import dev.mslalith.utils.stringFromArray

class RescheduleMeetingsForMaximumFreeTimeII : TestCaseProblem<RescheduleMeetingsForMaximumFreeTimeIIParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RescheduleMeetingsForMaximumFreeTimeII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<RescheduleMeetingsForMaximumFreeTimeIIParams, Int>> = arrayOf(
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIIParams(
                eventTime = 5,
                startTime = intArrayOf(1, 3),
                endTime = intArrayOf(2, 5)
            ),
            output = 2
        ),
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIIParams(
                eventTime = 10,
                startTime = intArrayOf(0, 7, 9),
                endTime = intArrayOf(1, 8, 10)
            ),
            output = 7
        ),
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIIParams(
                eventTime = 10,
                startTime = intArrayOf(0, 3, 7, 9),
                endTime = intArrayOf(1, 4, 8, 10)
            ),
            output = 6
        ),
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIIParams(
                eventTime = 5,
                startTime = intArrayOf(0, 1, 2, 3, 4),
                endTime = intArrayOf(1, 2, 3, 4, 5)
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: RescheduleMeetingsForMaximumFreeTimeIIParams): Int {
        return maxFreeTime(testCaseInput.eventTime, testCaseInput.startTime, testCaseInput.endTime)
    }

    private fun maxFreeTime(eventTime: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size

        var lastEnd = 0
        var maxLeft = 0
        var res = 0

        val gaps = IntArray(n + 1)
        for (i in 0..<n) {
            gaps[i] = startTime[i] - lastEnd
            lastEnd = endTime[i]
        }

        gaps[n] = eventTime - lastEnd

        val maxRight = IntArray(n + 1)
        for (i in n - 1 downTo 0) maxRight[i] = maxOf(maxRight[i + 1], gaps[i + 1])

        for (i in 1..n) {
            val dur = endTime[i - 1] - startTime[i - 1]

            if (maxLeft >= dur || maxRight[i] >= dur) res = maxOf(res, gaps[i - 1] + dur + gaps[i])

            res = maxOf(res, gaps[i - 1] + gaps[i])
            maxLeft = maxOf(maxLeft, gaps[i - 1])
        }

        return res
    }

    data class RescheduleMeetingsForMaximumFreeTimeIIParams(
        val eventTime: Int,
        val startTime: IntArray,
        val endTime: IntArray
    ) {
        override fun toString(): String {
            return """
                
                eventTime: $eventTime
                startTime: ${stringFromArray(startTime)}
                endTime: ${stringFromArray(endTime)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as RescheduleMeetingsForMaximumFreeTimeIIParams

            if (eventTime != other.eventTime) return false
            if (!startTime.contentEquals(other.startTime)) return false
            if (!endTime.contentEquals(other.endTime)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = eventTime
            result = 31 * result + startTime.contentHashCode()
            result = 31 * result + endTime.contentHashCode()
            return result
        }
    }
}
