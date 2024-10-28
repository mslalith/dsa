package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class LetterCombinationsOfPhoneNumber : TestCaseProblem<String, List<String>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LetterCombinationsOfPhoneNumber().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<String, List<String>>> = arrayOf(
        TestCase(
            input = "23",
            output = listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
        ),
        TestCase(
            input = "",
            output = emptyList()
        ),
        TestCase(
            input = "2",
            output = listOf("a", "b", "c")
        )
    )

    override fun solve(testCaseInput: String): List<String> {
        return letterCombinations(testCaseInput)
    }

    private fun letterCombinations(digits: String): List<String> {
        val mapping = hashMapOf(
            '2' to charArrayOf('a', 'b', 'c'),
            '3' to charArrayOf('d', 'e', 'f'),
            '4' to charArrayOf('g', 'h', 'i'),
            '5' to charArrayOf('j', 'k', 'l'),
            '6' to charArrayOf('m', 'n', 'o'),
            '7' to charArrayOf('p', 'q', 'r', 's'),
            '8' to charArrayOf('t', 'u', 'v'),
            '9' to charArrayOf('w', 'x', 'y', 'z'),
        )
        return letterCombinations(digits, mapping)
    }

    private fun letterCombinations(digits: String, mapping: HashMap<Char, CharArray>): List<String> {
        if (digits.isEmpty()) return emptyList()
        if (digits.length == 1) return mapping.getValue(digits[0]).map { it.toString() }

        val characters = mapping.getValue(digits[0])
        val list = letterCombinations(digits.drop(1), mapping)
        return buildList {
            characters.forEach { ch ->
                list.forEach { add(ch + it) }
            }
        }
    }
}
