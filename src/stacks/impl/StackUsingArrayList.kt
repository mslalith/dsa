package src.stacks.impl

import src.stacks.IStack

class StackUsingArrayList : IStack {

    private var arrayList = arrayListOf<Int>()

    override fun push(item: Int) {
        arrayList.add(item)
    }

    override fun pop(): Int? {
        val item = arrayList.lastOrNull()
        arrayList.removeLast()
        return item
    }

    override fun peek(): Int? = arrayList.lastOrNull()

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
