package dev.mslalith.core.problem

import dev.mslalith.core.TestCase
import dev.mslalith.core.TestResult
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.areListNodesEqual
import dev.mslalith.utils.displayStringFromListNode
import dev.mslalith.utils.stringFromArray
import kotlin.time.measureTimedValue

abstract class TestCaseProblem<I, O> : Problem {

    protected abstract fun getTestCases(): Array<TestCase<I, O>>
    abstract fun solve(testCaseInput: I): O

    open val skipIO: Boolean get() = false

    open fun isTestPassed(actual: O, expected: O): Boolean = isTestPassedInternal(actual, expected)
    open fun displayInput(input: I): String = stringFromType(input)
    open fun displayOutput(output: O): String = stringFromType(output)
    open fun displayExpected(expected: O): String = stringFromType(expected)

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

    override fun runForConsole() {
        val testCases = getTestCases()
        testCases.forEach { runSingle(testCase = it, silent = false) }
    }

    override fun run(): Boolean {
        val testCases = getTestCases()
        return testCases.count { runSingle(testCase = it, silent = true) } == testCases.size
    }

    private fun runSingle(testCase: TestCase<I, O>, silent: Boolean): Boolean {
        val (output, timeTaken) = measureTimedValue { solve(testCase.input) }
        val silentInternal = silent || skipIO

        if (!silentInternal) {
            val inputString = displayInput(testCase.input)
            val outputString = displayOutput(output)

            println("Input: $inputString")
            println("Output: $outputString")
        }

        val isTestPassed = isTestPassed(testCase.output, output)

        if (!silentInternal && !isTestPassed) println("Expected: " + displayExpected(testCase.output))

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

    protected fun stringFromType(input: Any?, pretty: Boolean = true): String = when (input) {
        is List<*> -> displayStringFromList(input, pretty)
        is Array<*> -> displayStringFromArray(input, pretty)
        is IntArray -> stringFromArray(input)
        is DoubleArray -> stringFromArray(input)
        is CharArray -> stringFromArray(input)
        is ListNode -> displayStringFromListNode(input)
        is Pair<*, *> -> displayStringFromPair(input)
        null -> "null"
        else -> input.toString()
    }

    @Suppress("UNCHECKED_CAST")
    private fun displayStringFromArray(array: Array<*>, pretty: Boolean = true): String = when {
        array.isEmpty() -> stringFromArray(array)
        else -> when (array.first()) {
            is IntArray -> buildDisplayStringFromIntArray(array as Array<IntArray>, pretty)
            is CharArray -> buildDisplayStringFromCharArray(array as Array<CharArray>, pretty)
            else -> buildDisplayStringFromArray(array, pretty)
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
        if (actual is Array<*> && expected is Array<*>) return actual.matches(expected)
        if (actual is List<*> && expected is List<*>) return actual.matches(expected)
        if (actual is IntArray && expected is IntArray) return actual.contentEquals(expected)
        if (actual is DoubleArray && expected is DoubleArray) return actual.contentEquals(expected)
        if (actual is ListNode && expected is ListNode) return areListNodesEqual(actual, expected)
        return actual == expected
    }

    private fun isTestPassedGiven(actual: Any?, expected: Any?): Boolean {
        if (actual == null && expected == null) return true
        if (actual == null || expected == null) return false
        if (actual is List<*> && expected is List<*>) return actual.matches(expected)
        if (actual is IntArray && expected is IntArray) return actual.contentEquals(expected)
        if (actual is CharArray && expected is CharArray) return actual.contentEquals(expected)
        if (actual is DoubleArray && expected is DoubleArray) return actual.contentEquals(expected)
        if (actual is ListNode && expected is ListNode) return areListNodesEqual(actual, expected)
        return actual == expected
    }

    private fun Array<*>.matches(other: Array<*>): Boolean {
        if (size != other.size) return false
        return zip(other).all { isTestPassedGiven(it.first, it.second) }
    }

    private fun List<*>.matches(other: List<*>): Boolean {
        if (size != other.size) return false
        return zip(other).all { isTestPassedGiven(it.first, it.second) }
    }

    protected fun displayStringFromList(list: List<*>, pretty: Boolean): String = buildDisplayStringFromIterable(iterable = list, pretty = pretty, map = { it.toString() })
}

private fun buildDisplayStringFromArray(array: Array<*>, pretty: Boolean): String = buildDisplayStringFromIterable(iterable = array.asIterable(), pretty = pretty, map = { it.toString() })
private fun buildDisplayStringFromIntArray(array: Array<IntArray>, pretty: Boolean): String = buildDisplayStringFromIterable(iterable = array.asIterable(), pretty = pretty, map = ::stringFromArray)
private fun buildDisplayStringFromCharArray(array: Array<CharArray>, pretty: Boolean): String = buildDisplayStringFromIterable(iterable = array.asIterable(), pretty = pretty, map = ::stringFromArray)

private fun <T> buildDisplayStringFromIterable(
    iterable: Iterable<T>,
    pretty: Boolean,
    map: (T) -> String
) = buildString {
    val isEmpty = !iterable.iterator().hasNext()
    if (isEmpty) {
        append("[]")
        return@buildString
    }

    append("[")
    append("\n\t")
    iterable.forEach {
        append(map(it))
        if (pretty) append(",\n\t") else append(", ")
    }

    // remove last appended chars
    deleteAt(length - 1)
    deleteAt(length - 1)
    if (pretty) deleteAt(length - 1)
    append("\n")
    append("]")
}
