package dev.mslalith.backtracking.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class ConstructLexicographicallyLargestValidSequence : TestCaseProblem<Int, IntArray>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructLexicographicallyLargestValidSequence().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Int, IntArray>> = arrayOf(
        TestCase(
            input = 3,
            output = intArrayOf(3, 1, 2, 3, 2)
        ),
        TestCase(
            input = 5,
            output = intArrayOf(5, 3, 1, 4, 3, 5, 2, 4, 2)
        )
    )

    override fun solve(testCaseInput: Int): IntArray {
        return constructDistancedSequence(testCaseInput)
    }

    private fun constructDistancedSequence(n: Int): IntArray {
        val size = (n * 2) - 1
        val result = IntArray(size)
        val used = hashSetOf<Int>()

        fun assignNum(pos: Int): Boolean {
            var idx = pos
            while (idx < size && result[idx] != 0) idx++

            if (idx == size) return true

            for (num in n downTo 0) {
                if (num in used) continue

                val other = idx + num
                if (num == 1) {
                    result[idx] = num
                    used += num

                    if (assignNum(idx + 1)) return true

                    result[idx] = 0
                    used -= num
                } else if (other < size && result[other] == 0) {
                    result[idx] = num
                    result[other] = num
                    used += num

                    if (assignNum(idx + 1)) return true

                    result[idx] = 0
                    result[other] = 0
                    used -= num
                }
            }

            return false
        }

        assignNum(0)

        return result
    }

    private fun constructDistancedSequenceOtherApproach(n: Int): IntArray {
        val size = (n * 2) - 1
        val result = IntArray(size)

        fun assignNum(num: Int): Boolean {
            if (num == 1) {
                for (i in result.indices) {
                    if (result[i] == 0) {
                        result[i] = num
                        return true
                    }
                }
                return false
            }

            for (i in result.indices) {
                val other = i + num
                if (other >= size) return false
                if (result[i] != 0 || result[other] != 0) continue

                result[i] = num
                result[other] = num
                val isSuccess = assignNum(num - 1)
                if (isSuccess) return true

                result[i] = 0
                result[other] = 0
            }

            return false
        }

        assignNum(n)

        return result
    }
}
