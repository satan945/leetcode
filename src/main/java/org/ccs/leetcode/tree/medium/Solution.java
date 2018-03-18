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
     * design TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; } Populate each next pointer
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
        TreeLinkNode pre = null;
        TreeLinkNode head = null;
        TreeLinkNode cur = root;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            head = null;
            pre = null;
        }
    }

    public void connect3(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode tempChild = new TreeLinkNode(0);
            TreeLinkNode currentChild = tempChild;
            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
        }
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
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(node.val, max);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
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
        int[] res = new int[] { 0, 0 };
        return findBottomLeftValueLevel(root, 1, res);
    }

    private int findBottomLeftValueLevel(TreeNode root, int depth, int[] res) {
        if (depth > res[1]) {
            res[0] = root.val;
            res[1] = depth;
        }
        if (root.left != null) {
            findBottomLeftValueLevel(root.left, depth + 1, res);
        }
        if (root.right != null) {
            findBottomLeftValueLevel(root.right, depth + 1, res);
        }
        return res[0];
    }

    /**
     * 250. Count Univalue Subtrees
     * <p>
     * https://leetcode.com/problems/count-univalue-subtrees
     * <p>
     * Given a binary tree, count the number of uni-value subtrees.
     * 
     * A Uni-value subtree means all nodes of the subtree have the same value.
     * 
     * For example: Given binary tree, 5 / \ 1 5 / \ \ 5 5 5 return 4.
     * 
     * </p>
     * 
     * @param root
     * @return
     */

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = new int[1];
        countUniTree(root, count);
        return count[0];
    }

    private boolean countUniTree(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }
        boolean left = countUniTree(root.left, count);
        boolean right = countUniTree(root.right, count);
        if (left && right) {
            if (root.left != null && root.val != root.left.val) {
                return false;
            }
            if (root.right != null && root.val != root.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }

    /**
     * 199. Binary Tree Right Side View
     * <p>
     * https://leetcode.com/problems/binary-tree-right-side-view
     * <p>
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can
     * see ordered from top to bottom.
     * 
     * org.ccs.leetcode.dfs.medium.Solution.rightSideView()
     *
     * org.ccs.leetcode.bfs.medium.Solution.rightSideView()
     * 
     * For example: Given the following binary tree, 1 <--- / \ 2 3 <--- \ \ 5 4 <---
     * </p>
     * 
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    /**
     * 113. Path Sum II
     * <p>
     * https://leetcode.com/problems/path-sum-ii
     * <p>
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     *
     * For example: Given the below binary tree and sum = 22, 5 / \ 4 8 / / \ 11 13 4 / \ / \ 7 2 5 1 return [
     * [5,4,11,2], [5,8,4,5] ]
     * </p>
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        // org.ccs.leetcode.dfs.medium.Solution.pathSum()
        return null;
    }

    /**
     * 222. Count Complete Tree Nodes
     * <p>
     * https://leetcode.com/problems/count-complete-tree-nodes
     * <p>
     * Given a complete binary tree, count the number of nodes.
     * 
     * Definition of a complete binary tree from
     * Wikipedia(https://en.wikipedia.org/wiki/Binary_tree#Types_of_binary_trees):
     * 
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
     * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     * </p>
     * 
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0
                : height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                        : (1 << h - 1) + countNodes(root.left);
    }

    private int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    /**
     * 129. Sum Root to Leaf Numbers
     * <p>
     * https://leetcode.com/problems/sum-root-to-leaf-numbers
     * <p>
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     * 
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     * 
     * Find the total sum of all root-to-leaf numbers.
     * 
     * For example,
     * 
     * 1 / \ 2 3 The root-to-leaf path 1->2 represents the number 12. The root-to-leaf path 1->3 represents the number
     * 13.
     * 
     * Return the sum = 12 + 13 = 25.
     * </p>
     * 
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> numList = new ArrayList<>();
        TreeNode node = root;
        genAllRootToLeaf(node, numList, 0);
        int res = 0;
        for (int num : numList) {
            res += num;
        }
        return res;

    }

    private void genAllRootToLeaf(TreeNode node, List<Integer> numList, int num) {
        num = num * 10 + node.val;
        if (node.left == null && node.right == null) {
            numList.add(num);
            return;
        }
        if (node.left != null) {
            genAllRootToLeaf(node.left, numList, num);
        }
        if (node.right != null) {
            genAllRootToLeaf(node.right, numList, num);
        }
    }

    /**
     * 255. Verify Preorder Sequence in Binary Search Tree
     * <p>
     * https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree
     * <p>
     * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
     * 
     * You may assume each number in the sequence is unique.
     * </p>
     * 
     * @param preorder
     * @return
     */
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack<>();
        for (int p : preorder) {
            if (p < low) {
                return false;
            }
            while (!path.empty() && p > path.peek()) {
                low = path.pop();
            }
            path.push(p);
        }
        return true;
    }

    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * <p>
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
     * <p>
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then
     * right to left for the next level and alternate between).
     * 
     * </p>
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        travelZigZag(root, 0, res);
        return res;
    }

    private void travelZigZag(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (res.size() <= level) {
            List<Integer> list = new LinkedList<>();
            res.add(list);
        }
        List<Integer> targetLevel = res.get(level);
        if (level % 2 == 0) {
            targetLevel.add(root.val);
        } else {
            targetLevel.add(0, root.val);
        }
        travelZigZag(root.left, level + 1, res);
        travelZigZag(root.right, level + 1, res);
    }

    /**
     * 95. Unique Binary Search Trees II
     * <p>
     * https://leetcode.com/problems/unique-binary-search-trees-ii
     * <p>
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     * </p>
     * 
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return buildBST(1, n);
    }

    private List<TreeNode> buildBST(int left, int right) {
        List<TreeNode> list = new ArrayList<>();
        if (left > right) {
            list.add(null);
            return list;
        }
        if (left == right) {
            list.add(new TreeNode(left));
            return list;
        }
        List<TreeNode> leftSub;
        List<TreeNode> rightSub;
        for (int i = left; i <= right; i++) {
            leftSub = buildBST(left, i - 1);
            rightSub = buildBST(i + 1, right);
            for (TreeNode leftNode : leftSub) {
                for (TreeNode rightNode : rightSub) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    list.add(root);
                }
            }
        }
        return list;
    }

    /**
     * 285. Inorder Successor in BST
     * <p>
     * https://leetcode.com/problems/inorder-successor-in-bst
     * <p>
     * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
     * 
     * Note: If the given node has no in-order successor in the tree, return null.
     * 
     * </p>
     * 
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return succ;
    }

    /**
     * 652. Find Duplicate Subtrees
     * <p>
     * https://leetcode.com/problems/find-duplicate-subtrees
     * 
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> countMap = new HashMap<>();
        List<TreeNode> res = new ArrayList<>();
        serialize(root, countMap, res);
        return res;
    }

    private String serialize(TreeNode node, HashMap<String, Integer> countMap, List<TreeNode> res) {
        if (node == null) {
            return "#";
        }
        String key = node.val + "," + serialize(node.left, countMap, res) + "," + serialize(node.right, countMap, res);
        if (countMap.containsKey(key)) {
            if (countMap.get(key) == 1) {
                res.add(node);
            }
        }
        countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        return key;
    }

    /**
     * 298. Binary Tree Longest Consecutive Sequence
     * <p>
     * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence
     * <p>
     * Given a binary tree, find the length of the longest consecutive sequence path.
     * 
     * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child
     * connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
     * 
     * </p>
     * dfs
     * 
     * @param root
     * @return
     */
    private int longestCount = 0;

    public int longestConsecutive(TreeNode root) {
        dfsLongest(root, 0, root.val);
        return longestCount;
    }

    private void dfsLongest(TreeNode root, int count, int value) {
        if (root == null) {
            return;
        }
        if (root.val == value) {
            count++;
        } else {
            count = 1;
        }
        longestCount = Math.max(count, longestCount);
        dfsLongest(root.left, count, root.val + 1);
        dfsLongest(root.right, count, root.val + 1);
    }

    /**
     * 582. Kill Process
     * <p>
     * https://leetcode.com/problems/kill-process
     * <p>
     * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
     * 
     * Each process only has one parent process, but may have one or more children processes. This is just like a tree
     * structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will
     * be distinct positive integers.
     * 
     * We use two list of integers to represent a list of processes, where the first list contains PID for each process
     * and the second list contains the corresponding PPID.
     * 
     * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes
     * that will be killed in the end. You should assume that when a process is killed, all its children processes will
     * be killed. No order is required for the final answer.
     * </p>
     * 
     * @param pid
     * @param ppid
     * @param kill
     * @return
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> processMap = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int process = pid.get(i);
            int parent = ppid.get(i);
            if (!processMap.containsKey(parent)) {
                processMap.put(parent, new ArrayList<>());
            }
            processMap.get(parent).add(process);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int pro = queue.poll();
            res.add(pro);
            List<Integer> sub = processMap.get(pro);
            if (sub != null) {
                queue.addAll(sub);
            }
        }
        return res;
    }

    /**
     * 508. Most Frequent Subtree Sum
     * <p>
     * https://leetcode.com/problems/most-frequent-subtree-sum
     * <p>
     * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is
     * defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
     * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest
     * frequency in any order.
     * </p>
     * 
     * @param root
     * @return
     */
    int maxFrequentSumCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> countMap = new HashMap<>();
        postOrderCalSumCount(root, countMap);
        List<Integer> sumList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxFrequentSumCount) {
                sumList.add(entry.getKey());
            }
        }
        int[] res = new int[sumList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = sumList.get(i);
        }
        return res;
    }

    private int postOrderCalSumCount(TreeNode root, Map<Integer, Integer> countMap) {
        if (root == null) {
            return 0;
        }
        int left = postOrderCalSumCount(root.left, countMap);
        int right = postOrderCalSumCount(root.right, countMap);
        int sum = root.val + left + right;
        countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
        maxFrequentSumCount = Math.max(countMap.get(sum), maxFrequentSumCount);
        return sum;
    }

    /**
     * 366. Find Leaves of Binary Tree
     * <p>
     * https://leetcode.com/problems/find-leaves-of-binary-tree
     * <p>
     * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat
     * until the tree is empty.
     * 
     * </p>
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        findLeavesHelper(res, root);
        return res;
    }

    private int findLeavesHelper(List<List<Integer>> res, TreeNode node) {
        if (node == null) {
            return -1;
        }
        int leftLevel = findLeavesHelper(res, node.left);
        int rightLevel = findLeavesHelper(res, node.right);
        int curLevel = Math.max(leftLevel, rightLevel) + 1;
        if (res.size() == curLevel) {
            res.add(new ArrayList<>());
        }
        res.get(curLevel).add(node.val);
        node.left = null;
        node.right = null;
        return curLevel;
    }

    /**
     * 314. Binary Tree Vertical Order Traversal
     * <p>
     * https://leetcode.com/problems/binary-tree-vertical-order-traversal
     * <p>
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
     * column).
     * 
     * If two nodes are in the same row and column, the order should be from left to right.
     * </p>
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        treeNodeQueue.offer(root);
        colQueue.offer(0);
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        while (!treeNodeQueue.isEmpty()) {
            TreeNode node = treeNodeQueue.poll();
            int col = colQueue.poll();
            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);
            if (node.left != null) {
                treeNodeQueue.offer(node.left);
                colQueue.offer(col - 1);
            }
            if (node.right != null) {
                treeNodeQueue.offer(node.right);
                colQueue.offer(col + 1);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    /**
     * 623. Add One Row to Tree
     * <p>
     * https://leetcode.com/problems/add-one-row-to-tree
     * <p>
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the
     * given depth d. The root node is at depth 1.
     * 
     * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two
     * tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be
     * the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new
     * right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v
     * as the new root of the whole original tree, and the original tree is the new root's left subtree.
     * </p>
     * 
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRowBFS(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i < d - 2; i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TreeNode t = queue.poll();
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
        }
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            TreeNode tmp = t.left;
            t.left = new TreeNode(v);
            t.left.left = tmp;
            tmp = t.right;
            t.right = new TreeNode(v);
            t.right.right = tmp;
        }
        return root;
    }

    public TreeNode addOneRowDFS(TreeNode root, int v, int d) {
        if (d < 2) {
            TreeNode newroot = new TreeNode(v);
            if (d == 0) {
                newroot.right = root;
            } else {
                newroot.left = root;
            }
            return newroot;
        }
        if (root == null) {
            return null;
        }
        root.left = addOneRowDFS(root.left, v, d == 2 ? 1 : d - 1);
        root.right = addOneRowDFS(root.right, v, d == 2 ? 0 : d - 1);
        return root;
    }

    /**
     * 536. Construct Binary Tree from String
     * <p>
     * https://leetcode.com/problems/construct-binary-tree-from-string
     * <p>
     * You need to construct a binary tree from a string consisting of parenthesis and integers.
     * 
     * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of
     * parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with
     * the same structure.
     * 
     * You always start to construct the left child node of the parent first if it exists. *
     * </p>
     * todo
     * 
     * @param s
     * @return
     */
    public TreeNode str2tree(String s) {
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(6);
        TreeNode l = new TreeNode(8);
        TreeNode r = new TreeNode(15);
        TreeNode ll = new TreeNode(7);
        TreeNode lr = new TreeNode(9);
        TreeNode rl = new TreeNode(10);
        TreeNode rr = new TreeNode(18);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        r.left = rl;
        r.right = rr;
        int[] nums=  new int[]{8,6,7,9,15,10,18};
        System.out.println(solution.verifyPreorder(nums));
//        System.out.println(solution.inorderSuccessor(root, new TreeNode(1)));
        // root.right = r;
        // r.left = rl;
        // r.right = rr;
        // root.right = new TreeNode(2);
        // root.right.right = new TreeNode(3);
        // System.out.println(solution.deleteNode(root, 2));
        // System.out.println(solution.kthSmallest(root, 1));
        // System.out.println(solution.isValidBST(root));
//        System.out.println(solution.sumNumbers(root));
    }
}
