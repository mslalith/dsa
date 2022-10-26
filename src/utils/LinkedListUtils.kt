package src.utils

import src.linkedlist.ListNode

/**
 * Builders
 */
fun buildLinkedListFromInt(input: String) = buildLinkedList(input) { it.toInt() }

fun <T> buildLinkedList(input: String, transform: (String) -> T): ListNode<T> {
    val split = input.split(" ")
    var head: ListNode<T>? = null
    var curr = head
    split.map(transform).forEach {
        if (head == null) {
            head = ListNode(it)
            curr = head
            return@forEach
        }
        curr?.next = ListNode(it)
        curr = curr?.next
    }
    return head!!
}

/**
 * Display
 */
fun <T> stringFromListNode(head: ListNode<T>?): String = buildString {
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
fun <T> areListNodesEqual(first: ListNode<T>?, second: ListNode<T>?): Boolean {
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
