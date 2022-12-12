package src.trees;

import org.jetbrains.annotations.NotNull;

public class TreeNode implements Comparable<TreeNode> {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    @Override
    public int compareTo(@NotNull TreeNode o) {
        return Integer.compare(val, o.val);
    }
}
