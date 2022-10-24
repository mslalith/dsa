package src.trees

import java.util.PriorityQueue

fun buildTreeNode(input: String): TreeNode {
    val numStrings = input.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val numbers = numStrings.map { it.toInt() }
    if (numStrings.size != numbers.size) throw Exception ("Unable to parse input")

    val queue = PriorityQueue<TreeNode>()
    val root = TreeNode(numbers[0])
    queue.add(root)

    var count = 1
    while (count < numbers.size && !queue.isEmpty()) {
        val node = queue.remove()
        numbers[count++].takeIf { it != -1 }?.let { left ->
            node.left = TreeNode(left)
            queue.add(node.left)
        }
        numbers[count++].takeIf { it != -1 }?.let { right ->
            node.right = TreeNode(right)
            queue.add(node.right)
        }
    }

    return root
}