package src.linkedlist.impl;

import src.linkedlist.ListNode;
import src.linkedlist.abstraction.ILinkedList;

public class ILinkedListImpl<T> implements ILinkedList<T> {

    ListNode<T> head;
    int size;

    public ILinkedListImpl() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addFirst(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        size++;
        if (isEmpty()) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    @Override
    public void addLast(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        size++;
        if (isEmpty()) {
            head = newNode;
            return;
        }

        ListNode<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    @Override
    public void add(T data, int index) {
        if (index == 0) {
            addFirst(data);
            return;
        }

        ListNode<T> newNode = new ListNode<>(data);
        size++;
        if (isEmpty()) {
            head = newNode;
            return;
        }

        ListNode<T> curr = head;
        int count = 0;
        while (index - 1 != count) {
            curr = curr.next;
            count++;
        }
        ListNode<T> oldNext = curr.next;
        curr.next = newNode;
        newNode.next = oldNext;
    }

    @Override
    public T deleteFirst() {
        if (isEmpty()) return null;

        size--;
        T item = head.val;
        head = head.next;
        return item;
    }

    @Override
    public T deleteLast() {
        if (isEmpty()) return null;
        if (head.next == null) return deleteFirst();

        size--;
        ListNode<T> curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }

        T item = curr.val;
        curr.next = null;
        return item;
    }

    @Override
    public T delete(T data) {
        if (isEmpty()) return null;
        if (head.next == null) return deleteFirst();

        size--;
        ListNode<T> curr = head;
        while (curr.next.val != data) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return data;
    }

    @Override
    public int search(T data) {
        if (isEmpty()) return -1;

        ListNode<T> curr = head;

        int count = 0;
        while (curr != null && curr.val != data) {
            curr = curr.next;
            count++;
        }

        return curr != null ? count : -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void traverse() {
        if (isEmpty()) {
            System.out.println("Linked List is empty");
            return;
        }

        System.out.print("(" + size + ") ");
        ListNode<T> curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}
