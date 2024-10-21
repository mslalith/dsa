package dev.mslalith.stacks.impl

import dev.mslalith.linkedlist.ListNode
import dev.mslalith.stacks.IStack

class StackUsingLinkedList : IStack {

    private var head: ListNode? = null

    override fun push(item: Int) {
        val newNode = ListNode(item)

        if (isEmpty()) {
            head = newNode
            return
        }

        newNode.next = head
        head = newNode
    }

    override fun pop(): Int? {
        if (isEmpty()) return null

        val item = head?.`val`
        head = head?.next
        return item
    }

    override fun peek(): Int? = if (isEmpty()) null else head?.`val`

    override fun isEmpty(): Boolean = head == null

    override fun toString(): String = buildString {
        var curr = head
        if (curr != null) {
            append(curr.`val`)
            curr = curr.next
            while (curr != null) {
                insert(0, curr.`val`.toString() + ", ")
                curr = curr.next
            }
        }
        insert(0, "[")
        append("]")
    }
}
