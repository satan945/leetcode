/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.tree.travel;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author abel created on 2017/8/25 下午3:52
 * @version $Id$
 */
public class LayerOrderTraversal {

    List<Integer> layerOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode l = new TreeNode(1);
        TreeNode r = new TreeNode(2);
        TreeNode ll = new TreeNode(3);
        TreeNode lr = new TreeNode(4);
        TreeNode rl = new TreeNode(5);
        TreeNode rr = new TreeNode(6);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        r.left = rl;
        r.right = rr;
        LayerOrderTraversal layerOrderTraversal = new LayerOrderTraversal();
        System.out.println(layerOrderTraversal.layerOrder(root));
    }
}
