/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.ccs.leetcode.bean.TreeNode;

/**
 * @author Abel created on 2017/7/7 11:47
 * @version $Id$
 */
public class Solution {

    /**
     * 606. Construct String from Binary Tree
     * <p>
     * https://leetcode.com/problems/construct-string-from-binary-tree
     * <p>
     * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder
     * traversing way.
     * 
     * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty
     * parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original
     * binary tree.
     * 
     * Example 1: Input: Binary tree: [1,2,3,4] 1 / \ 2 3 / 4
     * 
     * Output: "1(2(4))(3)"
     * 
     * Explanation: Originallay it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty
     * parenthesis pairs. And it will be "1(2(4))(3)". Example 2: Input: Binary tree: [1,2,3,null,4] 1 / \ 2 3 \ 4
     * 
     * Output: "1(2()(4))(3)"
     * 
     * Explanation: Almost the same as the first example, except we can't omit the first parenthesis pair to break the
     * one-to-one mapping relationship between the input and the output.
     * </p>
     * 
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        String leftStr;
        String rightStr;
        if (t.left == null && t.right == null) {
            return String.valueOf(t.val);
        }
        leftStr = tree2str(t.left);
        rightStr = tree2str(t.right);
        return t.val + (t.left == null ? "()" : "(" + leftStr + ")") + (t.right == null ? "" : "(" + rightStr + ")");
    }

    /**
     * 226 Invert Binary Tree
     *
     * <p>
     * https://leetcode.com/problems/invert-binary-tree
     *
     * @param root
     * @return
     */

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 104 Maximum Depth of Binary Tree
     * <p>
     * https://leetcode.com/problems/maximum-depth-of-binary-tree
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int maxDepth = 1;
        int leftDepth = 0;
        if (root.left != null) {
            leftDepth = maxDepth(root.left);
        }
        int rightDepth = 0;
        if (root.right != null) {
            rightDepth = maxDepth(root.right);
        }
        if (leftDepth > rightDepth) {
            return maxDepth + leftDepth;
        } else {
            return maxDepth + rightDepth;
        }
    }

    /**
     * 100. Same Tree QuestionEditorial
     * <p>
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     * </p>
     * 
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            boolean leftSame = isSameTree(p.left, q.left);
            boolean rightSame = isSameTree(p.right, q.right);
            return leftSame && rightSame;
        }
        return false;
    }

    /**
     * 637. Average of Levels in Binary Tree
     * <p>
     * https://leetcode.com/problems/average-of-levels-in-binary-tree
     *
     * <p>
     * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
     *
     * Example 1: Input: 3 / \ 9 20 / \ 15 7 Output: [3, 14.5, 11] Explanation: The average value of nodes on level 0 is
     * 3, on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11]. Note: The range of node's value is in
     * the range of 32-bit signed integer.
     * </p>
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();

        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()) {
            double sum = 0.0;
            int size = treeNodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodeQueue.poll();
                sum += node.val;
                if (node.left != null) {
                    treeNodeQueue.offer(node.left);
                }
                if (node.right != null) {
                    treeNodeQueue.offer(node.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    /**
     * 107. Binary Tree Level Order Traversal II
     * 
     * DFS Solution
     * 
     * <p>
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii
     * <p>
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right,
     * level by level from leaf to root).
     * 
     * For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7
     * 
     * return its bottom-up level order traversal as: [ [15,7], [9,20], [3] ]
     * </p>
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(0, list);
        }
        return result;
    }

    /**
     * BFS Solution
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomBFS(TreeNode root) {
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null)
            return;
        if (level >= list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level + 1);
        levelMaker(list, root.right, level + 1);
        list.get(list.size() - level - 1).add(root.val);
    }

    /**
     * 102. Binary Tree Level Order Traversal
     * <p>
     * https://leetcode.com/problems/binary-tree-level-order-traversal
     * <p>
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by
     * level).
     * 
     * For example: Given binary tree [3,9,20,null,null,15,7], 3 / \ 9 20 / \ 15 7 return its level order traversal as:
     * [ [3], [9,20], [15,7] ]
     * </p>
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 111. Minimum Depth of Binary Tree
     * <p>
     * https://leetcode.com/problems/minimum-depth-of-binary-tree
     * <p>
     * Given a binary tree, find its minimum depth.
     * 
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf
     * node.
     * 
     * 
     * </p>
     * 
     * @param root
     * @return
     */
    public int minDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }

    public int minDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepthRecursive(root.left);
        int right = minDepthRecursive(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    /**
     * 222. Count Complete Tree Nodes
     * 
     * <p>
     * https://leetcode.com/problems/count-complete-tree-nodes
     * <p>
     * Given a complete binary tree, count the number of nodes.
     * 
     * Definition of a complete binary tree from Wikipedia:
     * https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees
     * 
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
     * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     * </p>
     * 
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int num = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                num++;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return num;
    }

    /**
     * 572. Subtree of Another Tree
     * <p>
     * https://leetcode.com/problems/subtree-of-another-tree/description/
     * <p>
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values
     * with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The
     * tree s could also be considered as a subtree of itself.
     * 
     * Example 1: Given tree s:
     * 
     * 3 / \ 4 5 / \ 1 2 Given tree t: 4 / \ 1 2 Return true, because t has the same structure and node values with a
     * subtree of s.
     * 
     * Example 2: Given tree s:
     * 
     * 3 / \ 4 5 / \ 1 2 / 0 Given tree t: 4 / \ 1 2 Return false.
     * </p>
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtreeDFS(TreeNode s, TreeNode t) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        treeNodeQueue.add(s);
        while (!treeNodeQueue.isEmpty()) {
            int size = treeNodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodeQueue.poll();
                if (isSameTree(node, t)) {
                    return true;
                }
                if (node.left != null) {
                    treeNodeQueue.offer(node.left);
                }
                if (node.right != null) {
                    treeNodeQueue.offer(node.right);
                }
            }
        }
        return false;
    }

    /**
     * 563. Binary Tree Tilt
     * <p>
     * https://leetcode.com/problems/binary-tree-tilt
     * <p>
     * Given a binary tree, return the tilt of the whole tree.
     * 
     * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and
     * the sum of all right subtree node values. Null node has tilt 0.
     * 
     * The tilt of the whole tree is defined as the sum of all nodes' tilt.
     * 
     * Example:
     * 
     * Input: 1 / \ 2 3
     * 
     * Output: 1
     * 
     * Explanation:
     * 
     * Tilt of node 2 : 0
     * 
     * Tilt of node 3 : 0
     * 
     * Tilt of node 1 : |2-3| = 1
     * 
     * Tilt of binary tree : 0 + 0 + 1 = 1
     * 
     * Note:
     * 
     * The sum of node values in any subtree won't exceed the range of 32-bit integer. All the tilt values won't exceed
     * the range of 32-bit integer.
     * </p>
     * 
     * @param root
     * @return
     */
    public int findTiltPreOrder(TreeNode root) {
        /* Time */
        if (root == null || root.left == null && root.right == null) {
            return 0;
        }
        int sum = 0;
        int leftSum = calNodeSum(root.left);
        int rightSum = calNodeSum(root.right);
        sum += Math.abs(leftSum - rightSum);
        sum += findTiltPreOrder(root.left);
        sum += findTiltPreOrder(root.right);
        return sum;
    }

    /**
     *
     * @param node
     * @return
     */
    public int calNodeSum(TreeNode node) {
        int sum = 0;
        if (node == null) {
            return 0;
        }
        sum += (node.val + calNodeSum(node.left) + calNodeSum(node.right));
        return sum;
    }

    /* using variable PostOrder */
    int tilt = 0;

    public int findTiltPostOrder(TreeNode root) {
        postOrder(root);
        return tilt;
    }

    private int postOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = postOrder(root.left);
        int right = postOrder(root.right);

        tilt += Math.abs(left - right);

        return left + right + root.val;
    }

    /**
     * 101. Symmetric Tree
     * <p>
     * https://leetcode.com/problems/symmetric-tree
     * <p>
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * 
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     * 
     * 1 / \ 2 2 / \ / \ 3 4 4 3 But the following [1,2,2,null,3,null,3] is not: 1 / \ 2 2 \ \ 3 3 Note: Bonus points if
     * you could solve it both recursively and iteratively.
     * </p>
     * 
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val) && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
    }

    public boolean isSymmetricIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null)
                continue;
            if (t1 == null || t2 == null)
                return false;
            if (t1.val != t2.val)
                return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * <p>
     * <p>
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     * 
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
     * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of
     * itself).”
     * 
     * _______6______
     * 
     * 
     * / \
     *
     * ___2__ ___8__
     *
     * / \ / \
     *
     * 0 _4 7 9
     *
     * / \
     *
     * 3 5
     * 
     * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is
     * 2, since a node can be a descendant of itself according to the LCA definition.
     * </p>
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = root.val > p.val ? root.left : root.right;
        }
        return root;
    }

    /**
     * 538. Convert BST to Greater Tree
     * <p>
     * https://leetcode.com/problems/convert-bst-to-greater-tree
     * <p>
     * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed
     * to the original key plus sum of all keys greater than the original key in BST.
     * 
     * Example:
     * 
     * Input: The root of a Binary Search Tree like this: 5 / \ 2 13
     * 
     * Output: The root of a Greater Tree like this: 18 / \ 20 13
     * </p>
     * 
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        dfsSum(root, 0);
        return root;
    }

    private int dfsSum(TreeNode root, int value) {
        if (root == null) {
            return value;
        }
        int right = dfsSum(root.right, value);
        int left = dfsSum(root.left, root.val + value);
        root.val = root.val + right;
        return left;
    }

    /**
     * 110. Balanced Binary Tree
     * <p>
     * https://leetcode.com/problems/balanced-binary-tree
     * <p>
     * Given a binary tree, determine if it is height-balanced.
     * 
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     * </p>
     * 
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int heightLeft = getHeight(root.left);
        int heightRight = getHeight(root.right);
        if (Math.abs(heightLeft - heightRight) > 1) {
            return false;
        }
        boolean isBalancedLeft = isBalanced(root.left);
        boolean isBalancedRight = isBalanced(root.right);
        return isBalancedLeft && isBalancedRight;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }

    /**
     * 112. Path Sum
     * <p>
     * https://leetcode.com/problems/path-sum
     *
     * Tree DFS
     * <p>
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
     * along the path equals the given sum.
     * 
     * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ \ 7 2 1 return true, as there
     * exist a root-to-leaf path 5->4->11->2 which sum is 22.
     * </p>
     * 
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * <p>
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree
     * <p>
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     * 
     * </p>
     * 
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int pos = nums.length / 2;
        TreeNode root = new TreeNode(nums[pos]);
        root.left = convertArrayToBST(nums, 0, pos - 1);
        root.right = convertArrayToBST(nums, pos + 1, nums.length - 1);
        return root;

    }

    private TreeNode convertArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int pos = (start + end) / 2;
        TreeNode root = new TreeNode(nums[pos]);
        root.left = convertArrayToBST(nums, start, pos - 1);
        root.right = convertArrayToBST(nums, pos + 1, end);
        return root;
    }

    /**
     * 257. Binary Tree Paths
     * <p>
     * https://leetcode.com/problems/binary-tree-paths
     * <p>
     * Given a binary tree, return all root-to-leaf paths.
     * 
     * For example, given the following binary tree:
     * 
     * 1
     * 
     * / \
     * 
     * 2 3
     * 
     * \
     * 
     * 5
     * 
     * All root-to-leaf paths are:
     * 
     * ["1->2->5", "1->3"] Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test
     * cases.
     * </p>
     * 
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> pathList = new ArrayList<>();
        if (root == null) {
            return pathList;
        }
        genPaths("", root, pathList);
        return pathList;
    }

    private void genPaths(String preFix, TreeNode root, List<String> pathList) {
        if (root.left == null && root.right == null) {
            pathList.add(preFix + (!"".equals(preFix) ? "->" : "") + root.val);
            return;
        }
        if (root.left != null) {
            genPaths(preFix + (!"".equals(preFix) ? "->" : "") + String.valueOf(root.val), root.left, pathList);
        }
        if (root.right != null) {
            genPaths(preFix + (!"".equals(preFix) ? "->" : "") + String.valueOf(root.val), root.right, pathList);
        }

    }

    /**
     * 543. Diameter of Binary Tree
     * <p>
     * https://leetcode.com/problems/diameter-of-binary-tree
     * <p>
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
     * the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * </p>
     * 
     * @param root
     * @return
     */
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepthDiameter(root);
        return maxDiameter;
    }

    private int maxDepthDiameter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepthDiameter(root.left);
        int right = maxDepthDiameter(root.right);
        maxDiameter = Math.max(left + right, maxDiameter);
        return Math.max(left, right) + 1;
    }

    public int diameterOfBinaryTreeLayer(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int value = getHeight(node.left) + getHeight(node.right);
            max = Math.max(value, max);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return max;
    }

    /**
     * 653. Two Sum IV - Input is a BST
     * <p>
     * https://leetcode.com/problems/two-sum-iv-input-is-a-bst
     * <p>
     * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that
     * their sum is equal to the given target.
     * 
     * </p>
     * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/solution/#approach-1-using-hashsetaccepted
     * 
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);
        int low = 0, high = values.size() - 1;
        while (low < high) {
            int sum = values.get(low) + values.get(high);
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                low++;
            }
            if (sum > k) {
                high--;
            }
        }
        return false;
    }

    private void inOrder(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        inOrder(root.left, values);
        values.add(root.val);
        inOrder(root.right, values);
    }

    /**
     * 501. Find Mode in Binary Search Tree
     * <p>
     * https://leetcode.com/problems/find-mode-in-binary-search-tree
     * <p>
     * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in
     * the given BST.
     * 
     * Assume a BST is defined as follows:
     * 
     * The left subtree of a node contains only nodes with keys less than or equal to the node's key. The right subtree
     * of a node contains only nodes with keys greater than or equal to the node's key. Both the left and right subtrees
     * must also be binary search trees. For example: Given BST [1,null,2,2], 1 \ 2 / 2 return [2].
     * 
     * Note: If a tree has more than one mode, you can return them in any order.
     * 
     * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to
     * recursion does not count).
     * </p>
     * https://discuss.leetcode.com/category/650/find-mode-in-binary-search-tree
     * 
     * @param root
     * @return
     * 
     */
    private int maxCount = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        inOrderCountTravel(root, map);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCount) {
                list.add(entry.getKey());
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void inOrderCountTravel(TreeNode root, HashMap<Integer, Integer> map) {
        if (root.left != null) {
            inOrderCountTravel(root.left, map);
        }
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        maxCount = Math.max(map.get(root.val), maxCount);
        if (root.right != null) {
            inOrderCountTravel(root.right, map);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        root.left = node1;
        node1.right = node3;
        root.right = node2;
        System.out.println(new Solution().binaryTreePaths(root));
    }

}
