/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author abel created on 2018/2/24 下午1:02
 * @version $Id$
 */
public class PostOrderTraversal {

    public List<Integer> postOrderTravel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        travelR(res, root);
        return res;
    }

    private void travelR(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        travelR(res, root.left);
        travelR(res, root.right);
        res.add(root.val);
    }

    public List<Integer> postOrderTravel2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            res.addFirst(root.val);
            if (root.left != null) {
                stack.push(node.left);
            }
            if (root.right != null) {
                stack.push(node.right);
            }
        }
        return res;
    }

}
