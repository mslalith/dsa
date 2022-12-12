package src.linkedlist.abstraction

interface ILinkedList : ILinkedListInsertion, ILinkedListDeletion {
    fun traverse()
    fun search(data: Int): Int
    fun isEmpty(): Boolean
    fun size(): Int
}
