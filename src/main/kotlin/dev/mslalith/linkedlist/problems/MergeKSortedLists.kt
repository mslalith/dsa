package dev.mslalith.linkedlist.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList
import java.util.PriorityQueue

class MergeKSortedLists : TestCaseProblem<Array<ListNode?>, ListNode?>() {
    
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = MergeKSortedLists().runForConsole()
    }
    
    override fun getTestCases(): Array<TestCase<Array<ListNode?>, ListNode?>> = arrayOf(
        TestCase(
            input = arrayOf(
                buildLinkedList(input = "1,4,5"),
                buildLinkedList(input = "1,3,4"),
                buildLinkedList(input = "2,6")
            ),
            output = buildLinkedList(input = "1,1,2,3,4,4,5,6")
        ),
        TestCase(
            input = emptyArray(),
            output = buildLinkedList(input = "")
        ),
        TestCase(
            input = arrayOf(
                buildLinkedList(input = "")
            ),
            output = buildLinkedList(input = "")
        )
    )
    
    override fun solve(testCaseInput: Array<ListNode?>): ListNode? {
        return mergeKLists(testCaseInput)
    }

    private fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        val pq = PriorityQueue<Pair<Int, ListNode>> { a, b ->
            a.first - b.first
        }

        for (head in lists) {
            if (head == null) continue
            pq.add(head.`val` to head)
        }

        val start = ListNode(0)
        var curr: ListNode? = start

        while (pq.isNotEmpty()) {
            val (_, node) = pq.poll()

            curr?.next = node
            curr = curr?.next

            val next = node.next
            if (next != null) pq.add(next.`val` to next)
        }

        return start.next
    }

    private fun mergeKListsByMergingAdjacentLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        fun merge2Lists(head1: ListNode?, head2: ListNode?): ListNode? {
            if (head1 == null && head2 == null) return null
            if (head1 == null || head2 == null) return head1 ?: head2

            val start = ListNode(0)
            var curr: ListNode? = start
            var curr1 = head1
            var curr2 = head2

            while (curr1 != null && curr2 != null) {
                if (curr1.`val` <= curr2.`val`) {
                    curr?.next = curr1
                    curr = curr?.next
                    curr1 = curr1.next
                } else {
                    curr?.next = curr2
                    curr = curr?.next
                    curr2 = curr2.next
                }
            }

            while (curr1 != null) {
                curr?.next = curr1
                curr = curr?.next
                curr1 = curr1.next
            }

            while (curr2 != null) {
                curr?.next = curr2
                curr = curr?.next
                curr2 = curr2.next
            }

            return start.next
        }

        var head = lists[0]

        for (i in lists.indices) {
            if (i == 0) continue
            head = merge2Lists(head, lists[i])
        }

        return head
    }
}
