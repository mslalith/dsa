package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode
import dev.mslalith.linkedlist.utils.reverseLinkedList
import dev.mslalith.utils.buildLinkedList

class PalindromeLinkedList : TestCaseProblem<ListNode?, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PalindromeLinkedList().runAll()
    }

    override fun getTestCases(): Array<TestCase<ListNode?, Boolean>> = arrayOf(
        TestCase(
            input = buildLinkedList(input = "1,2,2,1"),
            output = true
        ),
        TestCase(
            input = buildLinkedList(input = "1,21"),
            output = false
        )
    )

    override fun solve(testCaseInput: ListNode?): Boolean {
        return isPalindrome(testCaseInput)
    }

    private fun isPalindrome(head: ListNode?): Boolean {
        val start = ListNode(0)
        start.next = head

        var slow: ListNode? = start
        var fast: ListNode? = start

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }

        slow?.next = reverseLinkedList(slow?.next)
        slow = slow?.next

        fast = head
        while (slow != null && slow.`val` == fast?.`val`) {
            slow = slow.next
            fast = fast.next
        }
        return slow == null
    }
}