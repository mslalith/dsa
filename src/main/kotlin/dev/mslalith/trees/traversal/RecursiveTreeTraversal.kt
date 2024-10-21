package dev.mslalith.trees.traversal

import dev.mslalith.trees.TreeNode

class RecursiveTreeTraversal : TreeTraversal {

    override fun preOrderTraversal(root: TreeNode?): List<Int> {
        return preOrderTraversalRec(arrayListOf(), root)
    }

    override fun inOrderTraversal(root: TreeNode?): List<Int> {
        return inOrderTraversalRec(arrayListOf(), root)
    }

    override fun postOrderTraversal(root: TreeNode?): List<Int> {
        return postOrderTraversalRec(arrayListOf(), root)
    }

    private fun preOrderTraversalRec(list: ArrayList<Int>, root: TreeNode?): List<Int> {
        if (root == null) return list
        list.add(root.`val`)
        preOrderTraversalRec(list, root.left)
        preOrderTraversalRec(list, root.right)
        return list
    }

    private fun inOrderTraversalRec(list: ArrayList<Int>, root: TreeNode?): List<Int> {
        if (root == null) return list
        inOrderTraversalRec(list, root.left)
        list.add(root.`val`)
        inOrderTraversalRec(list, root.right)
        return list
    }

    private fun postOrderTraversalRec(list: ArrayList<Int>, root: TreeNode?): List<Int> {
        if (root == null) return list
        postOrderTraversalRec(list, root.left)
        postOrderTraversalRec(list, root.right)
        list.add(root.`val`)
        return list
    }
}
