package src.stacks

import src.stacks.impl.StackUsingArrayList
import src.stacks.impl.StackUsingLinkedList

object IStackDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        println("======= Stack using ArrayList =======")
        baseDemo(StackUsingArrayList())
        println()

        println()
        println("======= Stack using LinkedList =======")
        baseDemo(StackUsingLinkedList())
        println()
        println()
    }

    private fun baseDemo(iStack: IStack<Int?>) {
        val end = 5
        println("Pushing 1 till $end to stack")
        for (i in 1..end) {
            iStack.push(i)
            println("Stack: $iStack")
        }

        println()
        println("Popping till stack is empty")
        while (!iStack.isEmpty()) {
            val item = iStack.peek()!!
            iStack.pop()
            println("Popped: $item")
            println("Stack: $iStack")
        }
    }
}
