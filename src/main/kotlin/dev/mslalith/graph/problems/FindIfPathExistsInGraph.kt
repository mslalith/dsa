package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.stringFromArray
import java.util.LinkedList
import java.util.Queue

class FindIfPathExistsInGraph : TestCaseProblem<FindIfPathExistsInGraphParams, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindIfPathExistsInGraph().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<FindIfPathExistsInGraphParams, Boolean>> = arrayOf(
        TestCase(
            input = FindIfPathExistsInGraphParams(
                n = 3,
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2),
                    intArrayOf(2, 0)
                ),
                source = 0,
                destination = 2
            ),
            output = true
        ),
        TestCase(
            input = FindIfPathExistsInGraphParams(
                n = 6,
                edges = arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(0, 2),
                    intArrayOf(3, 5),
                    intArrayOf(5, 4),
                    intArrayOf(4, 3)
                ),
                source = 0,
                destination = 5
            ),
            output = false
        ),
        TestCase(
            input = FindIfPathExistsInGraphParams(
                n = 5,
                edges = arrayOf(
                    intArrayOf(0, 4)
                ),
                source = 0,
                destination = 4
            ),
            output = true
        ),
        TestCase(
            input = FindIfPathExistsInGraphParams(
                n = 1,
                edges = emptyArray(),
                source = 0,
                destination = 0
            ),
            output = true
        )
    )

    override fun solve(testCaseInput: FindIfPathExistsInGraphParams): Boolean {
        return validPath(
            n = testCaseInput.n,
            edges = testCaseInput.edges,
            source = testCaseInput.source,
            destination = testCaseInput.destination
        )
    }

    private fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (edges.isEmpty()) return n == 1 && source == 0 && destination == 0

        val adjList = List(n) { mutableListOf<Int>() }
        edges.forEach {
            adjList[it[0]].add(it[1])
            adjList[it[1]].add(it[0])
        }

        val visited = hashSetOf<Int>()
        val queue: Queue<Int> = LinkedList()
        queue.add(source)

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (node == destination) return true

            if (node !in visited) {
                visited += node
                for (neighbour in adjList[node]) queue.add(neighbour)
            }
        }

        return false
    }

    private fun validPathDfs(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        if (edges.isEmpty()) return n == 1 && source == 0 && destination == 0

        val adjList = List(n) { mutableListOf<Int>() }
        edges.forEach {
            adjList[it[0]].add(it[1])
            adjList[it[1]].add(it[0])
        }

        val visited = hashSetOf<Int>()

        fun hasPath(node: Int): Boolean {
            if (node in visited) return false
            if (node == destination) return true

            visited += node
            val neighbours = adjList[node]
            if (neighbours.isEmpty()) return false

            return neighbours.any { hasPath(it) }
        }

        return hasPath(source)
    }
}

data class FindIfPathExistsInGraphParams(
    val n: Int,
    val edges: Array<IntArray>,
    val source: Int,
    val destination: Int
) {
    override fun toString(): String {
        return """
            
            n: $n
            edges: ${stringFromArray(edges)}
            source: $source
            destination: $destination
        """.trimIndent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FindIfPathExistsInGraphParams

        if (n != other.n) return false
        if (!edges.contentDeepEquals(other.edges)) return false
        if (source != other.source) return false
        if (destination != other.destination) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n
        result = 31 * result + edges.contentDeepHashCode()
        result = 31 * result + source
        result = 31 * result + destination
        return result
    }
}
