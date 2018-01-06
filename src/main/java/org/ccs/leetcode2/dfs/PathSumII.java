/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import org.ccs.leetcode.bean.TreeNode;
import org.ccs.utils.TreeDeserializer;

import java.util.ArrayList;
import java.util.List;

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
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, sum, res, new ArrayList<>());
        return res;
    }

    private void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        dfs(root.left, sum - root.val, res, list);
        dfs(root.right, sum - root.val, res, list);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        String tree = "5,4,8,11,null,13,4,7,2,null,null,5,1";
        TreeDeserializer treeDeserializer = new TreeDeserializer();
        TreeNode node = treeDeserializer.deserialize(tree);
        PathSumII solution = new PathSumII();
        System.out.println(solution.pathSum(node, 22));

    }
}
