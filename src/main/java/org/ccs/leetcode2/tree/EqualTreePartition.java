/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 663. Equal Tree Partition
 *
 * https://leetcode.com/problems/equal-tree-partition
 * 
 * @author abel created on 2018/3/14 下午5:07
 * @version $Id$
 */
public class EqualTreePartition {

    public boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = calSum(root, map);
        if (sum == 0) {
            return map.getOrDefault(0, 0) > 1;
        }
        return sum % 2 == 0 && map.containsKey(sum / 2);
    }

    private int calSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + calSum(root.left, map) + calSum(root.right, map);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l = new TreeNode(10);
        TreeNode r = new TreeNode(10);
        TreeNode rl = new TreeNode(2);
        TreeNode rr = new TreeNode(3);
        root.left = l;
        root.right =r;
        r.left=rl;
        r.right=rr;
        EqualTreePartition solution = new EqualTreePartition();
        System.out.println(solution.checkEqualTree(root));
    }
}
