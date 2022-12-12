package src.stacks.impl

import src.stacks.IStack

class StackUsingArrayList<T> : IStack<T> {

    private var arrayList = arrayListOf<T>()

    override fun push(item: T) {
        arrayList.add(item)
    }

    override fun pop(): T? {
        val item = arrayList.last()
        arrayList.removeLast()
        return item
    }

    override fun peek(): T? = arrayList.last()

    override fun isEmpty(): Boolean = arrayList.isEmpty()

    override fun toString(): String = buildString {
        append("[")
        for (i in arrayList.indices) {
            append(arrayList[i])
            if (i != arrayList.size - 1) append(", ")
        }
        append("]")
    }
}
