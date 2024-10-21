package dev.mslalith.linkedlist.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList

class RemoveDuplicatesFromSortedListII : Problem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveDuplicatesFromSortedListII().run()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,3,4,4,5"),
            output = buildLinkedList("1,2,5")
        ),
        TestCase(
            input = buildLinkedList(input = "1,1,1,2,3"),
            output = buildLinkedList("2,3")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        return deleteDuplicates(testCaseInput)
    }

    private fun deleteDuplicates(head: ListNode?): ListNode? {
        val start = ListNode(0)
        start.next = head
        var prev: ListNode? = start
        var curr = head

        while (curr?.next != null) {
            var first = curr
            while (first?.next != null && first.`val` == first.next?.`val`) {
                first = first.next
            }
            if (curr == first) {
                prev = curr
                curr = curr.next
            } else {
                prev?.next = first?.next
                curr = first?.next
            }
        }

        return start.next
    }
}