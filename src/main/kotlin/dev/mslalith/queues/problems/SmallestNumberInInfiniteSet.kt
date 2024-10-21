package dev.mslalith.queues.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import java.util.*

class SmallestNumberInInfiniteSet : Problem<Array<Int?>, List<Int>>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SmallestNumberInInfiniteSet().run()
    }

    override fun getTestCases(): Array<TestCase<Array<Int?>, List<Int>>> = arrayOf(
        TestCase(
            input = arrayOf(2, null, null, null, 1, null, null, null),
            output = listOf(1, 2, 3, 1, 4, 5)
        ),
        TestCase(
            input = arrayOf(null, null, 3, null, 2, null, null),
            output = listOf(1, 2, 3, 2, 4)
        ),
        TestCase(
            input = arrayOf(null, 352, null, 610, 140),
            output = listOf(1, 2)
        )
    )

    override fun solve(testCaseInput: Array<Int?>): List<Int> = buildList {
        val set = SmallestInfiniteSet()
        testCaseInput.forEach {
            if (it == null) add(set.popSmallest()) else set.addBack(it)
        }
    }
}

private class SmallestInfiniteSet {

    private val queue = PriorityQueue<Int>()
    private var count = 1

    fun popSmallest(): Int {
        if (queue.isEmpty()) return count++
        return queue.remove()
    }

    fun addBack(num: Int) {
        if (!queue.contains(num) && num < count) queue.add(num)
    }
}
