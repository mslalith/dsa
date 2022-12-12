package src.linkedlist.problems

import src.core.Problem
import src.core.TestCase
import src.linkedlist.ListNode
import src.utils.buildLinkedList
import src.utils.stringFromListNode

class RemoveNthNodeFromListEnd : Problem<RemoveNthNodeFromListEndParams, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveNthNodeFromListEnd().run()
    }

    override fun getTestCases(): Array<TestCase<RemoveNthNodeFromListEndParams, ListNode?>> = arrayOf(
        TestCase(
            RemoveNthNodeFromListEndParams(
                buildLinkedList("1 2 3 4 5"),
                2
            ),
            buildLinkedList("1 2 3 5")
        ),
        TestCase(
            RemoveNthNodeFromListEndParams(
                buildLinkedList("1"),
                1
            ),
            null
        ),
        TestCase(
            RemoveNthNodeFromListEndParams(
                buildLinkedList("20 380 349 322 389 424 429 120 64 691 677 58 327 631 916 203 484 918 596 252 509 644 33 460"),
                82
            ),
            buildLinkedList("380 349 322 389 424 429 120 64 691 677 58 327 631 916 203 484 918 596 252 509 644 33 460")
        )
    )

    override fun solve(testCaseInput: RemoveNthNodeFromListEndParams): ListNode {
        return removeNthFromEnd(testCaseInput.head, testCaseInput.lastNthIndexToRemove)!!
    }

    private fun removeNthFromEnd(head: ListNode, index: Int): ListNode? {
        var size = 0
        var curr: ListNode? = head
        while (curr != null) {
            curr = curr.next
            size++
        }
        if (index >= size) return head.next
        val indexToRemove = size - index
        var count = 0
        curr = head
        while (count != indexToRemove - 1) {
            curr = curr?.next
            count++
        }
        curr?.next = curr?.next?.next
        return head
    }

}

data class RemoveNthNodeFromListEndParams(
    val head: ListNode,
    val lastNthIndexToRemove: Int
) {
    override fun toString(): String {
        return """
              
              Linked List: ${stringFromListNode(head)}
              Last nth index: $lastNthIndexToRemove
              """.trimIndent()
    }
}
