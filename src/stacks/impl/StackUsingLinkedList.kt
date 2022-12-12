package src.stacks.impl;

import src.linkedlist.ListNode;
import src.stacks.IStack;

public class StackUsingLinkedList<T> implements IStack<T> {
    ListNode<T> head;

    public StackUsingLinkedList() {
        this.head = null;
    }

    @Override
    public void push(T item) {
        ListNode<T> newNode = new ListNode<>(item);
        if (isEmpty()) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;

        T item = head.val;
        head = head.next;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) return null;
        return head.val;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        ListNode<T> curr = head;
        if (curr != null) {
            sb.append(curr.val);
            curr = curr.next;
            while (curr != null) {
                sb.insert(0, curr.val + ", ");
                curr = curr.next;
            }
        }

        sb.insert(0, "[");
        sb.append("]");
        return sb.toString();
    }
}
