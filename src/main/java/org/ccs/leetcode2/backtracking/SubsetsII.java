/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * <p>
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * </p>
 * 
 * @author abel created on 2018/1/3 下午12:05
 * @version $Id$
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        helper(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int[] nums, int pos, List<Integer> list) {
        System.out.println(pos);
        res.add(new ArrayList<>(list));
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            helper(res, nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsII solution = new SubsetsII();
        System.out.println(solution.subsetsWithDup(new int[] { 1,2,3 }));
    }
}
