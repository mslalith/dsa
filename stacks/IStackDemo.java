package stacks;

import stacks.impl.StackUsingArrayList;
import stacks.impl.StackUsingLinkedList;

public class IStackDemo {
    public static void main(String[] args) {
        System.out.println("======= Stack using ArrayList =======");
        baseDemo(new StackUsingArrayList<>());
        System.out.println();
        System.out.println();

        System.out.println("======= Stack using LinkedList =======");
        baseDemo(new StackUsingLinkedList<>());
        System.out.println();
        System.out.println();
    }

    static void baseDemo(IStack<Integer> iStack) {
        int end = 5;
        System.out.println("Pushing 1 till " + end + " to stack");
        for (int i = 1; i <= end; i++) {
            iStack.push(i);
            System.out.println("Stack: " + iStack);
        }

        System.out.println();
        System.out.println("Popping till stack is empty");
        while (!iStack.isEmpty()) {
            int item = iStack.peek();
            iStack.pop();
            System.out.println("Popped: " + item);
            System.out.println("Stack: " + iStack);
        }
    }
}
