/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.easy;

/**
 * @author abel created on 2017/8/11 下午2:40
 * @version $Id$
 */
public class Solution {

    /**
     * 121. Best Time to Buy and Sell Stock
     * <p>
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock
     * <p>
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * 
     * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     * 
     * Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5
     * 
     * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price) Example 2: Input:
     * [7, 6, 4, 3, 1] Output: 0
     * 
     * In this case, no transaction is done, i.e. max profit = 0.
     * </p>
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int minBefore = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - minBefore;
            maxProfit = Math.max(maxProfit, diff);
            minBefore = Math.min(prices[i], minBefore);
        }
        return maxProfit;
    }

    /**
     * 53. Maximum Subarray
     * <p>
     * https://leetcode.com/problems/maximum-subarray
     * <p>
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     *
     * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
     *
     * click to show more practice.
     *
     * More practice: If you have figured out the O(n) solution, try coding another solution using the divide and
     * conquer approach, which is more subtle.
     *
     *
     * </p>
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        int maxSum = sums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sums[i - 1] > 0) {
                sums[i] = nums[i] + sums[i - 1];
            } else {
                sums[i] = nums[i];
            }
            maxSum = Math.max(maxSum, sums[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(solution.maxSubArray(prices));
    }


    /**
     * 70. Climbing Stairs
     * <p>
     * https://leetcode.com/problems/climbing-stairs
     * <p>
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Note: Given n will be a positive integer.
     * </p>
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int f1 = 1;
        int f2 = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = f1 + f2;
            f1 = f2;
            f2 = res;
        }
        return res;

    }

    /**
     * Given a list of N coins, their values (V1, V2, … , VN), and the total sum S. Find the minimum number of coins the
     * sum of which is S (we can use as many coins of one type as we want), or report that it’s not possible to select
     * coins in such a way that they sum up to S.
     *
     * @param coins
     * @param sum
     * @return
     */
    public int findCoins(int[] coins, int sum) {
        return 0;
    }
}
