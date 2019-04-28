package read;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 *
 * 2
 *
 * 示例 2:
 *
 * 输入:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * 输出:
 *
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */


/**
 * 方法：递归
 * 思路
 *
 * 我们可以将任何路径（具有相同值的节点）看作是最多两个从其根延伸出的箭头。
 *
 * 具体地说，路径的根将是唯一节点，因此该节点的父节点不会出现在该路径中，而箭头将是根在该路径中只有一个子节点的路径。
 *
 * 然后，对于每个节点，我们想知道向左延伸的最长箭头和向右延伸的最长箭头是什么？我们可以用递归来解决这个问题。
 *
 * 算法
 *
 * 令 arrow_length(node) 为从节点 node 延伸出的最长箭头的长度。如果 node.Left 存在且与节点 node 具有相同的值，则该值就会是 1 + arrow_length(node.left)。在 node.right 存在的情况下也是一样。
 *
 * 当我们计算箭头长度时，候选答案将是该节点在两个方向上的箭头之和。我们将这些候选答案记录下来，并返回最佳答案。
 */


public class LongestUnivaluePath {
    static int res;

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode level_1_1 = new TreeNode(4);
        TreeNode level_1_2 = new TreeNode(5);
        TreeNode level_2_1 = new TreeNode(4);
        TreeNode level_2_2 = new TreeNode(4);
        TreeNode level_2_3 = new TreeNode(5);
        root.left = level_1_1;
        root.right = level_1_2;

        level_1_1.left = level_2_1;
        level_1_1.right = level_2_2;
        level_1_2.right = level_2_3;

        res = 0;
        arrowLength(root);
        System.out.println(res);
    }

    /**
     * 复杂度分析
     *
     * 时间复杂度：O(N)O(N)，其中 NN 是树中节点数。我们处理每个节点一次。
     *
     * 空间复杂度：O(H)O(H)，其中 HH 是树的高度。我们的递归调用栈可以达到 HH 层的深度。
     */
    public static int arrowLength(TreeNode node) {
        if (null == node) return 0;

        int left = arrowLength(node.left);
        int right = arrowLength(node.right);

        int arrowLeft = 0, arrowRight = 0;
        if (null != node.left && node.left.val == node.val)
            arrowLeft += left+1;
        if (null != node.right && node.right.val == node.val)
            arrowRight += right+1;

        res = Math.max(res, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    private static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
