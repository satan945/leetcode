/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 314. Binary Tree Vertical Order Traversal
 * <p>
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
 * column).
 * 
 * If two nodes are in the same row and column, the order should be from left to right.
 * </p>
 * 
 * @author Abel created on 2018/1/29 21:52
 * @version $Id$
 */
public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int min = 0;
        int max = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            min = Math.min(num, min);
            max = Math.max(num, max);
            map.putIfAbsent(num, new ArrayList<>());
            map.get(num).add(node.val);
            if (node.left != null) {
                nodeQueue.offer(node.left);
                numQueue.offer(num - 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                numQueue.offer(num + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

}
