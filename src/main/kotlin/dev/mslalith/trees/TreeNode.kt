package dev.mslalith.trees

data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
) : Comparable<TreeNode> {

    override fun compareTo(other: TreeNode): Int {
        return `val`.compareTo(other.`val`)
    }
}
