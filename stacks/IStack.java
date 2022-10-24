package stacks;

public interface IStack<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
}
