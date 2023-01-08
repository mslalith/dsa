package src.linked_list.problems

import src.core.Problem
import src.core.TestCase
import src.linked_list.ListNode
import src.utils.buildLinkedList

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
        return reverseList(testCaseInput)
    }

    private fun reverseList(head: ListNode?): ListNode? {
        if (head == null) return null

        var prev: ListNode? = null
        var curr = head

        while (curr != null) {
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }

        return prev
    }
}