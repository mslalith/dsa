package dev.mslalith.queues.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.queues.problems.FindMedianFromDataStreamType.AddNum
import dev.mslalith.queues.problems.FindMedianFromDataStreamType.FindMedian
import java.util.PriorityQueue
import kotlin.math.absoluteValue

class FindMedianFromDataStream : TestCaseProblem<List<FindMedianFromDataStreamType>, List<Double?>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = FindMedianFromDataStream().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<List<FindMedianFromDataStreamType>, List<Double?>>> = arrayOf(
        TestCase(
            input = listOf(
                AddNum(num = 1),
                AddNum(num = 2),
                FindMedian,
                AddNum(num = 3),
                FindMedian
            ),
            output = listOf(null, null, 1.5, null, 2.0)
        ),
        TestCase(
            input = listOf(
                AddNum(num = 6),
                FindMedian,
                AddNum(num = 10),
                FindMedian,
                AddNum(num = 2),
                FindMedian,
                AddNum(num = 6),
                FindMedian,
                AddNum(num = 5),
                FindMedian,
                AddNum(num = 0),
                FindMedian,
                AddNum(num = 6),
                FindMedian,
                AddNum(num = 3),
                FindMedian,
                AddNum(num = 1),
                FindMedian,
                AddNum(num = 0),
                FindMedian,
                AddNum(num = 0),
                FindMedian
            ),
            output = listOf(null, 6.00000, null, 8.00000, null, 6.00000, null, 6.00000, null, 6.00000, null, 5.50000, null, 6.00000, null, 5.50000, null, 5.00000, null, 4.00000, null, 3.00000)
        )
    )

    override fun solve(testCaseInput: List<FindMedianFromDataStreamType>): List<Double?> = buildList {
        val medianFinder = MedianFinder()
        for (type in testCaseInput) {
            when (type) {
                FindMedian -> add(medianFinder.findMedian())
                is AddNum -> {
                    medianFinder.addNum(num = type.num)
                    add(null)
                }
            }
        }
    }
}

private class MedianFinder {

    private val smallPq = PriorityQueue<Int> { a, b -> b - a }
    private val largePq = PriorityQueue<Int>()

    fun addNum(num: Int) {
        if (smallPq.isEmpty() || num <= smallPq.peek()) smallPq.add(num)
        else largePq.add(num)

        val sizeDiff = (smallPq.size - largePq.size).absoluteValue
        if (sizeDiff > 1) {
            if (smallPq.size > largePq.size) largePq.add(smallPq.poll())
            else smallPq.add(largePq.poll())
        }
    }

    fun findMedian(): Double {
        return if (smallPq.size == largePq.size) {
            (smallPq.peek().toDouble() + largePq.peek()) / 2.0
        } else if (smallPq.size > largePq.size) {
            smallPq.peek().toDouble()
        } else {
            largePq.peek().toDouble()
        }
    }
}

sealed class FindMedianFromDataStreamType {
    data object FindMedian : FindMedianFromDataStreamType()
    data class AddNum(val num: Int) : FindMedianFromDataStreamType()
}
