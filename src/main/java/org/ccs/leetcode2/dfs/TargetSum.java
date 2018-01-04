/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dfs;

import java.util.Arrays;

/**
 * 494. Target Sum
 * <p>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For
 * each integer, you should choose one from + and - as its new symbol.
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * 
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * -1+1+1+1+1 = 3 +1-1+1+1+1 = 3 +1+1-1+1+1 = 3 +1+1+1-1+1 = 3 +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3. Note: The length of the given array is
 * positive and will not exceed 20. The sum of elements in the given array will not exceed 1000. Your output answer is
 * guaranteed to be fitted in a 32-bit integer.
 * </p>
 * 
 * @author abel created on 2018/1/3 下午5:32
 * @version $Id$
 */
public class TargetSum {

    private int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int sum = 0;
        int zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
            sum += num;
        }
        int target = sum - S;
        if (target % 2 != 0) {
            return 0;
        }
        dfs(target / 2, nums, 0);
        if (target == 0) {
            count *= Math.pow(2, zeroCount);
        }
        return count;
    }

    private void dfs(int target, int[] nums, int start) {
        if (target == 0) {
            count++;
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (target - nums[i] < 0) {
                return;
            }
            if (target - nums[i] >= 0) {
                dfs(target - nums[i], nums, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        TargetSum solution = new TargetSum();
        // System.out.println(solution.findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3));
        // System.out.println(solution.findTargetSumWays(new int[] { 1, 0 }, 1));
        System.out.println(solution.findTargetSumWays(new int[] { 9, 7, 0, 3, 9, 8, 6, 5, 7, 6 }, 2));
    }
}
