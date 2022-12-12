package src.stacks.impl

import src.linkedlist.ListNode
import src.stacks.IStack

class StackUsingLinkedList<T> : IStack<T> {

    private var head: ListNode<T>? = null

    override fun push(item: T) {
        val newNode = ListNode(item)
        if (isEmpty()) {
            head = newNode
            return
        }
        newNode.next = head
        head = newNode
    }

    override fun pop(): T? {
        if (isEmpty()) return null
        val item = head?.`val`
        head = head?.next
        return item
    }

    override fun peek(): T? = if (isEmpty()) null else head?.`val`

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
