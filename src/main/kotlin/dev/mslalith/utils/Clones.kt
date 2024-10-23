package dev.mslalith.utils

import dev.mslalith.trees.TreeNode

@Suppress("UNCHECKED_CAST")
fun <T : Any> T.createClone(): T = when (this) {
    is IntArray -> (this as IntArray).clone() as T
    is Array<*> -> (this as Array<*>).createClone() as T
    is TreeNode -> this.createClone() as T
    else -> throw UnsupportedOperationException("Type not handled")
}

private fun Array<*>.createClone(): Array<*> = when (first()) {
    is IntArray -> Array(size) { (get(it) as IntArray).clone() }
    else -> throw UnsupportedOperationException("Not handled for ${firstOrNull()?.javaClass?.simpleName} type")
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
