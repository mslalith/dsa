package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.CourseScheduleIV.CourseScheduleIVParams
import dev.mslalith.utils.stringFromArray

class CourseScheduleIV : TestCaseProblem<CourseScheduleIVParams, List<Boolean>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CourseScheduleIV().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<CourseScheduleIVParams, List<Boolean>>> = arrayOf(
        TestCase(
            input = CourseScheduleIVParams(
                numCourses = 2,
                prerequisites = arrayOf(
                    intArrayOf(1, 0)
                ),
                queries = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 0)
                )
            ),
            output = listOf(false, true)
        ),
        TestCase(
            input = CourseScheduleIVParams(
                numCourses = 2,
                prerequisites = arrayOf(),
                queries = arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(0, 1)
                )
            ),
            output = listOf(false, false)
        ),
        TestCase(
            input = CourseScheduleIVParams(
                numCourses = 3,
                prerequisites = arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 0),
                    intArrayOf(2, 0)
                ),
                queries = arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(1, 2)
                )
            ),
            output = listOf(true, true)
        ),
        TestCase(
            input = CourseScheduleIVParams(
                numCourses = 4,
                prerequisites = arrayOf(
                    intArrayOf(2, 3),
                    intArrayOf(2, 1),
                    intArrayOf(0, 3),
                    intArrayOf(0, 1)
                ),
                queries = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 3),
                    intArrayOf(2, 3),
                    intArrayOf(3, 0),
                    intArrayOf(2, 0),
                    intArrayOf(0, 2)
                )
            ),
            output = listOf(true, true, true, false, false, false)
        ),
        TestCase(
            input = CourseScheduleIVParams(
                numCourses = 5,
                prerequisites = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 3),
                    intArrayOf(3, 4)
                ),
                queries = arrayOf(
                    intArrayOf(0, 4),
                    intArrayOf(4, 0),
                    intArrayOf(1, 3),
                    intArrayOf(3, 0)
                )
            ),
            output = listOf(true, false, true, false)
        )
    )

    override fun solve(testCaseInput: CourseScheduleIVParams): List<Boolean> {
        return checkIfPrerequisite(testCaseInput.numCourses, testCaseInput.prerequisites, testCaseInput.queries)
    }

    private fun checkIfPrerequisite(
        numCourses: Int,
        prerequisites: Array<IntArray>,
        queries: Array<IntArray>
    ): List<Boolean> {
        if (prerequisites.isEmpty()) return List(numCourses) { false }

        val adjList = Array(numCourses) { mutableListOf<Int>() }
        for ((u, v) in prerequisites) adjList[v] += u

        val prerequisiteMap = hashMapOf<Int, MutableSet<Int>>()

        fun dfs(node: Int): Set<Int> {
            if (node !in prerequisiteMap) {
                val visitedSet = prerequisiteMap.getOrPut(node) { mutableSetOf() }

                for (neighbour in adjList[node]) {
                    visitedSet += neighbour
                    visitedSet += dfs(neighbour)
                }
            }

            return prerequisiteMap[node].orEmpty()
        }

        for (i in 0 until numCourses) dfs(i)

        return queries.map { (u, v) ->
            u in prerequisiteMap.getValue(v)
        }
    }

    data class CourseScheduleIVParams(
        val numCourses: Int,
        val prerequisites: Array<IntArray>,
        val queries: Array<IntArray>
    ) {
        override fun toString(): String {
            return """
                
                numCourses: $numCourses
                prerequisites: ${stringFromArray(prerequisites)}
                queries: ${stringFromArray(queries)}
            """.trimIndent()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CourseScheduleIVParams

            if (numCourses != other.numCourses) return false
            if (!prerequisites.contentDeepEquals(other.prerequisites)) return false
            if (!queries.contentDeepEquals(other.queries)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = numCourses
            result = 31 * result + prerequisites.contentDeepHashCode()
            result = 31 * result + queries.contentDeepHashCode()
            return result
        }
    }
}
