package dev.mslalith.linkedlist.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.linkedlist.utils.reverseLinkedList
import dev.mslalith.utils.buildLinkedList
import dev.mslalith.utils.createClone

class ReverseNodesInKGroup : TestCaseProblem<Pair<ListNode?, Int>, ListNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseNodesInKGroup().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<ListNode?, Int>, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5") to 2,
            output = buildLinkedList(input = "2,1,4,3,5")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5") to 3,
            output = buildLinkedList(input = "3,2,1,4,5")
        )
    )
    
    override fun solve(testCaseInput: Pair<ListNode?, Int>): ListNode? {
        return reverseKGroup(testCaseInput.first?.createClone(), testCaseInput.second)
    }

    private fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) return head

        var newHead: ListNode? = null
        var start = head
        var curr = start
        var prev: ListNode? = null

        while (curr != null) {
            var i = 1
            while (curr != null && i < k) {
                i++
                curr = curr.next
            }

            if (curr == null) {
                prev?.next = start
                break
            }

            val nextGroup = curr.next
            curr.next = null

            val rHead = reverseLinkedList(start)
            if (newHead == null) newHead = rHead
            prev?.next = rHead

            prev = start
            start = nextGroup
            curr = start
        }

        return newHead
    }
}
