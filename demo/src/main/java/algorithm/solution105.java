package algorithm;

import java.util.HashMap;

class Solution105 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Solution {
        HashMap<Integer, Integer> valToIndex;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            valToIndex = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                valToIndex.put(inorder[i], i);
            }
            return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd)
                return null;

            int rootVal = preorder[preStart];
            int index = valToIndex.get(rootVal);
            int leftSize = index - inStart;

            TreeNode root = new TreeNode(rootVal);
            root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
            root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
            return root;
        }
    }

    public static void main(String[] args) {
        // 创建 Solution 对象
        Solution solution = new Solution();

        // 构造测试用例
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        // 调用 buildTree 方法构建二叉树
        TreeNode root = solution.buildTree(preorder, inorder);

        // 直接返回根节点（按照题目要求输出）
        System.out.println(root);
    }
}