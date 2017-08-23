/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

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
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length <= 3) {
            Arrays.sort(nums);
            return nums[nums.length - 1];
        }
        int low = robNotCircle(nums, 0, nums.length - 2);
        int high = robNotCircle(nums, 1, nums.length - 1);

        return Math.max(low, high);

    }

    private int robNotCircle(int[] nums, int begin, int end) {
        int maxPrev = 0;
        int maxCur = 0;
        for (int i = begin; i <= end; i++) {
            int temp = maxCur;
            maxCur = Math.max(maxCur, maxPrev + nums[i]);
            maxPrev = temp;
        }
        return maxCur;
    }

    /**
     * 337. House Robber III
     * <p>
     * https://leetcode.com/problems/house-robber-iii
     * <p>
     * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called
     * the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief
     * realized that "all houses in this place forms a binary tree". It will automatically contact the police if two
     * directly-linked houses were broken into on the same night.
     * 
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     * </p>
     * https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem/2
     * 
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        return robSub(root, new HashMap<>());
    }

    private int robSub(TreeNode root, HashMap<TreeNode, Integer> nodeMap) {
        if (root == null) {
            return 0;
        }
        if (nodeMap.containsKey(root)) {
            return nodeMap.get(root);
        }
        int res = 0;
        if (root.left != null) {
            res += robSub(root.left.left, nodeMap) + robSub(root.left.right, nodeMap);
        }
        if (root.right != null) {
            res += robSub(root.right.left, nodeMap) + robSub(root.right.right, nodeMap);
        }
        res = Math.max(res + root.val, robSub(root.left, nodeMap) + robSub(root.right, nodeMap));
        nodeMap.put(root, res);
        return res;
    }

    /**
     * Step III -- Think one step back
     * 
     * In step I, we defined our problem as rob(root), which will yield the maximum amount of money that can be robbed
     * of the binary tree rooted at root. This leads to the DP problem summarized in step II.
     * 
     * Now let's take one step back and ask why we have overlapping subproblems. If you trace all the way back to the
     * beginning, you'll find the answer lies in the way how we have defined rob(root). As I mentioned, for each tree
     * root, there are two scenarios: it is robbed or is not. rob(root) does not distinguish between these two cases, so
     * "information is lost as the recursion goes deeper and deeper", which results in repeated subproblems.
     * 
     * If we were able to maintain the information about the two scenarios for each tree root, let's see how it plays
     * out. Redefine rob(root) as a new function which will return an array of two elements, the first element of which
     * denotes the maximum amount of money that can be robbed if root is not robbed, while the second element signifies
     * the maximum amount of money robbed if it is robbed.
     * 
     * Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc. For the 1st element of rob(root), we only
     * need to sum up the larger elements of rob(root.left) and rob(root.right), respectively, since root is not robbed
     * and we are free to rob its left and right subtrees. For the 2nd element of rob(root), however, we only need to
     * add up the 1st elements of rob(root.left) and rob(root.right), respectively, plus the value robbed from root
     * itself, since in this case it's guaranteed that we cannot rob the nodes of root.left and root.right.
     * 
     * As you can see, by keeping track of the information of both scenarios, we decoupled the subproblems and the
     * solution essentially boiled down to a greedy one.
     * 
     * @param root
     * @return
     */
    public int robDP(TreeNode root) {
        int res[] = robSub2(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub2(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = robSub2(root.left);
        int[] right = robSub2(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
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
