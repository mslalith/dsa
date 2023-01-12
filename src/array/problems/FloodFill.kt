package src.array.problems

import src.core.Problem
import src.core.TestCase
import src.utils.stringFromArray

class FloodFill : Problem<FloodFillParams, FloodFillOutput>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FloodFill().run()
    }

    override fun getTestCases(): Array<TestCase<FloodFillParams, FloodFillOutput>> = arrayOf(
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
            output = FloodFillOutput(
                output = arrayOf(
                    intArrayOf(2, 2, 2),
                    intArrayOf(2, 2, 0),
                    intArrayOf(2, 0, 1)
                )
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
            output = FloodFillOutput(
                output = arrayOf(
                    intArrayOf(0, 0, 0),
                    intArrayOf(0, 0, 0)
                )
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
            output = FloodFillOutput(
                output = arrayOf(
                    intArrayOf(2, 2, 2),
                    intArrayOf(2, 2, 2)
                )
            )
        )
    )

    override fun solve(testCaseInput: FloodFillParams): FloodFillOutput {
        return FloodFillOutput(
            output = floodFill(testCaseInput.image, testCaseInput.sr, testCaseInput.sc, testCaseInput.color)
        )
    }

    private fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        if (image.isEmpty()) return image
        val m = image.size
        val n = image.first().size
        floodFill(image, m, n, sr, sc, image[sr][sc], color)
        return image
    }

    private fun floodFill(image: Array<IntArray>, m: Int, n: Int, sr: Int, sc: Int, from: Int, to: Int) {
        if (sr < 0 || sr >= m) return
        if (sc < 0 || sc >= n) return
        if (image[sr][sc] != from || image[sr][sc] == to) return

        image[sr][sc] = to
        floodFill(image, m, n, sr + 1, sc, from, to)
        floodFill(image, m, n, sr - 1, sc, from, to)
        floodFill(image, m, n, sr, sc + 1, from, to)
        floodFill(image, m, n, sr, sc - 1, from, to)
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

data class FloodFillOutput(
    val output: Array<IntArray>
) {
    override fun toString(): String {
        return stringFromArray(output)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FloodFillOutput

        if (!output.contentDeepEquals(other.output)) return false

        return true
    }

    override fun hashCode(): Int {
        return output.contentDeepHashCode()
    }
}