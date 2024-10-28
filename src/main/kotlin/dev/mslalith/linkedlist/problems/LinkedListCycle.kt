package dev.mslalith.linkedlist.problems

import dev.mslalith.core.problem.TestCaseProblem
import dev.mslalith.core.TestCase
import dev.mslalith.linkedlist.ListNode

class LinkedListCycle : TestCaseProblem<Pair<ListNode?, String>, Boolean>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LinkedListCycle().runForConsole()
    }

    override fun displayInput(input: Pair<ListNode?, String>): String = input.second

    override fun getTestCases(): Array<TestCase<Pair<ListNode?, String>, Boolean>> {
        val input = ListNode(3)
        val loopNode = ListNode(2)
        input.next = loopNode
        input.next?.next = ListNode(0)
        input.next?.next?.next = ListNode(-4)
        input.next?.next?.next?.next = loopNode
        return arrayOf(
            TestCase(
                input = input to "3, 2, 0, -4",
                output = true
            )
        )
    }

    override fun solve(testCaseInput: Pair<ListNode?, String>): Boolean {
        return hasCycle(testCaseInput.first)
    }

    private fun hasCycle(head: ListNode?): Boolean {
        if (head == null) return false

        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) return true
        }

        return false
    }
}
