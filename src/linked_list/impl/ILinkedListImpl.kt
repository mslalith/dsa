package src.linked_list.impl

import src.linked_list.ListNode
import src.linked_list.abstraction.ILinkedList

class ILinkedListImpl : ILinkedList {

    private var head: ListNode? = null
    private var size = 0

    override fun addFirst(data: Int) {
        val newNode = ListNode(data)
        size++

        if (isEmpty()) {
            head = newNode
            return
        }

        newNode.next = head
        head = newNode
    }

    override fun addLast(data: Int) {
        val newNode = ListNode(data)
        size++

        if (isEmpty()) {
            head = newNode
            return
        }

        var curr = head
        while (curr?.next != null) {
            curr = curr.next
        }
        curr?.next = newNode
    }

    override fun add(data: Int, index: Int) {
        if (index == 0) {
            addFirst(data)
            return
        }

        val newNode = ListNode(data)
        size++

        if (isEmpty()) {
            head = newNode
            return
        }

        var curr = head
        var count = 0
        while (index - 1 != count) {
            curr = curr?.next
            count++
        }
        val oldNext = curr?.next
        curr?.next = newNode
        newNode.next = oldNext
    }

    override fun deleteFirst(): Int? {
        if (isEmpty()) return null
        size--

        val item = head?.`val`
        head = head?.next
        return item
    }

    override fun deleteLast(): Int? {
        if (isEmpty()) return null
        if (head?.next == null) return deleteFirst()

        size--
        var curr = head
        while (curr?.next?.next != null) {
            curr = curr.next
        }

        val item = curr?.`val`
        curr?.next = null
        return item
    }

    override fun delete(data: Int): Int? {
        if (isEmpty()) return null
        if (head?.next == null) return deleteFirst()

        size--
        var curr = head
        while (curr?.next?.`val` != data) {
            curr = curr?.next
        }
        curr.next = curr.next?.next
        return data
    }

    override fun search(data: Int): Int {
        if (isEmpty()) return -1

        var curr = head
        var count = 0
        while (curr != null && curr.`val` != data) {
            curr = curr.next
            count++
        }

        return if (curr != null) count else -1
    }

    override fun isEmpty(): Boolean = head == null

    override fun size(): Int = size

    override fun traverse() {
        if (isEmpty()) {
            println("Linked List is empty")
            return
        }

        print("($size) ")
        var curr = head
        while (curr != null) {
            print(curr.`val`.toString() + " -> ")
            curr = curr.next
        }
        println("null")
    }
}
