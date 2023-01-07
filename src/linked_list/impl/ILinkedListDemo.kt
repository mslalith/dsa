package src.linked_list.impl

import src.linked_list.abstraction.ILinkedList

object ILinkedListDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val iLinkedList: ILinkedList = ILinkedListImpl()

        println("Inserting 4, 5 at the end")
        iLinkedList.addLast(4)
        iLinkedList.addLast(5)

        println("Inserting 1, 2 at the beginning")
        iLinkedList.addFirst(2)
        iLinkedList.addFirst(1)

        println("Inserting 3 at index 2")
        iLinkedList.add(3, 2)
        iLinkedList.traverse()
        println()

        println("Deleting last item")
        iLinkedList.deleteLast()
        println("Deleting first item")
        iLinkedList.deleteFirst()
        println("Deleting 3")
        iLinkedList.delete(3)
        iLinkedList.traverse()
        println()

        println("Is empty: " + iLinkedList.isEmpty())
        println("Size: " + iLinkedList.size())
        println()

        println("Searching for 4")
        var index = iLinkedList.search(4)
        println("Found 4 at index $index")
        index = iLinkedList.search(2)
        println("Found 2 at index $index")
        println()

        println("Clearing linked list")
        iLinkedList.deleteFirst()
        iLinkedList.deleteFirst()
        println("Is empty: " + iLinkedList.isEmpty())
        println("Size: " + iLinkedList.size())
        iLinkedList.traverse()
    }
}
