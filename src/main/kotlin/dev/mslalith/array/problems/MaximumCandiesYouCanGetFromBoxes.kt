package dev.mslalith.array.problems

import dev.mslalith.array.problems.MaximumCandiesYouCanGetFromBoxes.MaximumCandiesYouCanGetFromBoxesParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import dev.mslalith.utils.stringFromArray

class MaximumCandiesYouCanGetFromBoxes : TestCaseProblem<MaximumCandiesYouCanGetFromBoxesParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumCandiesYouCanGetFromBoxes().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MaximumCandiesYouCanGetFromBoxesParams, Int>> = arrayOf(
        TestCase(
            input = MaximumCandiesYouCanGetFromBoxesParams(
                status = intArrayOf(1, 0, 1, 0),
                candies = intArrayOf(7, 5, 4, 100),
                keys = arrayOf(
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf(1),
                    intArrayOf()
                ),
                containedBoxes = arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(3),
                    intArrayOf(),
                    intArrayOf()
                ),
                initialBoxes = intArrayOf(0)
            ),
            output = 16
        ),
        TestCase(
            input = MaximumCandiesYouCanGetFromBoxesParams(
                status = intArrayOf(1, 0, 0, 0, 0, 0),
                candies = intArrayOf(1, 1, 1, 1, 1, 1),
                keys = arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf()
                ),
                containedBoxes = arrayOf(
                    intArrayOf(1, 2, 3, 4, 5),
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf()
                ),
                initialBoxes = intArrayOf(0)
            ),
            output = 6
        ),
        TestCase(
            input = MaximumCandiesYouCanGetFromBoxesParams(
                status = intArrayOf(1, 1, 1),
                candies = intArrayOf(100, 1, 100),
                keys = arrayOf(
                    intArrayOf(),
                    intArrayOf(0, 2),
                    intArrayOf()
                ),
                containedBoxes = arrayOf(
                    intArrayOf(),
                    intArrayOf(),
                    intArrayOf()
                ),
                initialBoxes = intArrayOf(1)
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: MaximumCandiesYouCanGetFromBoxesParams): Int {
        return maxCandies(testCaseInput.status.createClone(), testCaseInput.candies, testCaseInput.keys, testCaseInput.containedBoxes, testCaseInput.initialBoxes)
    }

    private fun maxCandies(
        status: IntArray,
        candies: IntArray,
        keys: Array<IntArray>,
        containedBoxes: Array<IntArray>,
        initialBoxes: IntArray
    ): Int {
        val dq = ArrayDeque<Int>()
        for (box in initialBoxes) {
            for (key in keys[box]) status[key] = 1
            if (status[box] == 1) dq.addFirst(box) else dq.addLast(box)
        }

        var totalCandies = 0

        while (dq.isNotEmpty()) {
            val box = dq.removeFirst()
            if (status[box] == 0) break

            totalCandies += candies[box]
            for (key in keys[box]) status[key] = 1

            for (containedBox in containedBoxes[box]) {
                if (status[containedBox] == 1) dq.addFirst(containedBox) else dq.addLast(containedBox)
            }
        }

        return totalCandies
    }

    data class MaximumCandiesYouCanGetFromBoxesParams(
        val status: IntArray,
        val candies: IntArray,
        val keys: Array<IntArray>,
        val containedBoxes: Array<IntArray>,
        val initialBoxes: IntArray
    ) {
        override fun toString(): String {
            return """
                
                status: ${stringFromArray(status)}
                candies: ${stringFromArray(candies)}
                keys: ${stringFromArray(keys)}
                containedBoxes: ${stringFromArray(containedBoxes)}
                initialBoxes: ${stringFromArray(initialBoxes)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MaximumCandiesYouCanGetFromBoxesParams

            if (!status.contentEquals(other.status)) return false
            if (!candies.contentEquals(other.candies)) return false
            if (!keys.contentDeepEquals(other.keys)) return false
            if (!containedBoxes.contentDeepEquals(other.containedBoxes)) return false
            if (!initialBoxes.contentEquals(other.initialBoxes)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = status.contentHashCode()
            result = 31 * result + candies.contentHashCode()
            result = 31 * result + keys.contentDeepHashCode()
            result = 31 * result + containedBoxes.contentDeepHashCode()
            result = 31 * result + initialBoxes.contentHashCode()
            return result
        }
    }
}