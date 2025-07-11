package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.utils.createClone
import java.util.*


class MeetingRoomsIII : TestCaseProblem<Pair<Int, Array<IntArray>>, Int>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MeetingRoomsIII().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<Pair<Int, Array<IntArray>>, Int>> = arrayOf(
        TestCase(
            input = 2 to arrayOf(
                intArrayOf(0, 10),
                intArrayOf(1, 5),
                intArrayOf(2, 7),
                intArrayOf(3, 4)
            ),
            output = 0
        ),
        TestCase(
            input = 3 to arrayOf(
                intArrayOf(1, 20),
                intArrayOf(2, 10),
                intArrayOf(3, 5),
                intArrayOf(4, 9),
                intArrayOf(6, 8)
            ),
            output = 1
        )
    )

    override fun solve(testCaseInput: Pair<Int, Array<IntArray>>): Int {
        return mostBooked(testCaseInput.first, testCaseInput.second.createClone())
    }

    private fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }

        val count = IntArray(n)
        val engaged = PriorityQueue<IntArray> { a, b ->
            if (a[1] == b[1]) a[0] - b[0] else a[1] - b[1]
        }

        val unused = PriorityQueue<Int>()
        for (i in 0..<n) unused.offer(i)

        for (meeting in meetings) {
            val (start, end) = meeting

            while (engaged.isNotEmpty() && engaged.peek()[1] <= start) {
                val room = engaged.poll()[0]
                unused.offer(room)
            }

            if (unused.isNotEmpty()) {
                val room = unused.poll()
                count[room]++
                engaged.offer(intArrayOf(room, end))
            } else {
                val cur = engaged.poll()
                val (room, curEnd) = cur
                count[room]++

                val newEnd = curEnd + end - start
                engaged.offer(intArrayOf(room, newEnd))
            }
        }

        var maxRoomId = 0
        for (i in 1..<n) if (count[i] > count[maxRoomId]) maxRoomId = i
        return maxRoomId
    }
}
