/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.greedy.easy;

/**
 * @author abel created on 2017/8/11 下午5:04
 * @version $Id$
 */
public class Solution {

    /**
     * 122. Best Time to Buy and Sell Stock II
     * <p>
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
     * <p>
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * 
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
     * and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same
     * time (ie, you must sell the stock before you buy again).
     * </p>
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 1) {
            return 0;
        }
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                profit += (prices[i + 1] - prices[i]);
            }
        }
        return profit;
    }
}
