package dev.mslalith.trees.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import java.util.LinkedList
import java.util.Queue

class ConstructQuadTree : TestCaseProblem<Array<IntArray>, Array<IntArray?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ConstructQuadTree().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Array<IntArray>, Array<IntArray?>>> = arrayOf(
        TestCase(
            input = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0)
            ),
            output = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0),
                intArrayOf(1, 1),
                intArrayOf(1, 1),
                intArrayOf(1, 0)
            )
        ),
        TestCase(
            input = arrayOf(
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0)
            ),
            output = arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 1),
                intArrayOf(0, 1),
                intArrayOf(1, 1),
                intArrayOf(1, 0),
                intArrayOf(1, 0),
                intArrayOf(1, 0),
                intArrayOf(1, 1),
                intArrayOf(1, 1)
            )
        )
    )

    override fun solve(testCaseInput: Array<IntArray>): Array<IntArray?> {
        return construct(testCaseInput).toOutput()
    }

    private fun construct(grid: Array<IntArray>): Node? {
        val n = grid.size
        if (n == 0) return null

        fun areAllSame(row: Int, col: Int, size: Int): Boolean {
            val initial = grid[row][col]

            for (i in row until (row + size)) {
                for (j in col until (col + size)) {
                    if (grid[i][j] != initial) return false
                }
            }

            return true
        }

        fun build(row: Int, col: Int, size: Int): Node {
            if (areAllSame(row, col, size)) return Node(grid[row][col] == 1, true)

            val half = size / 2
            val node = Node(true, false)
            node.topLeft = build(row, col, half)
            node.topRight = build(row, col + half, half)
            node.bottomLeft = build(row + half, col, half)
            node.bottomRight = build(row + half, col + half, half)
            return node
        }

        return build(0, 0, n)
    }
}

private fun Node?.toOutput(): Array<IntArray?> {
    if (this == null) return emptyArray()

    val result = mutableListOf<IntArray?>()
    val queue: Queue<Node?> = LinkedList()
    queue.add(this)

    while (queue.isNotEmpty()) {
        val node = queue.poll()

        if (node == null) {
            result.add(null)
            continue
        }

        result.add(intArrayOf(if (node.isLeaf) 1 else 0, if (node.`val`) 1 else 0))

        if (node.isLeaf) continue

        queue.add(node.topLeft)
        queue.add(node.topRight)
        queue.add(node.bottomLeft)
        queue.add(node.bottomRight)
    }

    return result.toTypedArray()
}

data class Node(
    var `val`: Boolean,
    var isLeaf: Boolean
) {
    var topLeft: Node? = null
    var topRight: Node? = null
    var bottomLeft: Node? = null
    var bottomRight: Node? = null
}
