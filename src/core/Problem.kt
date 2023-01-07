package src.core

import src.linked_list.ListNode
import src.utils.areListNodesEqual
import src.utils.stringFromArray
import src.utils.displayStringFromListNode

abstract class Problem<I, O> {

    protected abstract fun getTestCases(): Array<TestCase<I, O>>
    protected abstract fun solve(testCaseInput: I): O

    open fun skipIO(): Boolean = false

    fun runSilent(): TestResult {
        val testCases = getTestCases()
        val passed = testCases.count { runSingle(testCase = it, silent = true) }
        val failed = testCases.size - passed

        return TestResult(
            allTests = testCases.size,
            passed = passed,
            failed = failed
        )
    }

    fun run() = getTestCases().forEach { runSingle(testCase = it, silent = false) }

    private fun runSingle(testCase: TestCase<I, O>, silent: Boolean): Boolean {
        val output = solve(testCase.input)
        val silentInternal = silent || skipIO()

        if (!silentInternal) {
            val inputString = stringFromType(testCase.input)
            val outputString = stringFromType(output)

            println("Input: $inputString")
            println("Output: $outputString")
        }

        val isTestPassed = isTestPassed<O>(testCase.output, output)

        if (!silentInternal && !isTestPassed) println("Expected: " + stringFromType(testCase.output))
        if (!silent) println(if (isTestPassed) "✅ Passed" else "❌ Failed")
        if (!silent) println()

        return isTestPassed
    }

    private fun stringFromType(input: Any?): String = when (input) {
        is Array<*> -> stringFromArray(input)
        is IntArray -> stringFromArray(input)
        is ListNode -> displayStringFromListNode(input)
        null -> "null"
        else -> input.toString()
    }

    private fun <T> isTestPassed(actual: T?, expected: T?): Boolean {
        if (actual == null && expected == null) return true
        if (actual == null || expected == null) return false
        if (actual is IntArray && expected is IntArray) return actual.contentEquals(expected)
        if (actual is ListNode && expected is ListNode) return areListNodesEqual(actual, expected)
        return actual == expected
    }
}
