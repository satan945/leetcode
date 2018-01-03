/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. Permutations
 * <p>
 * Given a collection of distinct numbers, return all possible permutations.
 * </p>
 * 
 * @author abel created on 2018/1/2 上午10:15
 * @version $Id$
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, nums, 0, nums.length - 1);
        return res;
    }

    private void helper(List<List<Integer>> res, int[] nums, int start, int end) {
        if (start == end) {
            res.add(genList(nums));
            return;
        }

        for (int i = start; i <= end; i++) {
            swap(nums, i, start);
            helper(res, nums, start + 1, end);
            swap(nums, i, start);
        }

    }

    private List<Integer> genList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return list;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        Permutations solution = new Permutations();
        System.out.println(solution.permute(a));
    }
}
