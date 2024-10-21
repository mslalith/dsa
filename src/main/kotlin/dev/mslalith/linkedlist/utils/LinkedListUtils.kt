package dev.mslalith.linkedlist.utils

import dev.mslalith.linkedlist.ListNode

fun reverseLinkedList(head: ListNode?): ListNode? {
    if (head == null) return null

    var prev: ListNode? = null
    var curr = head

    while (curr != null) {
        val next = curr.next
        curr.next = prev
        prev = curr
        curr = next
    }
    return prev
}
