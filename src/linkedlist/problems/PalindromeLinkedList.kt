package src.linkedlist.problems

import src.core.Problem
import src.core.TestCase
import src.linkedlist.ListNode
import src.utils.buildLinkedList

class PalindromeLinkedList : Problem<ListNode?, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = PalindromeLinkedList().run()
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
        val start: ListNode? = ListNode(0)
        start?.next = head

        var slow = start
        var fast = start

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

    private fun reverseLinkedList(root: ListNode?): ListNode? {
        var prev: ListNode? = null
        var curr = root
        while (curr != null) {
            val next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        return prev
    }
}