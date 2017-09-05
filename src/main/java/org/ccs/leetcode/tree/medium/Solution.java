/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.ccs.leetcode.bean.TreeLinkNode;
import org.ccs.leetcode.bean.TreeNode;

/**
 * @author Abel created on 2017/7/19 17:12
 * @version $Id$
 */
public class Solution {
    /**
     * 623. Add One Row to Tree
     *
     * <P>
     *
     * https://leetcode.com/problems/add-one-row-to-tree
     * 
     * <p>
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the
     * given depth d. The root node is at depth 1.
     * 
     * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two
     * tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be
     * the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new
     * right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v
     * as the new root of the whole original tree, and the original tree is the new root's left subtree.
     * 
     * Example 1: Input: A binary tree as following: 4 / \ 2 6 / \ / 3 1 5
     * 
     * v = 1
     * 
     * d = 2
     * 
     * Output: 4 / \ 1 1 / \ 2 6 / \ / 3 1 5
     * 
     * Example 2: Input: A binary tree as following: 4 / 2 / \ 3 1
     * 
     * v = 1
     * 
     * d = 3
     * 
     * Output: 4 / 2 / \ 1 1 / \ 3 1 Note: The given d is in range [1, maximum depth of the given tree + 1]. The given
     * binary tree has at least one tree node.
     * </p>
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d <= 1) {
            TreeNode newRoot = new TreeNode(v);
            if (d == 1) {
                newRoot.left = root;
            }
            if (d == 0) {
                newRoot.right = root;
            }
        }
        if (root != null && d >= 2) {
            root.left = addOneRow(root.left, v, d - 1);
        }
        return root;
    }

    /**
     * 144. Binary Tree Preorder Traversal
     * <p>
     * https://leetcode.com/problems/binary-tree-preorder-traversal
     * 
     * <p>
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * 
     * For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [1,2,3].
     * 
     * Note: Recursive solution is trivial, could you do it iteratively?
     * </p>
     * 
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                result.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 94. Binary Tree Inorder Traversal
     *
     * <p>
     * https://leetcode.com/problems/binary-tree-inorder-traversal
     * <p>
     * Given a binary tree, return the inorder traversal of its nodes' values.
     *
     * For example: Given binary tree [1,null,2,3], 1 \ 2 / 3 return [1,3,2].
     *
     * Note: Recursive solution is trivial, could you do it iteratively?
     * </p>
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     *
     * <p>
     * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     *
     * <p>
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     *
     * </p>
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeByInAndPostOrder(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTreeRecursiveByInAndPostOrder(inorder, 0, inorder.length - 1, postorder, 0,
                postorder.length - 1, indexMap);
        return root;
    }

    private TreeNode buildTreeRecursiveByInAndPostOrder(int[] inorder, int inStart, int inEnd, int[] postorder,
            int postStart, int postEnd, Map<Integer, Integer> indexMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);

        int inRoot = indexMap.get(postorder[postEnd]);
        int nodeNums = inRoot - inStart;
        root.left = buildTreeRecursiveByInAndPostOrder(inorder, inStart, inRoot - 1, postorder, postStart,
                postStart + nodeNums - 1, indexMap);
        root.right = buildTreeRecursiveByInAndPostOrder(inorder, inRoot + 1, inEnd, postorder, postStart + nodeNums,
                postEnd - 1, indexMap);
        return root;
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * <p>
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/
     * <p>
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note: You may assume that duplicates do not exist in the tree.
     * </p>
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPreAndInOrder(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTreeRecursiveByPreAndInOrder(preorder, 0, preorder.length - 1, inorder, 0,
                inorder.length - 1, indexMap);
        return root;
    }

    private TreeNode buildTreeRecursiveByPreAndInOrder(int[] preorder, int preStart, int preEnd, int[] inorder,
            int inStart, int inEnd, Map<Integer, Integer> indexMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = indexMap.get(preorder[preStart]);
        int nodenums = inRoot - inStart;
        root.left = buildTreeRecursiveByPreAndInOrder(preorder, preStart + 1, preStart + nodenums, inorder, inStart,
                inRoot - 1, indexMap);
        root.right = buildTreeRecursiveByPreAndInOrder(preorder, preStart + nodenums, preEnd, inorder, inRoot + 1,
                inEnd, indexMap);
        return root;
    }

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * <p>
     * <p>
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * 
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
     * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of
     * itself).”
     * 
     * _______3______
     *
     * / \
     * 
     * ___5__ ___1__
     *
     * / \ / \
     * 
     * 6 _2 0 8
     * 
     * / \
     * 
     * 7 4
     * 
     * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is
     * 5, since a node can be a descendant of itself according to the LCA definition.
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
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public TreeNode lowestCommonAncestorIterate(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        stack.push(root);
        parentMap.put(root, null);
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }

        HashSet<TreeNode> ancestorSet = new HashSet<>();
        while (p != null) {
            ancestorSet.add(p);
            p = parentMap.get(p);
        }
        while (!ancestorSet.contains(q)) {
            q = parentMap.get(q);
        }
        return q;
    }

    /**
     * 116. Populating Next Right Pointers in Each Node
     * <p>
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node
     * <p>
     * 
     * Given a binary tree
     * 
     * struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; } Populate each next pointer
     * to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * 
     * Initially, all next pointers are set to NULL.
     * 
     * Note:
     * 
     * You may only use constant extra space. You may assume that it is a perfect binary tree (ie, all leaves are at the
     * same level, and every parent has two children). For example, Given the following perfect binary tree, 1 / \ 2 3 /
     * \ / \ 4 5 6 7 After calling your function, the tree should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ / \
     * 4->5->6->7 -> NULL
     * </p>
     * 
     * @param root
     */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode node;
        while (pre.left != null) {
            node = pre;
            while (node != null) {
                node.left.next = node.right;
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            pre = pre.left;
        }
    }

