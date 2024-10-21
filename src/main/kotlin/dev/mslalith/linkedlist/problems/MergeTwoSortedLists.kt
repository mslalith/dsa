package dev.mslalith.linkedlist.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList

class MergeTwoSortedLists : Problem<Pair<ListNode?, ListNode?>, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MergeTwoSortedLists().run()
    }

    override fun getTestCases(): Array<TestCase<Pair<ListNode?, ListNode?>, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,4") to buildLinkedList(input = "1,3,4"),
            output = buildLinkedList(input = "1,1,2,3,4,4")
        ),
        TestCase(
            input = buildLinkedList(input = "") to buildLinkedList(input = ""),
            output = buildLinkedList(input = "")
        ),
        TestCase(
            input = buildLinkedList(input = "") to buildLinkedList(input = "0"),
            output = buildLinkedList(input = "0")
        )
    )

    override fun solve(testCaseInput: Pair<ListNode?, ListNode?>): ListNode? {
        return mergeTwoLists(testCaseInput.first, testCaseInput.second)
    }

    private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val head = ListNode(0)

        var curr = head
        var curr1 = list1
        var curr2 = list2

        while (curr1 != null && curr2 != null) {
            if (curr1.`val` <= curr2.`val`) {
                curr.next = curr1
                curr = curr.next!!
                curr1 = curr1.next
            } else {
                curr.next = curr2
                curr = curr.next!!
                curr2 = curr2.next
            }
        }

        while (curr1 != null) {
            curr.next = curr1
            curr = curr.next!!
            curr1 = curr1.next
        }

        while (curr2 != null) {
            curr.next = curr2
            curr = curr.next!!
            curr2 = curr2.next
        }

        return head.next
    }
}
