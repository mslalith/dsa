package dev.mslalith.trees.traversal

import dev.mslalith.trees.TreeNode

interface TreeTraversal {
    fun preOrderTraversal(root: TreeNode?): List<Int>
    fun inOrderTraversal(root: TreeNode?): List<Int>
    fun postOrderTraversal(root: TreeNode?): List<Int>
}
