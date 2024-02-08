package src.linkedlist.problems

import src.core.Problem
import src.core.TestCase
import src.linkedlist.ListNode
import src.utils.buildLinkedList
import src.utils.stringFromListNode

class ReorderList : Problem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReorderList().run()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,4"),
            output = buildLinkedList(input = "1,4,2,3")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5"),
            output = buildLinkedList(input = "1,5,2,4,3")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        val node = buildLinkedList(stringFromListNode(testCaseInput))
        reorderList(node)
        return node
    }

    private fun reorderList(head: ListNode?) {
        var slow: ListNode? = head
        var fast: ListNode? = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        val reversed = reverseLinkedList(slow?.next)
        slow?.next = null

        slow = head
        fast = reversed

        while (fast != null) {
            val slowNode = ListNode(fast.`val`)
            slowNode.next = slow?.next
            slow?.next = slowNode
            slow = slowNode.next
            fast = fast.next
        }
    }

    private fun reverseLinkedList(head: ListNode?): ListNode? {
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