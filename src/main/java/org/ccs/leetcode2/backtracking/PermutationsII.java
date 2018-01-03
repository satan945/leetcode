/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 47. Permutations II
 * <p>
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: [ [1,1,2], [1,2,1], [2,1,1] ]
 * </p>
 * 
 * @author abel created on 2018/1/3 上午11:49
 * @version $Id$
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, nums.length - 1, res);
        return res;
    }

    private void helper(int[] nums, int start, int end, List<List<Integer>> res) {
        if (start == end) {
            res.add(calList(nums));
            return;
        }
        Set<Integer> used = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (used.add(nums[i])) {
                swap(nums, i, start);
                helper(nums, start + 1, end, res);
                swap(nums, i, start);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private List<Integer> calList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    public static void main(String[] args) {
        PermutationsII solution = new PermutationsII();
        System.out.println(solution.permuteUnique(new int[] { 1, 1, 2 }));
    }
}
