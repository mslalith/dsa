package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem

class CourseSchedule : TestCaseProblem<Pair<Int, Array<IntArray>>, Boolean>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CourseSchedule().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Boolean>> = arrayOf(
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(1, 0)
            ),
            output = true
        ),
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            ),
            output = false
        )
    )
    
    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Boolean {
        return canFinish(testCaseInput.first, testCaseInput.second)
    }

    private fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val adjList = Array(numCourses) { mutableListOf<Int>() }
        prerequisites.forEach { (a, b) -> adjList[a].add(b) }

        val visited = IntArray(numCourses)

        fun hasCycle(curr: Int): Boolean {
            visited[curr] = 2

            for (next in adjList[curr]) {
                when (visited[next]) {
                    0 -> if (hasCycle(next)) return true
                    2 -> return true
                }
            }

            visited[curr] = 1
            return false
        }

        for (i in 0 until numCourses) {
            if (visited[i] == 0 && hasCycle(i)) return false
        }

        return true
    }
}
