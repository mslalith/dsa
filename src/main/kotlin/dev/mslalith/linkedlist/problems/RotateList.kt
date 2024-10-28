package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList

class RotateList : TestCaseProblem<Pair<ListNode?, Int>, ListNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RotateList().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Pair<ListNode?, Int>, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5") to 2,
            output = buildLinkedList(input = "4,5,1,2,3")
        ),
        TestCase(
            input = buildLinkedList(input = "0,1,2") to 4,
            output = buildLinkedList(input = "2,0,1")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2") to 1,
            output = buildLinkedList(input = "2,1")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4") to 4,
            output = buildLinkedList(input = "1,2,3,4")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4") to 6,
            output = buildLinkedList(input = "3,4,1,2")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4,5") to 10,
            output = buildLinkedList(input = "1,2,3,4,5")
        )
    )
//        .let { arrayOf(it.last()) }
    
    override fun solve(testCaseInput: Pair<ListNode?, Int>): ListNode? {
        return rotateRight(testCaseInput.first, testCaseInput.second)
    }

    private fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) return head
        if (k == 0) return head

        val start = ListNode(0)
        start.next = head

        var count = 1
        var slow = head
        var fast = head

        while (fast?.next != null) {
            if (count > k) slow = slow?.next
            fast = fast.next
            count++
        }

        if (k % count == 0) return head

        if (count < k) {
            val c = k % count
            slow = head
            repeat(count - c - 1) { slow = slow?.next }
        }

        val next = slow?.next
        var curr = next
        while (curr?.next != null) curr = curr.next

        slow?.next = null
        start.next = next
        fast?.next = head

        return start.next
    }
}
