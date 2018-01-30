/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import java.util.ArrayList;
import java.util.List;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 113. Path Sum II
 * <p>
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * </p>
 * 
 * @author Abel created on 2018/1/5 18:56
 * @version $Id$
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfsFindPath(root, sum, res, new ArrayList<>());
        return res;
    }

    private void dfsFindPath(TreeNode root, int sum, List<List<Integer>> res, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && sum == root.val) {
            list.add(root.val);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        dfsFindPath(root.left, sum - root.val, res, list);
        dfsFindPath(root.right, sum - root.val, res, list);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l = new TreeNode(4);
        TreeNode r = new TreeNode(8);
        TreeNode ll = new TreeNode(11);
        TreeNode rl = new TreeNode(13);
        TreeNode rr = new TreeNode(4);
        TreeNode lll = new TreeNode(7);
        TreeNode llr = new TreeNode(2);
        TreeNode rrl = new TreeNode(5);
        TreeNode rrr = new TreeNode(1);
        root.left = l;
        root.right = r;
        l.left = ll;
        r.left = rl;
        r.right = rr;
        ll.left = lll;
        ll.right = llr;
        rr.left = rrl;
        rr.right = rrr;
        System.out.println(new PathSumII().pathSum(root, 22));
    }
}
