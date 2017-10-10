/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bfs.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author abel created on 2017/10/9 下午5:48
 * @version $Id$
 */
public class Solution {
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
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        while (size > 0) {
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
            size = queue.size();
        }
        return res;
    }



    public List<Integer> rightSideView2(TreeNode root) {
        // reverse level traversal
        List<Integer> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        if (root == null) return result;

        queue.offer(root);
        while (queue.size() != 0) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) result.add(cur.val);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }

        }
        return result;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(new Solution().rightSideView(root));
    }
}
