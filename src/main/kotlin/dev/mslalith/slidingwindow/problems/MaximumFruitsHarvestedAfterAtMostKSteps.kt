package dev.mslalith.slidingwindow.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.slidingwindow.problems.MaximumFruitsHarvestedAfterAtMostKSteps.MaximumFruitsHarvestedAfterAtMostKStepsParams
import dev.mslalith.utils.stringFromArray
import kotlin.math.abs

class MaximumFruitsHarvestedAfterAtMostKSteps : TestCaseProblem<MaximumFruitsHarvestedAfterAtMostKStepsParams, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumFruitsHarvestedAfterAtMostKSteps().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<MaximumFruitsHarvestedAfterAtMostKStepsParams, Int>> = arrayOf(
        TestCase(
            input = MaximumFruitsHarvestedAfterAtMostKStepsParams(
                fruits = arrayOf(
                    intArrayOf(2, 8),
                    intArrayOf(6, 3),
                    intArrayOf(8, 6)
                ),
                startPos = 5,
                k = 4
            ),
            output = 9
        ),
        TestCase(
            input = MaximumFruitsHarvestedAfterAtMostKStepsParams(
                fruits = arrayOf(
                    intArrayOf(0, 9),
                    intArrayOf(4, 1),
                    intArrayOf(5, 7),
                    intArrayOf(6, 2),
                    intArrayOf(7, 4),
                    intArrayOf(10, 9)
                ),
                startPos = 5,
                k = 4
            ),
            output = 16
        ),
        TestCase(
            input = MaximumFruitsHarvestedAfterAtMostKStepsParams(
                fruits = arrayOf(
                    intArrayOf(0, 3),
                    intArrayOf(6, 4),
                    intArrayOf(8, 5)
                ),
                startPos = 3,
                k = 2
            ),
            output = 0
        )
    )
    
    override fun solve(testCaseInput: MaximumFruitsHarvestedAfterAtMostKStepsParams): Int {
        return maxTotalFruits(testCaseInput.fruits, testCaseInput.startPos, testCaseInput.k)
    }

    private fun maxTotalFruits(fruits: Array<IntArray>, startPos: Int, k: Int): Int {
        var left = 0
        var sum = 0
        var max = 0

        fun minSteps(left: Int, right: Int, start: Int): Int {
            val goLeft = abs(start - left) + (right - left)
            val goRight = abs(start - right) + (right - left)
            return minOf(goLeft, goRight)
        }

        for (right in fruits.indices) {
            sum += fruits[right][1]

            while (left <= right && minSteps(fruits[left][0], fruits[right][0], startPos) > k) {
                sum -= fruits[left][1]
                left++
            }

            max = maxOf(max, sum)
        }

        return max
    }

    data class MaximumFruitsHarvestedAfterAtMostKStepsParams(
        val fruits: Array<IntArray>,
        val startPos: Int,
        val k: Int
    ) {
        override fun toString(): String {
            return """
                
                fruits: ${stringFromArray(fruits)}
                startPos: $startPos
                k: $k
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MaximumFruitsHarvestedAfterAtMostKStepsParams

            if (startPos != other.startPos) return false
            if (k != other.k) return false
            if (!fruits.contentDeepEquals(other.fruits)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = startPos
            result = 31 * result + k
            result = 31 * result + fruits.contentDeepHashCode()
            return result
        }
    }
}
