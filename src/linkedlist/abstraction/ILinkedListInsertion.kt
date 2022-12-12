package src.linkedlist.abstraction;

public interface ILinkedListInsertion<T> {
    void addFirst(T data);
    void addLast(T data);
    void add(T data, int index);
}
