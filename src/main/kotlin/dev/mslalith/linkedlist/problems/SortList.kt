package dev.mslalith.linkedlist.problems

import dev.mslalith.core.TestCase
import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList

class SortList : TestCaseProblem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = SortList().runAll()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "4,2,1,3"),
            output = buildLinkedList(input = "1,2,3,4")
        ),
        TestCase(
            input = buildLinkedList(input = "-1,5,3,4,0"),
            output = buildLinkedList(input = "-1,0,3,4,5")
        ),
        TestCase(
            input = buildLinkedList(input = ""),
            output = buildLinkedList(input = "")
        ),
        TestCase(
            input = buildLinkedList(input = "1,2,3,4"),
            output = buildLinkedList(input = "1,2,3,4")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        return sortList(testCaseInput)
    }

    private fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var prev = head
        var slow = head
        var fast = head
        while (fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }
        prev?.next = null

        val left = sortList(head)
        val right = sortList(slow)

        return merge(left, right)
    }

    private fun merge(left: ListNode?, right: ListNode?): ListNode? {
        val start = ListNode(0)

        var l = left
        var r = right
        var curr: ListNode? = start

        while (l != null && r != null) {
            if (l.`val` <= r.`val`) {
                curr?.next = l
                l = l.next
            } else {
                curr?.next = r
                r = r.next
            }
            curr = curr?.next
        }

        while (l != null) {
            curr?.next = l
            curr = curr?.next
            l = l.next
        }

        while (r != null) {
            curr?.next = r
            curr = curr?.next
            r = r.next
        }

        return start.next
    }

    private fun sortListNaive(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val start = ListNode(0)

        var curr = head
        while (curr != null) {
            if (start.next == null) {
                start.next = ListNode(curr.`val`)
            } else {
                val node = ListNode(curr.`val`)
                var newCurr = start.next
                if (newCurr != null && node.`val` < newCurr.`val`) {
                    val next = start.next
                    start.next = node
                    node.next = next
                } else {
                    while (newCurr?.next != null && newCurr.next!!.`val` < curr.`val`) newCurr = newCurr.next
                    val next = newCurr?.next
                    newCurr?.next = node
                    node.next = next
                }
            }

            curr = curr.next
        }

        return start.next
    }
}
