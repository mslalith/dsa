package src.stacks.impl;

import src.stacks.IStack;

import java.util.ArrayList;

public class StackUsingArrayList<T> implements IStack<T> {
    ArrayList<T> arrayList;

    public StackUsingArrayList() {
        arrayList = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        arrayList.add(item);
    }

    @Override
    public T pop() {
        int lastIndex = arrayList.size() - 1;
        T item = arrayList.get(lastIndex);
        arrayList.remove(lastIndex);
        return item;
    }

    @Override
    public T peek() {
        return arrayList.get(arrayList.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arrayList.size(); i++) {
            sb.append(arrayList.get(i));
            if (i != arrayList.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
