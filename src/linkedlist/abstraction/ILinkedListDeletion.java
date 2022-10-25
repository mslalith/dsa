package src.linkedlist.abstraction;

public interface ILinkedListDeletion<T> {
    T deleteFirst();
    T deleteLast();
    T delete(T data);
}
