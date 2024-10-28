package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.utils.buildLinkedList
import dev.mslalith.utils.stringFromListNode

class PartitionList : TestCaseProblem<PartitionListParams, String>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PartitionList().runForConsole()
    }

    override fun getTestCases(): Array<TestCase<PartitionListParams, String>> = arrayOf(
        TestCase(
            input = PartitionListParams(
                head = "1,4,3,2,5,2",
                x = 3
            ),
            output = "1,2,2,4,3,5"
        ),
        TestCase(
            input = PartitionListParams(
                head = "2,1",
                x = 2
            ),
            output = "1,2"
        )
    )

    override fun solve(testCaseInput: PartitionListParams): String {
        return stringFromListNode(
            head = partition(
                head = buildLinkedList(testCaseInput.head),
                x = testCaseInput.x
            )
        )
    }

    private fun partition(head: ListNode?, x: Int): ListNode? {
        if (head?.next == null) return head

        val left = ListNode(0)
        val right = ListNode(0)

        var leftCurr: ListNode? = left
        var rightCurr: ListNode? = right
        var curr = head

        while (curr != null) {
            if (curr.`val` < x) {
                leftCurr?.next = ListNode(curr.`val`)
                leftCurr = leftCurr?.next
            } else {
                rightCurr?.next = ListNode(curr.`val`)
                rightCurr = rightCurr?.next
            }
            curr = curr.next
        }
        leftCurr?.next = right.next
        return left.next
    }
}

data class PartitionListParams(
    val head: String,
    val x: Int
) {
    override fun toString(): String {
        return """
            
            head: $head
            x: $x
        """.trimIndent()
    }
}