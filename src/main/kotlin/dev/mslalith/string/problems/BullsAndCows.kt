package dev.mslalith.string.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase

class BullsAndCows : TestCaseProblem<BullsAndCowsParams, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = BullsAndCows().runAll()
    }

    override fun getTestCases(): Array<TestCase<BullsAndCowsParams, String>> = arrayOf(
        TestCase(
            input = BullsAndCowsParams(
                secret = "1807",
                guess = "7810"
            ),
            output = "1A3B"
        ),
        TestCase(
            input = BullsAndCowsParams(
                secret = "1123",
                guess = "0111"
            ),
            output = "1A1B"
        ),
        TestCase(
            input = BullsAndCowsParams(
                secret = "1122",
                guess = "1222"
            ),
            output = "3A0B"
        ),
        TestCase(
            input = BullsAndCowsParams(
                secret = "11",
                guess = "01"
            ),
            output = "1A0B"
        ),
        TestCase(
            input = BullsAndCowsParams(
                secret = "1122",
                guess = "0001"
            ),
            output = "0A1B"
        )
    )

    override fun solve(testCaseInput: BullsAndCowsParams): String {
        return getHint(testCaseInput.secret, testCaseInput.guess)
    }

    private fun getHint(secret: String, guess: String): String {
        val secretMapping = Array(10) { 0 }

        var bulls = 0
        var cows = 0

        secret.forEachIndexed { i, ch ->
            if (guess[i] == ch) bulls++
            else secretMapping[Character.getNumericValue(ch)]++
        }

        guess.forEachIndexed { i, ch ->
            if (secret[i] != ch && secretMapping[Character.getNumericValue(ch)] > 0) {
                cows++
                secretMapping[Character.getNumericValue(ch)]--
            }
        }

        return "${bulls}A${cows}B"
    }
}

data class BullsAndCowsParams(
    val secret: String,
    val guess: String
) {
    override fun toString(): String {
        return """
            
            secret: $secret
            guess: $guess
        """.trimIndent()
    }
}