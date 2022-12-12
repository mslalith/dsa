package src.utils

import src.linkedlist.ListNode

/**
 * Builders
 */
fun buildLinkedList(input: String): ListNode? {
    val split = input.split(" ")
    var head: ListNode? = null
    var curr = head
    split.map { it.toInt() }.forEach {
        if (head == null) {
            head = ListNode(it)
            curr = head
            return@forEach
        }
        curr?.next = ListNode(it)
        curr = curr?.next
    }
    return head
}

/**
 * Display
 */
fun stringFromListNode(head: ListNode?): String = buildString {
    var curr = head
    while (curr != null) {
        append(curr.`val`)
        append(" -> ")
        curr = curr.next
    }
    append("null")
}

/**
 * Display
 */
fun areListNodesEqual(first: ListNode?, second: ListNode?): Boolean {
    if (first == null && second == null) return true
    if (first == null || second == null) return false

    var currOne = first
    var currTwo = second
    while (currOne != null && currTwo != null && currOne.`val` == currTwo.`val`) {
        currOne = currOne.next
        currTwo = currTwo.next
    }
    return currOne == null && currTwo == null
}
