package src.linkedlist.impl;

import src.linkedlist.abstraction.ILinkedList;

public class ILinkedListDemo {
    public static void main(String[] args) {
        ILinkedList<Integer> iLinkedList = new ILinkedListImpl<>();

        System.out.println("Inserting 4, 5 at the end");
        iLinkedList.addLast(4);
        iLinkedList.addLast(5);

        System.out.println("Inserting 1, 2 at the beginning");
        iLinkedList.addFirst(2);
        iLinkedList.addFirst(1);

        System.out.println("Inserting 3 at index 2");
        iLinkedList.add(3, 2);

        iLinkedList.traverse();
        System.out.println();

        System.out.println("Deleting last item");
        iLinkedList.deleteLast();

        System.out.println("Deleting first item");
        iLinkedList.deleteFirst();

        System.out.println("Deleting 3");
        iLinkedList.delete(3);

        iLinkedList.traverse();

        System.out.println();
        System.out.println("Is empty: " + iLinkedList.isEmpty());

        System.out.println("Searching for 4");
        int index = iLinkedList.search(4);
        System.out.println("Found 4 at index " + index);
        index = iLinkedList.search(2);
        System.out.println("Found 2 at index " + index);


        System.out.println();
        System.out.println("Clearing linked list");
        iLinkedList.deleteFirst();
        iLinkedList.deleteFirst();

        System.out.println("Is empty: " + iLinkedList.isEmpty());
        iLinkedList.traverse();
    }
}
