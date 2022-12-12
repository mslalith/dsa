package src.stacks

interface IStack<T> {
    fun push(item: T)
    fun pop(): T?
    fun peek(): T?
    fun isEmpty(): Boolean
}
