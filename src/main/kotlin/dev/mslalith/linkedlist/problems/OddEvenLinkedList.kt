package dev.mslalith.linkedlist.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList

class OddEvenLinkedList : Problem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = OddEvenLinkedList().run()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5"),
            output = buildLinkedList(input = "1,3,5,2,4")
        ),
        TestCase(
            input = buildLinkedList(input = "2,1,3,5,6,4,7"),
            output = buildLinkedList(input = "2,3,6,7,1,5,4")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        return oddEvenList(testCaseInput)
    }

    private fun oddEvenList(head: ListNode?): ListNode? {
        if (head?.next?.next == null) return head

        var lastOdd = head
        var lastEven = head.next
        val evenHead = lastEven

        while (lastEven?.next != null) {
            lastOdd?.next = lastEven.next
            lastOdd = lastOdd?.next
            lastEven.next = lastOdd?.next
            lastEven = lastEven.next
        }

        lastOdd?.next = evenHead
        return head
    }
}