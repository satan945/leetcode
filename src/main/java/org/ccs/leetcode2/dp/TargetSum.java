/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

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

    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        if (s > sum || s < -sum)
            return 0;
        int[] dp = new int[2 * sum + 1];
        dp[0 + sum] = 1;
        for (int i = 0; i < nums.length; i++) {
            int[] next = new int[2 * sum + 1];
            for (int k = 0; k < 2 * sum + 1; k++) {
                if (dp[k] != 0) {
                    next[k + nums[i]] += dp[k];
                    next[k - nums[i]] += dp[k];
                }
            }
            dp = next;
        }
        return dp[sum + s];
    }

    public static void main(String[] args) {
        TargetSum solution = new TargetSum();
        // System.out.println(solution.findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3));
        // System.out.println(solution.findTargetSumWays(new int[] { 1, 0 }, 1));
        System.out.println(solution.findTargetSumWays(new int[] { 9, 7, 0, 3, 9, 8, 6, 5, 7, 6 }, 2));
    }
}
