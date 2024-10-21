package dev.mslalith.linkedlist.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.linkedlist.utils.reverseLinkedList
import dev.mslalith.utils.buildLinkedList

class ReverseLinkedList : Problem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseLinkedList().run()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5"),
            output = buildLinkedList(input = "5,4,3,2,1")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2"),
            output = buildLinkedList(input = "2,1")
        ),
        TestCase(
            input = buildLinkedList(input = ""),
            output = buildLinkedList(input = "")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        return reverseLinkedList(testCaseInput)
    }
}