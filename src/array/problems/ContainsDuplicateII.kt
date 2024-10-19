package src.array.problems

import src.core.Problem
import src.core.TestCase

class ContainsDuplicateII : Problem<Pair<IntArray, Int>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ContainsDuplicateII().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<IntArray, Int>, Boolean>> = arrayOf(
        TestCase(
            input = intArrayOf(1, 2, 3, 1) to 3,
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 0, 1, 1) to 1,
            output = true
        ),
        TestCase(
            input = intArrayOf(1, 2, 3, 1, 2, 3) to 2,
            output = false
        )
    )

    override fun solve(testCaseInput: Pair<IntArray, Int>): Boolean {
        return containsNearbyDuplicate(testCaseInput.first, testCaseInput.second)
    }

    private fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = hashMapOf<Int, Int>()

        for (i in nums.indices) {
            val num = nums[i]
            if (map.contains(num)) {
                val index = map.getValue(num)
                if (i - index <= k) return true
                map[num] = i
            } else {
                map[num] = i
            }
        }

        return false
    }
}
