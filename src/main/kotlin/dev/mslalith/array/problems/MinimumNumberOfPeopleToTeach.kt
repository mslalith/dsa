package dev.mslalith.array.problems

import dev.mslalith.array.problems.MinimumNumberOfPeopleToTeach.MinimumNumberOfPeopleToTeachParams
import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray

class MinimumNumberOfPeopleToTeach : TestCaseProblem<MinimumNumberOfPeopleToTeachParams, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MinimumNumberOfPeopleToTeach().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<MinimumNumberOfPeopleToTeachParams, Int>> = arrayOf(
        TestCase(
            input = MinimumNumberOfPeopleToTeachParams(
                n = 2,
                languages = arrayOf(
                    intArrayOf(1),
                    intArrayOf(2),
                    intArrayOf(1, 2)
                ),
                friendships = arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(2, 3)
                )
            ),
            output = 1
        ),
        TestCase(
            input = MinimumNumberOfPeopleToTeachParams(
                n = 3,
                languages = arrayOf(
                    intArrayOf(2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 2),
                    intArrayOf(3)
                ),
                friendships = arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(1, 2),
                    intArrayOf(3, 4),
                    intArrayOf(2, 3)
                )
            ),
            output = 2
        )
    )

    override fun solve(testCaseInput: MinimumNumberOfPeopleToTeachParams): Int {
        return minimumTeachings(testCaseInput.n, testCaseInput.languages, testCaseInput.friendships)
    }

    private fun minimumTeachings(n: Int, languages: Array<IntArray>, friendships: Array<IntArray>): Int {
        val need = hashSetOf<Int>()

        for ((u, v) in friendships) {
            val match = languages[u - 1].any { it in languages[v - 1] }
            if (!match) {
                need += u - 1
                need += v - 1
            }
        }

        var mini = languages.size + 1

        for (i in 1..n) {
            val ans = need.count { i !in languages[it] }
            mini = minOf(mini, ans)
        }

        return mini
    }

    data class MinimumNumberOfPeopleToTeachParams(
        val n: Int,
        val languages: Array<IntArray>,
        val friendships: Array<IntArray>
    ) {
        override fun toString(): String {
            return """
                
                n: $n
                languages: ${stringFromArray(languages)}
                friendships: ${stringFromArray(friendships)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as MinimumNumberOfPeopleToTeachParams

            if (n != other.n) return false
            if (!languages.contentDeepEquals(other.languages)) return false
            if (!friendships.contentDeepEquals(other.friendships)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = n
            result = 31 * result + languages.contentDeepHashCode()
            result = 31 * result + friendships.contentDeepHashCode()
            return result
        }
    }
}
