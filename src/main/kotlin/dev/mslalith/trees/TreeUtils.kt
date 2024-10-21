package dev.mslalith.trees

fun buildTreeNode(input: String): TreeNode? {
    val numStrings = input.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val numbers = numStrings.map { it.toIntOrNull() }
    if (numStrings.size != numbers.size) throw Exception("Unable to parse input")

    val queue = ArrayDeque<TreeNode>()
    val root = TreeNode(numbers.getOrNull(0) ?: return null)
    queue.add(root)

    var count = 1
    while (count < numbers.size && !queue.isEmpty()) {
        val node = queue.removeFirst()

        numbers[count++]?.let { left ->
            val treeNode = TreeNode(left)
            node.left = treeNode
            queue.add(treeNode)
        }

        if (count >= numbers.size) break

        numbers[count++]?.let { right ->
            val treeNode = TreeNode(right)
            node.right = treeNode
            queue.add(treeNode)
        }
    }

    return root
}