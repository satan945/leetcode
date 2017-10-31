/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.greedy.easy;

import java.util.Arrays;

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

    /**
     * 455. Assign Cookies
     * <p>
     * https://leetcode.com/problems/assign-cookies
     *
     * <p>
     * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at
     * most one cookie. Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be
     * content with; and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the
     * child i will be content. Your goal is to maximize the number of your content children and output the maximum
     * number.
     * 
     * Note: You may assume the greed factor is always positive. You cannot assign more than one cookie to one child.
     * 
     * Example 1: Input: [1,2,3], [1,1]
     * 
     * Output: 1
     * 
     * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. And even though you
     * have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content. You
     * need to output 1. Example 2: Input: [1,2], [1,2,3]
     * 
     * Output: 2
     * 
     * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. You have 3 cookies and
     * their sizes are big enough to gratify all of the children, You need to output 2.
     * </p>
     * 
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int j = 0;
        for (int i = 0; i < g.length && j < s.length;) {
            if (g[i] <= s[i]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }
}