    /**
     * 117. Populating Next Right Pointers in Each Node II
     * <p>
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
     * <p>
     * Follow up for problem "Populating Next Right Pointers in Each Node".
     *
     * What if the given tree could be any binary tree? Would your previous solution still work?
     * </p>
     *
     *
     * @param root
     */
    public void connect2(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode layerStart = pre.left != null ? pre.left : pre.right;
        TreeLinkNode cur;

        while (layerStart != null) {

        }
    }

    private TreeLinkNode findNextStart(TreeLinkNode layerStart, TreeLinkNode node) {
        if (layerStart != null) {
            return layerStart;
        }
        return node.left != null ? node.left : node.right;
    }

    /**
     * 114. Flatten Binary Tree to Linked List
     * <p>
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list
     * <p>
     * Given a binary tree, flatten it to a linked list in-place.
     * 
     * For example, Given
     * 
     * 1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like: 1 \ 2 \ 3 \ 4 \ 5 \ 6
     * </p>
     * ???
     *
     * @param root
     */
    public void flattenRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flattenRecursive(left);
        flattenRecursive(right);

        root.right = left;
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        node.right = right;
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null; // dont forget this!!
        }
    }

    /**
     * 96. Unique Binary Search Trees
     * <p>
     * https://leetcode.com/problems/unique-binary-search-trees
     * <p>
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     *
     * For example, Given n = 3, there are a total of 5 unique BST's.
     * </p>
     * https://zh.wikipedia.org/wiki/卡塔兰数
     *
     * http://blog.csdn.net/linhuanmars/article/details/24761459
     *
     * https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
     * 
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                result[i] = result[j] * result[i - j - 1];
            }
        }
        return result[n];
    }

    /**
     * 337. House Robber III
     * <p>
     * https://leetcode.com/problems/house-robber-iii
     * <p>
     * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called
     * the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief
     * realized that "all houses in this place forms a binary tree". It will automatically contact the police if two
     * directly-linked houses were broken into on the same night.
     * 
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     * </p>
     * https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem/2
     * 
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.left != null) {
            res += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            res += rob(root.right.left) + rob(root.right.left);
        }
        return Math.max(res + root.val, rob(root.left) + rob(root.right));
    }

    /**
     * 655. Print Binary Tree
     * <p>
     * https://leetcode.com/problems/print-binary-tree
     * <p>
     *
     * Print a binary tree in an m*n 2D string array following these rules:
     * 
     * 1. The row number m should be equal to the height of the given binary tree.
     * 
     * 2. The column number n should always be an odd number.
     * 
     * 3. The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
     * The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part
     * and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in
     * the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one
     * subtree is none while the other is not, you don't need to print anything for the none subtree but still need to
     * leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to
     * leave space for both of them.
     * 
     * 4. Each unused space should contain an empty string "".
     * 
     * 5. Print the subtrees following the same rules.
     * 
     * </p>
     * 
     * @param root
     * @return
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int height = getHeight(root);
        String[][] matrix = new String[height][(1 << height) - 1];
        for (String[] row : matrix) {
            Arrays.fill(row, "");
        }
        fillByTree(matrix, root, 0, 0, matrix[0].length);
        for (String[] row : matrix) {
            res.add(Arrays.asList(row));
        }
        return res;
    }

    private void fillByTree(String[][] matrix, TreeNode root, int i, int l, int r) {
        if (root == null) {
            return;
        }
        matrix[i][(l + r) / 2] = String.valueOf(root.val);
        fillByTree(matrix, root.left, i + 1, l, (l + r) / 2);
        fillByTree(matrix, root.right, i + 1, (l + r) / 2 + 1, r);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 450. Delete Node in a BST
     * <p>
     * https://leetcode.com/problems/delete-node-in-a-bst
     * <p>
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root
     * node reference (possibly updated) of the BST.
     * 
     * Basically, the deletion can be divided into two stages:
     * 
     * Search for a node to remove. If the node is found, delete the node. Note: Time complexity should be O(height of
     * tree).
     * 
     * Example:
     * 
     * root = [5,3,6,2,4,null,7] key = 3
     * 
     * 5 / \ 3 6 / \ \ 2 4 7
     * 
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     * 
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     * 
     * 5 / \ 4 6 / \ 2 7
     * 
     * Another valid answer is [5,2,6,null,4,null,7].
     * 
     * 5 / \ 2 6 \ \ 4 7
     * </p>
     * 
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 156. Binary Tree Upside Down
     *
     * <p>
     * https://leetcode.com/problems/binary-tree-upside-down
     * <p>
     * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the
     * same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned
     * into left leaf nodes. Return the new root.
     * 
     * For example: Given a binary tree {1,2,3,4,5}, 1 / \ 2 3 / \ 4 5 return the root of the binary tree
     * [4,5,2,#,#,3,1]. 4 / \ 5 2 / \ 3 1 confused what "{1,#,2,3}" means? > read more on how binary tree is serialized
     * on OJ.
     * 
     * 
     * OJ's Binary Tree Serialization: The serialization of a binary tree follows a level order traversal, where '#'
     * signifies a path terminator where no node exists below.
     * 
     * Here's an example: 1 / \ 2 3 / 4 \ 5 The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
     * </p>
     * https://discuss.leetcode.com/topic/40924/java-recursive-o-logn-space-and-iterative-solutions-o-1-space-with-explanation-and-figure
     * 
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.right = null;
        root.left.right = root;
        root.left = null;
        return newRoot;
    }

    /**
     * 230. Kth Smallest Element in a BST
     * <p>
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst
     * <p>
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     * 
     * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     * 
     * Follow up: What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
     * frequently? How would you optimize the kthSmallest routine?
     * 
     * Credits: Special thanks to @ts for adding this problem and creating all test cases.
     * 
     * 
     * </p>
     * 
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                count++;
                if (count == k) {
                    return node.val;
                }
                node = node.right;
            }
        }
        return 0;
    }

    /**
     * 98. Validate Binary Search Tree
     * <p>
     * https://leetcode.com/problems/validate-binary-search-tree
     * <p>
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * 
     * Assume a BST is defined as follows:
     * 
     * The left subtree of a node contains only nodes with keys less than the node's key. The right subtree of a node
     * contains only nodes with keys greater than the node's key. Both the left and right subtrees must also be binary
     * search trees. Example 1: 2 / \ 1 3 Binary tree [2,1,3], return true. Example 2: 1 / \ 2 3 Binary tree [1,2,3],
     * return false.
     * </p>
     * 
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode pre = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (pre != null && pre.val > node.val) {
                    return false;
                }
                pre = node;
                node = node.right;
            }
        }
        return true;
    }

    /**
     * 654. Maximum Binary Tree
     * <p>
     * https://leetcode.com/problems/maximum-binary-tree
     * <p>
     * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
     * 
     * The root is the maximum number in the array. The left subtree is the maximum tree constructed from left part
     * subarray divided by the maximum number. The right subtree is the maximum tree constructed from right part
     * subarray divided by the maximum number. Construct the maximum tree by the given array and output the root node of
     * this tree.
     * 
     * Example 1: Input: [3,2,1,6,0,5] Output: return the tree root node representing the following tree:
     * 
     * 6 / \ 3 5 \ / 2 0 \ 1 Note: The size of the given array will be in the range [1,1000].
     * 
     * </p>
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return buildMaximumTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildMaximumTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxIndex = start;
        for (int i = start; i < end; i++) {
            maxIndex = nums[i] > nums[maxIndex] ? i : maxIndex;
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = buildMaximumTree(nums, start, maxIndex - 1);
        root.right = buildMaximumTree(nums, maxIndex + 1, end);
        return root;
    }

    /**
     * 515. Find Largest Value in Each Tree Row
     * 
     * <p>
     * https://leetcode.com/problems/find-largest-value-in-each-tree-row
     * 
     * <p>
     * You need to find the largest value in each row of a binary tree.
     * 
     * Example: Input:
     * 
     * 1 / \ 3 2 / \ \ 5 3 9
     * 
     * Output: [1, 3, 9]
     * </p>
     * 
     * @param root
     * @return
     */

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int queueSize = 1;
        while (queueSize > 0) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            queueSize = queue.size();
            res.add(max);
        }
        return res;
    }

    /**
     * DFS
     * 
     * @param root
     * @return
     */
    public List<Integer> largestValuesdfs(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res, 0);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res, int d) {
        if (root == null) {
            return;
        }
        // expand list size
        if (d == res.size()) {
            res.add(root.val);
        } else {
            // or set value
            res.set(d, Math.max(res.get(d), root.val));
        }
        helper(root.left, res, d + 1);
        helper(root.right, res, d + 1);
    }

    /**
     * 513. Find Bottom Left Tree Value
     * <p>
     * https://leetcode.com/problems/find-bottom-left-tree-value
     * <p>
     * Given a binary tree, find the leftmost value in the last row of the tree.
     * 
     * Example 1: Input:
     * 
     * 2 / \ 1 3
     * 
     * Output: 1 Example 2: Input:
     * 
     * 1 / \ 2 3 / / \ 4 5 6 / 7
     * 
     * Output: 7 Note: You may assume the tree (i.e., the given root node) is not NULL.
     * 
     * 
     * </p>
     * 
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(10);
        TreeNode l = new TreeNode(5);
        TreeNode r = new TreeNode(15);
        TreeNode rl = new TreeNode(6);
        TreeNode rr = new TreeNode(20);
        root.left = l;
        root.right = r;
        r.left = rl;
        r.right = rr;
        // root.right = new TreeNode(2);
        // root.right.right = new TreeNode(3);
        // System.out.println(solution.deleteNode(root, 2));
        // System.out.println(solution.kthSmallest(root, 1));
        System.out.println(solution.isValidBST(root));
    }
}
