package src.linked_list.problems

import src.core.Problem
import src.core.TestCase
import src.linked_list.ListNode
import src.utils.buildLinkedList

class RemoveDuplicatesFromSortedList : Problem<ListNode?, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = RemoveDuplicatesFromSortedList().run()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, ListNode?>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,2,3,3"),
            output = buildLinkedList(input = "1,2,3")
        )
    )

    override fun solve(testCaseInput: ListNode?): ListNode? {
        return deleteDuplicates(testCaseInput)
    }

    private fun deleteDuplicates(head: ListNode?): ListNode? {
        var curr: ListNode? = head
        while (curr != null) {
            var first = curr
            while (first?.next != null && first.`val` == first.next?.`val`) {
                first = first.next
            }
            curr.next = first?.next
            curr = curr.next
        }
        return head
    }
}
