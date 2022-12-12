package src.stacks

interface IStack {
    fun push(item: Int)
    fun pop(): Int?
    fun peek(): Int?
    fun isEmpty(): Boolean
}
