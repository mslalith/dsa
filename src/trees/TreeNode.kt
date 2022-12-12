package src.trees

data class TreeNode(
    val `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
) : Comparable<TreeNode> {

    override fun compareTo(other: TreeNode): Int {
        return `val`.compareTo(other.`val`)
    }
}