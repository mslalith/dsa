package src.linkedlist.problems

import src.core.Problem
import src.core.TestCase
import src.linkedlist.ListNode

class LinkedListCycleII : Problem<ListNode?, Int?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = LinkedListCycleII().run()
    }

    override val skipIO: Boolean get() = true

    override fun getTestCases(): Array<TestCase<ListNode?, Int?>> {
        val input = ListNode(3)
        val loopNode = ListNode(2)
        input.next = loopNode
        input.next?.next = ListNode(0)
        input.next?.next?.next = ListNode(-4)
        input.next?.next?.next?.next = loopNode
        return arrayOf(
            TestCase(
                input = input,
                output = 2
            )
        )
    }

    override fun solve(testCaseInput: ListNode?): Int? {
        return detectCycle(testCaseInput)?.`val`
    }

    private fun detectCycle(head: ListNode?): ListNode? {
        if (head == null) return null

        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) break
        }

        if (fast?.next == null) return null

        slow = head
        while (slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }

        return slow
    }
}