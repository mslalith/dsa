package dev.mslalith.array.problems

import dev.mslalith.array.problems.DesignSpreadsheet.DesignSpreadsheetParams
import dev.mslalith.array.problems.DesignSpreadsheet.DesignSpreadsheetType.GetValue
import dev.mslalith.array.problems.DesignSpreadsheet.DesignSpreadsheetType.ResetCell
import dev.mslalith.array.problems.DesignSpreadsheet.DesignSpreadsheetType.SetValue
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class DesignSpreadsheet : TestCaseProblem<DesignSpreadsheetParams, List<Int?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DesignSpreadsheet().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<DesignSpreadsheetParams, List<Int?>>> = arrayOf(
        TestCase(
            input = DesignSpreadsheetParams(
                rows = 3,
                queries = listOf(
                    GetValue(formula = "=5+7"),
                    SetValue(cell = "A1", value = 10),
                    GetValue(formula = "=A1+6"),
                    SetValue(cell = "B2", value = 15),
                    GetValue(formula = "=A1+B2"),
                    ResetCell(cell = "A1"),
                    GetValue(formula = "=A1+B2")
                )
            ),
            output = listOf(12, null, 16, null, 25, null, 15)
        ),
        TestCase(
            input = DesignSpreadsheetParams(
                rows = 458,
                queries = listOf(
                    GetValue(formula = "=O126+10272")
                )
            ),
            output = listOf(10272)
        ),
        TestCase(
            input = DesignSpreadsheetParams(
                rows = 24,
                queries = listOf(
                    SetValue(cell = "B24", value = 66688),
                    ResetCell(cell = "O15")
                )
            ),
            output = listOf(null, null)
        )
    )

    override fun solve(testCaseInput: DesignSpreadsheetParams): List<Int?> {
        val spreadsheet = Spreadsheet(rows = testCaseInput.rows)
        return testCaseInput.queries.map {
            when (it) {
                is GetValue -> spreadsheet.getValue(formula = it.formula)
                is ResetCell -> {
                    spreadsheet.resetCell(cell = it.cell)
                    null
                }

                is SetValue -> {
                    spreadsheet.setCell(cell = it.cell, value = it.value)
                    null
                }
            }
        }
    }

    private class Spreadsheet(rows: Int) {

        private val sheet = Array(rows) { IntArray(26) }

        fun setCell(cell: String, value: Int) {
            val (row, col) = cell.extractRowCol()
            sheet[row][col] = value
        }

        fun resetCell(cell: String) {
            setCell(cell = cell, value = 0)
        }

        fun getValue(formula: String): Int {
            val (one, two) = formula.drop(1).split("+")

            val x = if (one[0] in 'A'..'Z') {
                val (row, col) = one.extractRowCol()
                sheet[row][col]
            } else {
                one.toInt()
            }

            val y = if (two[0] in 'A'..'Z') {
                val (row, col) = two.extractRowCol()
                sheet[row][col]
            } else {
                two.toInt()
            }

            return x + y
        }

        private fun String.extractRowCol(): Pair<Int, Int> {
            val row = this.drop(1).toInt() - 1
            val col = this[0] - 'A'
            return row to col
        }
    }

    data class DesignSpreadsheetParams(
        val rows: Int,
        val queries: List<DesignSpreadsheetType>
    )

    sealed interface DesignSpreadsheetType {
        data class GetValue(val formula: String) : DesignSpreadsheetType
        data class SetValue(val cell: String, val value: Int) : DesignSpreadsheetType
        data class ResetCell(val cell: String) : DesignSpreadsheetType
    }
}
