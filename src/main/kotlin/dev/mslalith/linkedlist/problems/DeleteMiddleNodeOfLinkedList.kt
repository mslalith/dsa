package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList

class DeleteMiddleNodeOfLinkedList : TestCaseProblem<ListNode?, ListNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = DeleteMiddleNodeOfLinkedList().runAll()
    }
    
    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,3,4,7,1,2,6"),
            output = buildLinkedList(input = "1,3,4,1,2,6")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4"),
            output = buildLinkedList(input = "1,2,4")
        )
    )
    
    override fun solve(testCaseInput: ListNode?): ListNode? {
        return deleteMiddle(testCaseInput)
    }

    private fun deleteMiddle(head: ListNode?): ListNode? {
        if (head?.next == null) return null

        var slow = head
        var fast = head.next

        while (fast?.next?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        slow?.next = slow?.next?.next
        return head
    }
}
