package stacks.impl;

import stacks.IStack;

import java.util.LinkedList;

// TODO: refactor this with custom LinkedList
public class StackUsingLinkedList<T> implements IStack<T> {
    LinkedList<T> linkedList;

    public StackUsingLinkedList() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(T item) {
        linkedList.push(item);
    }

    @Override
    public T pop() {
        return linkedList.pop();
    }

    @Override
    public T peek() {
        return linkedList.peek();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = linkedList.size() - 1; i >= 0; i--) {
            sb.append(linkedList.get(i));
            if (i != 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
