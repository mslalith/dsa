package src.trees.traversal

import src.trees.TreeNode

interface TreeTraversal {
    fun preOrderTraversal(root: TreeNode?): List<Int>
    fun inOrderTraversal(root: TreeNode?): List<Int>
    fun postOrderTraversal(root: TreeNode?): List<Int>
}
