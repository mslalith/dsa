package dev.mslalith.linkedlist.abstraction

interface ILinkedListDeletion {
    fun deleteFirst(): Int?
    fun deleteLast(): Int?
    fun delete(data: Int): Int?
}
