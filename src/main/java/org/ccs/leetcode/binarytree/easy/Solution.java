/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.binarytree.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        root.left = n1;
        root.right = n2;
        n1.right = n3;
        System.out.println(new Solution().tree2str(root));
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

}
