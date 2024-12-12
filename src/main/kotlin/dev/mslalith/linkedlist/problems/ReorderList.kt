package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.linkedlist.utils.reverseLinkedList
import dev.mslalith.utils.buildLinkedList
import dev.mslalith.utils.stringFromListNode

class ReorderList : TestCaseProblem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReorderList().runForConsole()
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
}
