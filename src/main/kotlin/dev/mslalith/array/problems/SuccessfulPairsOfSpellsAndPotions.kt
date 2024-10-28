package dev.mslalith.array.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.utils.stringFromArray

class SuccessfulPairsOfSpellsAndPotions : TestCaseProblem<SuccessfulPairsOfSpellsAndPotionsParams, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SuccessfulPairsOfSpellsAndPotions().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<SuccessfulPairsOfSpellsAndPotionsParams, IntArray>> = arrayOf(
        TestCase(
            input = SuccessfulPairsOfSpellsAndPotionsParams(
                spells = intArrayOf(5, 1, 3),
                potions = intArrayOf(1, 2, 3, 4, 5),
                success = 7
            ),
            output = intArrayOf(4, 0, 3)
        ),
        TestCase(
            input = SuccessfulPairsOfSpellsAndPotionsParams(
                spells = intArrayOf(3, 1, 2),
                potions = intArrayOf(8, 5, 8),
                success = 16
            ),
            output = intArrayOf(2, 0, 2)
        )
    )

    override fun solve(testCaseInput: SuccessfulPairsOfSpellsAndPotionsParams): IntArray {
        return successfulPairs(testCaseInput.spells, testCaseInput.potions, testCaseInput.success)
    }

    private fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        potions.sort()
        return spells.map { spell ->
            val index = binarySearch(spell, potions, success)
            if (index == -1) 0 else potions.size - index
        }.toIntArray()
    }

    private fun binarySearch(spell: Int, potions: IntArray, success: Long): Int {
        var left = 0
        var right = potions.lastIndex
        if ((spell.toLong() * potions[right]) < success) return -1

        while (left < right) {
            val mid = left + (right - left) / 2
            val x = spell.toLong() * potions[mid]
            when {
                x >= success -> right = mid
                x < success -> left = mid + 1
                else -> Unit
            }
        }

        return right
    }

    private fun successfulPairsNaive(spells: IntArray, potions: IntArray, success: Long): IntArray {
        return spells.map { spell ->
            potions.count { potion ->
                (spell * potion.toLong()) >= success
            }
        }.toIntArray()
    }
}

data class SuccessfulPairsOfSpellsAndPotionsParams(
    val spells: IntArray,
    val potions: IntArray,
    val success: Long
) {
    override fun toString(): String {
        return """
            
            spells: ${stringFromArray(spells)}
            potions: ${stringFromArray(potions)}
            success: $success
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SuccessfulPairsOfSpellsAndPotionsParams

        if (!spells.contentEquals(other.spells)) return false
        if (!potions.contentEquals(other.potions)) return false
        if (success != other.success) return false

        return true
    }

    override fun hashCode(): Int {
        var result = spells.contentHashCode()
        result = 31 * result + potions.contentHashCode()
        result = 31 * result + success.hashCode()
        return result
    }
}
