/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 199. Binary Tree Right Side View
 * 
 * @author abel created on 2018/1/4 下午6:00
 * @version $Id$
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    res.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

}
