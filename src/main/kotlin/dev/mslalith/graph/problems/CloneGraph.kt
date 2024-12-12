package dev.mslalith.graph.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.graph.problems.CloneGraph.Node
import java.util.*

class CloneGraph : TestCaseProblem<Node?, Node?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = CloneGraph().runForConsole()

        private var count = 0
    }


    data class Node(
        val `val`: Int,
        val id: Int = ++count // to add uniqueness & for comparing two nodes
    ) {
        val neighbors: ArrayList<Node?> = ArrayList()
    }

    override fun getTestCases(): Array<TestCase<Node?, Node?>> = arrayOf(
        TestCase(
            input = buildGraph(
                indexToEdgesArray = listOf(
                    intArrayOf(2, 4),
                    intArrayOf(1, 3),
                    intArrayOf(2, 4),
                    intArrayOf(1, 3)
                )
            ),
            output = buildGraph(
                indexToEdgesArray = listOf(
                    intArrayOf(2, 4),
                    intArrayOf(1, 3),
                    intArrayOf(2, 4),
                    intArrayOf(1, 3)
                )
            )
        ),
        TestCase(
            input = buildGraph(
                indexToEdgesArray = listOf(
                    intArrayOf()
                )
            ),
            output = buildGraph(
                indexToEdgesArray = listOf(
                    intArrayOf()
                )
            )
        ),
        TestCase(
            input = buildGraph(
                indexToEdgesArray = emptyList()
            ),
            output = buildGraph(
                indexToEdgesArray = emptyList()
            )
        )
    )

    override fun isTestPassed(actual: Node?, expected: Node?): Boolean {
        val visited = hashSetOf<Int>()

        fun areNodesMatching(a: Node?, e: Node?): Boolean {
            if (a == null && e == null) return true
            if (a == null || e == null) {
                println("one of them is null a: $a, e: $e")
                return false
            }

            if (a.hashCode() == e.hashCode()) {
                println("Both nodes are same a: $a, e: $e")
                return false
            }
            if (a.`val` != e.`val`) {
                println("value match failed for a: $a, e: $e")
                return false
            }

            visited += a.`val`

            val actualNeighbours = a.neighbors.filterNotNull().sortedBy { it.`val` }
            val expectedNeighbours = e.neighbors.filterNotNull().sortedBy { it.`val` }
            if (actualNeighbours.size != expectedNeighbours.size) {
                println("Neighbours count not matching")
                return false
            }

            actualNeighbours.zip(expectedNeighbours) { x, y ->
                if (x.`val` in visited && y.`val` !in visited) return false
                if (x.`val` !in visited && y.`val` in visited) return false
                if (x.`val` !in visited && y.`val` !in visited && !areNodesMatching(x, y)) return false
            }
            return true
        }

        return areNodesMatching(actual, expected)
    }

    override fun solve(testCaseInput: Node?): Node? {
        return cloneGraph(testCaseInput)
    }

    private fun cloneGraph(node: Node?): Node? {
        return cloneGraphBfs(node)
    }

    private fun cloneGraphBfs(node: Node?): Node? {
        if (node == null) return null

        val map = hashMapOf<Int, Node>()

        val queue: Queue<Node> = LinkedList()
        queue.add(node)

        while (queue.isNotEmpty()) {
            val curr = queue.poll()

            val currClone = map.getOrPut(curr.`val`) { Node(curr.`val`) }

            for (neighbour in curr.neighbors) {
                if (neighbour == null) continue

                if (!map.containsKey(neighbour.`val`)) {
                    map[neighbour.`val`] = Node(neighbour.`val`)
                    queue.add(neighbour)
                }

                val nClone = map.getValue(neighbour.`val`)
                currClone.neighbors.add(nClone)
            }
        }

        return map.getValue(node.`val`)
    }

    private fun cloneGraphDfs(node: Node?): Node? {
        if (node == null) return null

        val map = hashMapOf<Int, Node>()

        fun dfs(node: Node): Node {
            if (map.contains(node.`val`)) return map.getValue(node.`val`)

            val clone = Node(node.`val`)
            map[node.`val`] = clone

            for (neighbour in node.neighbors) {
                if (neighbour == null) continue
                clone.neighbors.add(dfs(neighbour))
            }

            return clone
        }

        return dfs(node)
    }

    private fun buildGraph(indexToEdgesArray: List<IntArray>): Node? {
        if (indexToEdgesArray.isEmpty()) return null

        val map = hashMapOf<Int, Node>()

        for (i in indexToEdgesArray.indices) {
            val value = i + 1
            val node = map.getOrPut(value) { Node(value) }
            for (neighbour in indexToEdgesArray[i]) {
                val nNode = map.getOrPut(neighbour) { Node(neighbour) }
                node.neighbors.add(nNode)
            }
        }

        return if (map.isEmpty()) null else map[map.keys.first()]
    }
}
