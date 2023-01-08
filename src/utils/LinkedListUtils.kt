package src.utils

import src.linked_list.ListNode

/**
 * Builders
 */
fun buildLinkedList(input: String, separator: String = ","): ListNode? {
    if (input.isEmpty()) return null
    val split = input.split(separator)
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
fun displayStringFromListNode(head: ListNode?): String = buildString {
    var curr = head
    while (curr != null) {
        append(curr.`val`)
        append(" -> ")
        curr = curr.next
    }
    append("null")
}

fun stringFromListNode(head: ListNode?, separator: String = ","): String = buildString {
    var curr = head
    while (curr != null) {
        append(curr.`val`)
        append(separator)
        curr = curr.next
    }
    deleteCharAt(lastIndex)
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
