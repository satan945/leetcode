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
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] + maxSum > maxSum) {
                maxSum = nums[i] + maxSum;
            } else {

            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = new int[] { 2, 4, 1 };
        System.out.println(solution.maxProfit(prices));
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
