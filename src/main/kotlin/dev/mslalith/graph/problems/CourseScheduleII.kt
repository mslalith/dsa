package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.Stack

class CourseScheduleII : TestCaseProblem<Pair<Int, Array<IntArray>>, IntArray>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CourseScheduleII().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, IntArray>> = arrayOf(
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(1, 0)
            ),
            output = intArrayOf(0, 1)
        ),
        TestCase(
            input = 4 to arrayOf(
                intArrayOf(1, 0),
                intArrayOf(2, 0),
                intArrayOf(3, 1),
                intArrayOf(3, 2)
            ),
            output = intArrayOf(0, 2, 1, 3)
        ),
        TestCase(
            input = 1 to arrayOf(),
            output = intArrayOf(0)
        ),
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            ),
            output = intArrayOf()
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): IntArray {
        return findOrder(testCaseInput.first, testCaseInput.second)
    }

    private fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val inDegree = Array(numCourses) { 0 }
        val adjList = Array(numCourses) { mutableListOf<Int>() }
        prerequisites.forEach { (a, b) ->
            adjList[a].add(b)
            inDegree[b]++
        }

        val queue = ArrayDeque<Int>()
        val order = mutableListOf<Int>()

        for (course in inDegree.indices) {
            if (inDegree[course] == 0) queue.add(course)
        }

        while (queue.isNotEmpty()) {
            val course = queue.removeFirst()
            order.add(course)

            for (nextCourse in adjList[course]) {
                inDegree[nextCourse]--
                if (inDegree[nextCourse] == 0) queue.add(nextCourse)
            }
        }

        return if (order.size != numCourses) intArrayOf() else {
            val result = IntArray(numCourses)
            for (i in order.indices) result[i] = order[order.lastIndex - i]
            result
        }
    }

    private fun findOrderNaive(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val adjList = Array(numCourses) { mutableListOf<Int>() }
        prerequisites.forEach { (a, b) -> adjList[a].add(b) }

        val visited = IntArray(numCourses)

        fun hasCycle(curr: Int): Boolean {
            visited[curr] = 2

            for (next in adjList[curr]) {
                when(visited[next]) {
                    0 -> if (hasCycle(next)) return true
                    2 -> return true
                }
            }

            visited[curr] = 1
            return false
        }

        for (i in 0 until numCourses) {
            if (visited[i] == 0 && hasCycle(i)) return intArrayOf()
        }

        visited.indices.forEach { visited[it] = 0 }
        val stack = Stack<Int>()

        fun dfs(index: Int) {
            if (visited[index] == 1) return

            visited[index] = 1
            adjList[index].forEach { dfs(it) }
            stack.push(index)
        }

        for (i in 0 until numCourses) dfs(i)

        return stack.toIntArray()
    }
}
