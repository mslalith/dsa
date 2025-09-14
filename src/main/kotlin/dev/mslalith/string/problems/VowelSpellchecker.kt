package dev.mslalith.string.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class VowelSpellchecker : TestCaseProblem<Pair<Array<String>, Array<String>>, Array<String>>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = VowelSpellchecker().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Array<String>, Array<String>>, Array<String>>> = arrayOf(
        TestCase(
            input = arrayOf(
                "KiTe","kite","hare","Hare"
            ) to arrayOf(
                "kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"
            ),
            output = arrayOf(
                "kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"
            )
        ),
        TestCase(
            input = arrayOf(
                "yellow"
            ) to arrayOf(
                "YellOw"
            ),
            output = arrayOf(
                "yellow"
            )
        )
    )
    
    override fun solve(testCaseInput: Pair<Array<String>, Array<String>>): Array<String> {
        return spellchecker(testCaseInput.first, testCaseInput.second)
    }

    private fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
        val normalMap = mutableMapOf<String, Int>()
        val lowercaseMap = mutableMapOf<String, Int>()
        val noVowelsMap = mutableMapOf<String, Int>()
        val result = Array(queries.size) { "" }

        fun String.maskVowels(): String = buildString {
            for (ch in this@maskVowels) {
                when (ch.lowercaseChar()) {
                    'a', 'e', 'i', 'o', 'u' -> append('*')
                    else -> append(ch)
                }
            }
        }

        for ((i, word) in wordlist.withIndex()) {
            normalMap[word] = i
            lowercaseMap.putIfAbsent(word.lowercase(),  i)
            noVowelsMap.putIfAbsent(word.lowercase().maskVowels(), i)
        }

        for ((i, query) in queries.withIndex()) {
            val normalIdx = normalMap[query]
            if (normalIdx != null) {
                result[i] = query
                continue
            }

            val lowercaseIdx = lowercaseMap[query.lowercase()]
            if (lowercaseIdx != null) {
                result[i] = wordlist[lowercaseIdx]
                continue
            }

            val withoutVowelsIdx = noVowelsMap[query.lowercase().maskVowels()]
            if (withoutVowelsIdx != null) result[i] = wordlist[withoutVowelsIdx]
        }

        return result
    }
}
