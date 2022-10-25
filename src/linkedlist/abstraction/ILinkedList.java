package src.linkedlist.abstraction;

public interface ILinkedList<T> extends ILinkedListInsertion<T>, ILinkedListDeletion<T> {
    void traverse();
    int search(T data);
    boolean isEmpty();
    int size();
}
