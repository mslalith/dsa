package dev.mslalith.array.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray

class FloodFill : TestCaseProblem<FloodFillParams, Array<IntArray>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FloodFill().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FloodFillParams, Array<IntArray>>> = arrayOf(
        TestCase(
            input = FloodFillParams(
                image = arrayOf(
                    intArrayOf(1, 1, 1),
                    intArrayOf(1, 1, 0),
                    intArrayOf(1, 0, 1)
                ),
                sr = 1,
                sc = 1,
                color = 2
            ),
            output = arrayOf(
                intArrayOf(2, 2, 2),
                intArrayOf(2, 2, 0),
                intArrayOf(2, 0, 1)
            )
        ),
        TestCase(
            input = FloodFillParams(
                image = arrayOf(
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0)
                ),
                sr = 0,
                sc = 0,
                color = 0
            ),
            output = arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0)
            )
        ),
        TestCase(
            input = FloodFillParams(
                image = arrayOf(
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0)
                ),
                sr = 1,
                sc = 0,
                color = 2
            ),
            output = arrayOf(
                intArrayOf(2, 2, 2),
                intArrayOf(2, 2, 2)
            )
        )
    )

    override fun solve(testCaseInput: FloodFillParams): Array<IntArray> {
        return floodFill(testCaseInput.image, testCaseInput.sr, testCaseInput.sc, testCaseInput.color)
    }

    private fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        if (image.isEmpty()) return image
        val m = image.size
        val n = image.first().size

        fun floodFill(sr: Int, sc: Int, from: Int, to: Int) {
            if (sr < 0 || sr >= m) return
            if (sc < 0 || sc >= n) return
            if (image[sr][sc] != from || image[sr][sc] == to) return

            image[sr][sc] = to
            floodFill(sr + 1, sc, from, to)
            floodFill(sr - 1, sc, from, to)
            floodFill(sr, sc + 1, from, to)
            floodFill(sr, sc - 1, from, to)
        }

        floodFill(sr, sc, image[sr][sc], color)
        return image
    }
}

data class FloodFillParams(
    val image: Array<IntArray>,
    val sr: Int,
    val sc: Int,
    val color: Int
) {
    override fun toString(): String {
        return """

            image: ${stringFromArray(image)}
            sr: $sr
            sc: $sc
            color: $color
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FloodFillParams

        if (!image.contentDeepEquals(other.image)) return false
        if (sr != other.sr) return false
        if (sc != other.sc) return false
        if (color != other.color) return false

        return true
    }

    override fun hashCode(): Int {
        var result = image.contentDeepHashCode()
        result = 31 * result + sr
        result = 31 * result + sc
        result = 31 * result + color
        return result
    }
}
