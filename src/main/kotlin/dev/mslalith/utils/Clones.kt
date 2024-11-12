package dev.mslalith.utils

import dev.mslalith.linkedlist.ListNode
import dev.mslalith.trees.TreeNode

@Suppress("UNCHECKED_CAST")
fun <T : Any> T.createClone(): T = when (this) {
    is IntArray -> (this as IntArray).clone() as T
    is Array<*> -> (this as Array<*>).createClone() as T
    is ListNode -> this.createClone() as T
    is TreeNode -> this.createClone() as T
    else -> throw UnsupportedOperationException("Type not handled")
}

private fun Array<*>.createClone(): Array<*> = if (isEmpty()) this else when (first()) {
    is IntArray -> Array(size) { (get(it) as IntArray).clone() }
    is CharArray -> Array(size) { (get(it) as CharArray).clone() }
    else -> throw UnsupportedOperationException("Not handled for ${firstOrNull()?.javaClass?.simpleName} type")
}

private fun ListNode.createClone(): ListNode {
    val head = ListNode(`val`)
    var newCurr: ListNode? = head
    var curr = next

    while (curr != null) {
        newCurr?.next = ListNode(curr.`val`)
        newCurr = newCurr?.next
        curr = curr.next
    }

    return head
}

private fun TreeNode.createClone(): TreeNode {
    fun clone(oldNode: TreeNode?): TreeNode? {
        if (oldNode == null) return null
        val newNode = TreeNode(oldNode.`val`)
        newNode.left = clone(oldNode.left)
        newNode.right = clone(oldNode.right)
        return newNode
    }

    return clone(this)!!
}
