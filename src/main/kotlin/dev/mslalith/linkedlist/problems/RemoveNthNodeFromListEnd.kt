package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList
import dev.mslalith.utils.displayStringFromListNode

class RemoveNthNodeFromListEnd : TestCaseProblem<RemoveNthNodeFromListEndParams, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveNthNodeFromListEnd().runAll()
    }

    override fun getTestCases(): Array<TestCase<RemoveNthNodeFromListEndParams, ListNode?>> = arrayOf(
        TestCase(
            input = RemoveNthNodeFromListEndParams(
                head = buildLinkedList(input = "1,2,3,4,5"),
                lastNthIndexToRemove = 2
            ),
            output = buildLinkedList(input = "1,2,3,5")
        ),
        TestCase(
            input = RemoveNthNodeFromListEndParams(
                head = buildLinkedList(input = "1"),
                lastNthIndexToRemove = 1
            ),
            output = null
        ),
        TestCase(
            input = RemoveNthNodeFromListEndParams(
                head = buildLinkedList(input = "20,380,349,322,389,424,429,120,64,691,677,58,327,631,916,203,484,918,596,252,509,644,33,460"),
                lastNthIndexToRemove = 82
            ),
            output = buildLinkedList(input = "380,349,322,389,424,429,120,64,691,677,58,327,631,916,203,484,918,596,252,509,644,33,460")
        )
    )

    override fun solve(testCaseInput: RemoveNthNodeFromListEndParams): ListNode? {
        return removeNthFromEnd(testCaseInput.head, testCaseInput.lastNthIndexToRemove)
    }

    private fun removeNthFromEnd(head: ListNode?, index: Int): ListNode? {
        if (head == null) return null

        val start = ListNode(0)
        start.next = head

        var slow: ListNode? = start
        var fast: ListNode? = start

        for (i in 0 until index + 1) fast = fast?.next

        while (fast != null) {
            fast = fast.next
            slow = slow?.next
        }

        slow?.next = slow?.next?.next
        return start.next
    }

}

data class RemoveNthNodeFromListEndParams(
    val head: ListNode?,
    val lastNthIndexToRemove: Int
) {
    override fun toString(): String {
        return """
              
              Linked List: ${displayStringFromListNode(head)}
              Last nth index: $lastNthIndexToRemove
              """.trimIndent()
    }
}
