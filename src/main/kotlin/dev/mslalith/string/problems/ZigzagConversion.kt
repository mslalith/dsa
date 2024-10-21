package dev.mslalith.string.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class ZigzagConversion : Problem<ZigzagConversionParams, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ZigzagConversion().run()
    }

    override fun getTestCases(): Array<TestCase<ZigzagConversionParams, String>> = arrayOf(
        TestCase(
            input = ZigzagConversionParams(s = "PAYPALISHIRING", numRows = 3),
            output = "PAHNAPLSIIGYIR"
        ),
        TestCase(
            input = ZigzagConversionParams(s = "PAYPALISHIRING", numRows = 4),
            output = "PINALSIGYAHRPI"
        ),
        TestCase(
            input = ZigzagConversionParams(s = "AB", numRows = 1),
            output = "AB"
        )
    )

    override fun solve(testCaseInput: ZigzagConversionParams): String {
        return convert(testCaseInput.s, testCaseInput.numRows)
    }

    private fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        val rows = Array(numRows) { StringBuilder() }
        var toAdd = true
        var i = 0

        s.forEach { ch ->
            if (i == 0) toAdd = true else if (i == numRows - 1) toAdd = false
            rows[i].append(ch)
            if (toAdd) i++ else i--
        }

        return rows.reduce { acc, sb -> acc.append(sb) }.toString()
    }
}

data class ZigzagConversionParams(
    val s: String,
    val numRows: Int
) {
    override fun toString(): String {
        return """
            
            s: $s
            numRows: $numRows
        """.trimIndent()
    }
}