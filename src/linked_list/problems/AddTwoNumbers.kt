package src.linked_list.problems

import src.core.Problem
import src.core.TestCase
import src.linked_list.ListNode
import src.utils.buildLinkedList
import src.utils.stringFromListNode

class AddTwoNumbers : Problem<AddTwoNumbersParams, ListNode?>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = AddTwoNumbers().run()
    }

    override fun getTestCases(): Array<TestCase<AddTwoNumbersParams, ListNode?>> = arrayOf(
        TestCase(
            input = AddTwoNumbersParams(
                headA = buildLinkedList(input = "2,4,3"),
                headB = buildLinkedList(input = "5,6,4")
            ),
            output = buildLinkedList("7,0,8")
        ),
        TestCase(
            input = AddTwoNumbersParams(
                headA = buildLinkedList(input = "9,9,9,9,9,9,9"),
                headB = buildLinkedList(input = "9,9,9,9")
            ),
            output = buildLinkedList("8,9,9,9,0,0,0,1")
        )
    )

    override fun solve(testCaseInput: AddTwoNumbersParams): ListNode? {
        return addTwoNumbers(testCaseInput.headA, testCaseInput.headB)
    }

    private fun addTwoNumbers(headA: ListNode?, headB: ListNode?): ListNode? {
        var carry = 0
        var currA = headA
        var currB = headB

        val head = ListNode(0)
        var curr: ListNode? = head

        while (currA != null && currB != null) {
            val sum = currA.`val` + currB.`val` + carry
            val digit = if (sum > 9) sum % 10 else sum
            carry = sum / 10

            curr?.next = ListNode(digit)
            curr = curr?.next

            currA = currA.next
            currB = currB.next
        }

        while (currA != null) {
            val sum = currA.`val` + carry
            val digit = if (sum > 9) sum % 10 else sum
            carry = sum / 10

            curr?.next = ListNode(digit)
            curr = curr?.next

            currA = currA.next
        }

        while (currB != null) {
            val sum = currB.`val` + carry
            val digit = if (sum > 9) sum % 10 else sum
            carry = sum / 10

            curr?.next = ListNode(digit)
            curr = curr?.next

            currB = currB.next
        }

        if (carry != 0) {
            curr?.next = ListNode(carry)
        }

        return head.next
    }
}

data class AddTwoNumbersParams(
    val headA: ListNode?,
    val headB: ListNode?
) {
    override fun toString(): String {
        return """
            
            headA: ${stringFromListNode(headA)}
            headB: ${stringFromListNode(headB)}
        """.trimIndent()
    }
}