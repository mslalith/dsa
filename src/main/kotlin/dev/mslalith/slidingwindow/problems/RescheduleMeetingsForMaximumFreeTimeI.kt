package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.slidingwindow.problems.RescheduleMeetingsForMaximumFreeTimeI.RescheduleMeetingsForMaximumFreeTimeIParams
import dev.mslalith.utils.stringFromArray

class RescheduleMeetingsForMaximumFreeTimeI : TestCaseProblem<RescheduleMeetingsForMaximumFreeTimeIParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RescheduleMeetingsForMaximumFreeTimeI().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<RescheduleMeetingsForMaximumFreeTimeIParams, Int>> = arrayOf(
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIParams(
                eventTime = 5,
                k = 1,
                startTime = intArrayOf(1, 3),
                endTime = intArrayOf(2, 5)
            ),
            output = 2
        ),
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIParams(
                eventTime = 10,
                k = 1,
                startTime = intArrayOf(0, 2, 9),
                endTime = intArrayOf(1, 4, 10)
            ),
            output = 6
        ),
        TestCase(
            input = RescheduleMeetingsForMaximumFreeTimeIParams(
                eventTime = 5,
                k = 2,
                startTime = intArrayOf(0, 1, 2, 3, 4),
                endTime = intArrayOf(1, 2, 3, 4, 5)
            ),
            output = 0
        )
    )

    override fun solve(testCaseInput: RescheduleMeetingsForMaximumFreeTimeIParams): Int {
        return maxFreeTime(testCaseInput.eventTime, testCaseInput.k, testCaseInput.startTime, testCaseInput.endTime)
    }

    private fun maxFreeTime(eventTime: Int, k: Int, startTime: IntArray, endTime: IntArray): Int {
        val n = startTime.size

        val sum = IntArray(n + 1)
        for (i in 0..<n) sum[i + 1] = sum[i] + endTime[i] - startTime[i]

        var res = 0

        for (i in k - 1..<n) {
            val right = if (i == n - 1) eventTime else startTime[i + 1]
            val left = if (i == k - 1) 0 else endTime[i - k]
            res = maxOf(res, right - left - (sum[i + 1] - sum[i - k + 1]))
        }

        return res
    }

    data class RescheduleMeetingsForMaximumFreeTimeIParams(
        val eventTime: Int,
        val k: Int,
        val startTime: IntArray,
        val endTime: IntArray
    ) {
        override fun toString(): String {
            return """
                
                eventTime: $eventTime
                k: $k
                startTime: ${stringFromArray(startTime)}
                endTime: ${stringFromArray(endTime)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as RescheduleMeetingsForMaximumFreeTimeIParams

            if (eventTime != other.eventTime) return false
            if (k != other.k) return false
            if (!startTime.contentEquals(other.startTime)) return false
            if (!endTime.contentEquals(other.endTime)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = eventTime
            result = 31 * result + k
            result = 31 * result + startTime.contentHashCode()
            result = 31 * result + endTime.contentHashCode()
            return result
        }
    }
}
