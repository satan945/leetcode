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
     * 198. House Robber
     *
     * <p>
     * https://leetcode.com/problems/house-robber
     * <p>
     * 
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
     * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
     * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * 
     * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
     * amount of money you can rob tonight without alerting the police.
     * 
     * Credits: Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for
     * adding additional test cases.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int[] sum = new int[nums.length];
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        sum[0] = nums[0];
        sum[1] = nums[1];
        int maxPre = sum[0];
        int max = sum[0] > sum[1] ? sum[0] : sum[1];
        if (nums.length == 2) {
            return max;
        }
        for (int i = 2; i < nums.length; i++) {
            sum[i] = maxPre + nums[i];
            maxPre = Math.max(maxPre, sum[i - 1]);
            max = Math.max(max, sum[i]);
        }
        return max;
    }

    /**
     * https://leetcode.com/problems/house-robber/solution/
     * 
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int maxCur = 0;
        int maxPrev = 0;
        for (int num : nums) {
            int temp = maxCur;
            maxCur = Math.max(maxPrev + num, maxPrev);
            maxPrev = temp;

        }
        return maxCur;
    }

    /**
     * 256. Paint House
     * <p>
     * https://leetcode.com/problems/paint-house/description/
     * <p>
     * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost
     * of painting each house with a certain color is different. You have to paint all the houses such that no two
     * adjacent houses have the same color.
     * 
     * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example,
     * costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with
     * color green, and so on... Find the minimum cost to paint all houses.
     * 
     * Note: All costs are positive integers.
     * </p>
     * 
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int houses = costs.length;
        int[][] estimateCost = new int[costs.length][3];
        estimateCost[0][0] = costs[0][0];
        estimateCost[0][1] = costs[0][1];
        estimateCost[0][2] = costs[0][2];
        for (int i = 1; i < houses; i++) {
            for (int j = 0; j < 3; j++) {
                estimateCost[i][j] = costs[i][j] + calMin(j, estimateCost[i - 1]);
            }
        }
        return Math.min(Math.min(estimateCost[houses - 1][0], estimateCost[houses - 1][1]),
                estimateCost[houses - 1][2]);
    }

    public int minCost2(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][1], costs[i - 1][0]);
        }
        int n = costs.length - 1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }

    private int calMin(int j, int[] costs) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            if (i != j) {
                min = Math.min(costs[i], min);
            }
        }
        return min;
    }

    /**
     * 276. Paint Fence
     * <p>
     * https://leetcode.com/problems/paint-fence
     * <p>
     * There is a fence with n posts, each post can be painted with one of the k colors.
     * 
     * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
     * 
     * Return the total number of ways you can paint the fence.
     * 
     * </p>
     * https://discuss.leetcode.com/topic/31093/easy-to-understand-java-o-n-runtime-o-1-space
     * https://discuss.leetcode.com/topic/23426/o-n-time-java-solution-o-1-space/2
     * 
     * @param n
     * @param k
     * @return
     */
    public int numWays(int n, int k) {
        if ((n == 0 || k == 0) || (k == 1 && n >= 3)) {
            return 0;
        }
        int first = k;
        int second = k * k;
        int sameCount = 0;
        int diffCount = 0;
        if (n == 1) {
            return first;
        }
        if (n == 2) {
            return second;
        }
        int third;
        for (int i = 3; i <= n; i++) {
            third = (first + second) * (k - 1);
            first = second;
            second = third;
        }

        return second;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = new int[] { 1, 3, 1 };
        // System.out.println(solution.maxSubArray(prices));
        System.out.println(solution.rob(prices));
        System.out.println(solution.numWays(2, 1));
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
