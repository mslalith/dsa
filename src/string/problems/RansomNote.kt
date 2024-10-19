package src.string.problems

import src.core.Problem
import src.core.TestCase

class RansomNote : Problem<Pair<String, String>, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RansomNote().run()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<String, String>, Boolean>> = arrayOf(
        TestCase(
            input = "a" to "b",
            output = false
        ),
        TestCase(
            input = "aa" to "ab",
            output = false
        ),
        TestCase(
            input = "aa" to "aab",
            output = true
        )
    )
    
    override fun solve(testCaseInput: Pair<String, String>): Boolean {
        return canConstruct(testCaseInput.first, testCaseInput.second)
    }

    private fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val map = hashMapOf<Char, Int>()
        magazine.forEach { map[it] = map.getOrDefault(it, 0) + 1 }

        ransomNote.forEach {
            val count = map[it] ?: return false
            if (count <= 0) return false
            map[it] = count - 1
        }

        return true
    }
}
