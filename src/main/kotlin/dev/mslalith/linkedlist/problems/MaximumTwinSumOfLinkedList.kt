package dev.mslalith.linkedlist.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.linkedlist.utils.reverseLinkedList
import dev.mslalith.utils.buildLinkedList

class MaximumTwinSumOfLinkedList : Problem<ListNode?, Int>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MaximumTwinSumOfLinkedList().run()
    }
    
    override fun getTestCases(): Array<TestCase<ListNode?, Int>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "5,4,2,1"),
            output = 6
        ),
        TestCase(
            input = buildLinkedList(input = "4,2,2,3"),
            output = 7
        )
    )
    
    override fun solve(testCaseInput: ListNode?): Int {
        return pairSum(testCaseInput)
    }

    private fun pairSum(head: ListNode?): Int {
        var slow = head
        var fast = head?.next

        // find second half head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        // reverse second half
        val prev = slow
        val reversedHead = reverseLinkedList(slow?.next)
        prev?.next = reversedHead

        slow = head
        fast = reversedHead
        var max = 0

        // find max from all twins
        while (slow != null && fast != null) {
            (slow.`val` + fast.`val`).let {
                if (max < it) max = it
            }
            slow = slow.next
            fast = fast.next
        }

        return max
    }
}
