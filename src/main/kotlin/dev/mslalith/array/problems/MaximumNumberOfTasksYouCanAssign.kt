package dev.mslalith.array.problems

import dev.mslalith.array.problems.MaximumNumberOfTasksYouCanAssign.MaximumNumberOfTasksYouCanAssignParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import dev.mslalith.utils.stringFromArray
import java.util.*
import kotlin.math.min

class MaximumNumberOfTasksYouCanAssign : TestCaseProblem<MaximumNumberOfTasksYouCanAssignParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumNumberOfTasksYouCanAssign().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MaximumNumberOfTasksYouCanAssignParams, Int>> = arrayOf(
        TestCase(
            input = MaximumNumberOfTasksYouCanAssignParams(
                tasks = intArrayOf(3, 2, 1),
                workers = intArrayOf(0, 3, 3),
                pills = 1,
                strength = 1
            ),
            output = 3
        ),
        TestCase(
            input = MaximumNumberOfTasksYouCanAssignParams(
                tasks = intArrayOf(5, 4),
                workers = intArrayOf(0, 0, 0),
                pills = 1,
                strength = 5
            ),
            output = 1
        ),
        TestCase(
            input = MaximumNumberOfTasksYouCanAssignParams(
                tasks = intArrayOf(10, 15, 30),
                workers = intArrayOf(0, 10, 10, 10, 10),
                pills = 3,
                strength = 10
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: MaximumNumberOfTasksYouCanAssignParams): Int {
        return maxTaskAssign(testCaseInput.tasks.createClone(), testCaseInput.workers.createClone(), testCaseInput.pills, testCaseInput.strength)
    }

    private fun maxTaskAssign(tasks: IntArray, workers: IntArray, pills: Int, strength: Int): Int {
        val n = tasks.size
        val m = workers.size

        tasks.sort()
        workers.sort()

        fun tryK(k: Int): Boolean {
            val workersFreq = TreeMap<Int, Int>()
            for (i in (m - k) until m) workersFreq[workers[i]] = workersFreq.getOrDefault(workers[i], 0) + 1

            var p = pills

            for (i in (k - 1) downTo 0) {
                var key = workersFreq.lastKey()
                if (key >= tasks[i]) {
                    workersFreq[key] = workersFreq.getValue(key) - 1
                    if (workersFreq[key] == 0) workersFreq.remove(key)
                } else {
                    if (p == 0) return false
                    key = workersFreq.ceilingKey(tasks[i] - strength)
                    if (key == null) return false

                    workersFreq[key] = workersFreq.getValue(key) - 1
                    if (workersFreq[key] == 0) workersFreq.remove(key)

                    p--
                }
            }

            return true
        }

        var left = 1
        var right = min(n, m)
        var ans = 0

        while (left <= right) {
            val mid = left + (right - left) / 2
            if (tryK(mid)) {
                ans = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return ans
    }

    data class MaximumNumberOfTasksYouCanAssignParams(
        val tasks: IntArray,
        val workers: IntArray,
        val pills: Int,
        val strength: Int
    ) {
        override fun toString(): String {
            return """
                tasks: ${stringFromArray(tasks)}
                workers: ${stringFromArray(workers)}
                pills: $pills
                strength: $strength
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MaximumNumberOfTasksYouCanAssignParams

            if (pills != other.pills) return false
            if (strength != other.strength) return false
            if (!tasks.contentEquals(other.tasks)) return false
            if (!workers.contentEquals(other.workers)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = pills
            result = 31 * result + strength
            result = 31 * result + tasks.contentHashCode()
            result = 31 * result + workers.contentHashCode()
            return result
        }
    }
}