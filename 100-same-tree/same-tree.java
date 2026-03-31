/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both null → same
        if (p == null && q == null) return true;
        
        // One null or values differ → not same
        if (p == null || q == null || p.val != q.val) return false;
        
        // Check left and right
        return isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right);
    }
}
