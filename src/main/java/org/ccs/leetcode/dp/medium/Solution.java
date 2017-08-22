/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.medium;

/**
 * @author abel created on 2017/8/11 下午5:15
 * @version $Id$
 */
public class Solution {

    /**
     * 309. Best Time to Buy and Sell Stock with Cooldown
     * <p>
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * <p>
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * 
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times) with the following restrictions:
     * 
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day) Example:
     * 
     * prices = [1, 2, 3, 0, 2] maxProfit = 3 transactions = [buy, sell, cooldown, buy, sell]
     * </p>
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        return 0;

    }

    /**
     * 152. Maximum Product Subarray
     * <p>
     * https://leetcode.com/problems/maximum-product-subarray
     * <p>
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
     *
     * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
     * </p>
     * https://discuss.leetcode.com/category/160/maximum-product-subarray
     * 
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxPro = nums[0];
        int maxCur, minCur;
        int maxPre = nums[0];
        int minPre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxCur = Math.max(Math.max(maxPre * nums[i], minPre * nums[i]), nums[i]);
            minCur = Math.min(Math.min(minPre * nums[i], maxPre * nums[i]), nums[i]);
            maxPro = Math.max(maxCur, maxPro);
            maxPre = maxCur;
            minPre = minCur;
        }
        return maxPro;
    }

    /**
     * 213. House Robber II
     * <p>
     * https://leetcode.com/problems/house-robber-ii
     * <p>
     * Note: This is an extension of House Robber.
     * 
     * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he
     * will not get too much attention. This time, all houses at this place are arranged in a circle. That means the
     * first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as
     * for those in the previous street.
     * 
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
     * amount of money you can rob tonight without alerting the police.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        return 0;

    }

    /**
     * 96. Unique Binary Search Trees
     * <p>
     * https://leetcode.com/problems/unique-binary-search-trees
     * <p>
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     *
     * For example, Given n = 3, there are a total of 5 unique BST's.
     * </p>
     * https://zh.wikipedia.org/wiki/卡塔兰数
     *
     * http://blog.csdn.net/linhuanmars/article/details/24761459
     *
     * https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // return org.ccs.leetcode.tree.medium.Solution.numTrees();
        return 0;
    }
}
