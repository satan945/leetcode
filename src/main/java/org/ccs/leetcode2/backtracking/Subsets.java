/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * </p>
 * 
 * @author abel created on 2018/1/2 下午6:43
 * @version $Id$
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        helper(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int[] nums, int pos, List<Integer> list) {
        res.add(new ArrayList<>(list));
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();
        System.out.println(solution.subsets(new int[] { 1, 2, 3 }));
    }
}
