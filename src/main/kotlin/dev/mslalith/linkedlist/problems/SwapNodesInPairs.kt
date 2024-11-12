package dev.mslalith.linkedlist.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList
import dev.mslalith.utils.createClone

class SwapNodesInPairs : TestCaseProblem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SwapNodesInPairs().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,4"),
            output = buildLinkedList(input = "2,1,4,3")
        ),
        TestCase(
            input = buildLinkedList(input = ""),
            output = buildLinkedList(input = "")
        ),
        TestCase(
            input = buildLinkedList(input = "1"),
            output = buildLinkedList(input = "1")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3"),
            output = buildLinkedList(input = "2,1,3")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        return swapPairs(testCaseInput?.createClone())
    }

    private fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val start = ListNode(0)
        start.next = head

        var prev: ListNode? = start
        var curr = head
        var next = curr.next

        while (next != null) {
            val later = next.next
            prev?.next = next
            curr?.next = later
            next.next = curr

            prev = curr
            curr = prev?.next
            next = curr?.next
        }

        return start.next
    }
}
