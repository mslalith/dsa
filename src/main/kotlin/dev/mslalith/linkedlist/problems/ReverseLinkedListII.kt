package dev.mslalith.linkedlist.problems

import dev.mslalith.core.Problem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList
import dev.mslalith.utils.displayStringFromListNode
import dev.mslalith.utils.stringFromListNode

class ReverseLinkedListII : Problem<ReverseLinkedListIIParams, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = ReverseLinkedListII().run()
    }

    override fun getTestCases(): Array<TestCase<ReverseLinkedListIIParams, ListNode?>> = arrayOf(
        TestCase(
            input = ReverseLinkedListIIParams(
                head = buildLinkedList(input = "1,2,3,4,5"),
                left = 2,
                right = 4
            ),
            output = buildLinkedList(input = "1,4,3,2,5")
        ),
        TestCase(
            input = ReverseLinkedListIIParams(
                head = buildLinkedList(input = "5"),
                left = 1,
                right = 1
            ),
            output = buildLinkedList(input = "5")
        ),
        TestCase(
            input = ReverseLinkedListIIParams(
                head = buildLinkedList(input = "1,2,3,4,5"),
                left = 1,
                right = 5
            ),
            output = buildLinkedList(input = "5,4,3,2,1")
        )
    )

    override fun solve(testCaseInput: ReverseLinkedListIIParams): ListNode? {
        val head = buildLinkedList(stringFromListNode(testCaseInput.head))
        return reverseBetween(
            head = head,
            left = testCaseInput.left,
            right = testCaseInput.right
        )
    }

    private fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head == null) return null

        var count = 1
        val start = ListNode(0)
        start.next = head
        var curr: ListNode? = start

        while (curr != null && count < left) {
            curr = curr.next
            count++
        }

        val leftEnd = curr
        val newEnd = curr?.next
        curr = curr?.next

        var prev: ListNode? = null
        var next = curr?.next
        while (curr != null && count <= right) {
            curr.next = prev
            prev = curr
            curr = next
            next = next?.next
            count++
        }

        leftEnd?.next = prev
        newEnd?.next = curr

        return start.next
    }
}

data class ReverseLinkedListIIParams(
    val head: ListNode?,
    val left: Int,
    val right: Int
) {
    override fun toString(): String {
        return """
            
            head: ${displayStringFromListNode(head)}
            left: $left
            right: $right
        """.trimIndent()
    }
}