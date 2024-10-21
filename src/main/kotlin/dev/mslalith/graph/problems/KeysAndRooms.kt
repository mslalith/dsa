package dev.mslalith.graph.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase

class KeysAndRooms : Problem<List<List<Int>>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = KeysAndRooms().run()
    }

    override fun getTestCases(): Array<TestCase<List<List<Int>>, Boolean>> = arrayOf(
        TestCase(
            input = listOf(
                listOf(1),
                listOf(2),
                listOf(3),
                listOf()
            ),
            output = true
        ),
        TestCase(
            input = listOf(
                listOf(1, 3),
                listOf(3, 0, 1),
                listOf(2),
                listOf(0)
            ),
            output = false
        )
    )

    override fun solve(testCaseInput: List<List<Int>>): Boolean {
        return canVisitAllRooms(testCaseInput)
    }

    private fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        if (rooms.isEmpty()) return false

        val queue = ArrayDeque<Int>()
        rooms.first().forEach { queue.add(it) }

        val visited = BooleanArray(rooms.size) { false }
        visited[0] = true

        while (queue.isNotEmpty()) {
            val room = queue.removeFirst()
            if (!visited[room]) {
                visited[room] = true
                rooms[room].forEach { queue.add(it) }
            }
        }

        return visited.all { it }
    }
}
