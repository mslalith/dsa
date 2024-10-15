package src.core

import src.linkedlist.ListNode
import src.utils.areListNodesEqual
import src.utils.displayStringFromListNode
import src.utils.stringFromArray
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

abstract class Problem<I, O> {

    protected abstract fun getTestCases(): Array<TestCase<I, O>>
    protected abstract fun solve(testCaseInput: I): O

    open val trackTime: Boolean get() = true
    open val skipIO: Boolean get() = false
    open fun isTestPassed(actual: O, expected: O): Boolean = isTestPassedInternal(actual, expected)

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

    @OptIn(ExperimentalTime::class)
    private fun runSingle(testCase: TestCase<I, O>, silent: Boolean): Boolean {
        val (output, timeTaken) = measureTimedValue { solve(testCase.input) }
        val silentInternal = silent || skipIO

        if (!silentInternal) {
            val inputString = stringFromType(testCase.input)
            val outputString = stringFromType(output)

            println("Input: $inputString")
            println("Output: $outputString")
        }

        val isTestPassed = isTestPassed(testCase.output, output)

        if (!silentInternal && !isTestPassed) println("Expected: " + stringFromType(testCase.output))

        val displayResultStatus = buildString {
            if (!silent) append(if (isTestPassed) "✅ Passed" else "❌ Failed")
            if (trackTime) {
                append(" (")
                append(timeTaken)
                append(")")
            }
        }
        if (!silent) println(displayResultStatus)
        if (!silent) println()

        return isTestPassed
    }

    private fun stringFromType(input: Any?): String = when (input) {
        is Array<*> -> displayStringFromArray(input)
        is IntArray -> stringFromArray(input)
        is DoubleArray -> stringFromArray(input)
        is CharArray -> stringFromArray(input)
        is ListNode -> displayStringFromListNode(input)
        is Pair<*, *> -> displayStringFromPair(input)
        null -> "null"
        else -> input.toString()
    }

    @Suppress("UNCHECKED_CAST")
    private fun displayStringFromArray(array: Array<*>): String = when {
        array.isEmpty() -> stringFromArray(array)
        else -> when (array.first()) {
            is IntArray -> buildDisplayStringFromIntArray(array as Array<IntArray>)
            is CharArray -> buildDisplayStringFromCharArray(array as Array<CharArray>)
            else -> stringFromArray(array)
        }
    }

    private fun displayStringFromPair(pair: Pair<*, *>): String = buildString {
        append("(")
        append(stringFromType(input = pair.first))
        append(", ")
        append(stringFromType(input = pair.second))
        append(")")
    }

    private fun <T> isTestPassedInternal(actual: T?, expected: T?): Boolean {
        if (actual == null && expected == null) return true
        if (actual == null || expected == null) return false
        if (actual is IntArray && expected is IntArray) return actual.contentEquals(expected)
        if (actual is DoubleArray && expected is DoubleArray) return actual.contentEquals(expected)
        if (actual is ListNode && expected is ListNode) return areListNodesEqual(actual, expected)
        return actual == expected
    }
}

private fun buildDisplayStringFromIntArray(array: Array<IntArray>): String = buildDisplayStringFromTypedArray(array = array, map = ::stringFromArray)
private fun buildDisplayStringFromCharArray(array: Array<CharArray>): String = buildDisplayStringFromTypedArray(array = array, map = ::stringFromArray)

private fun <T> buildDisplayStringFromTypedArray(
    array: Array<T>,
    map: (T) -> String
) = buildString {
    append("[")
    append("\n\t")
    array.forEach {
        append(map(it))
        append(",\n\t")
    }
    // remove last appended chars
    deleteAt(length - 1)
    deleteAt(length - 1)
    deleteAt(length - 1)
    append("\n")
    append("]")
}
