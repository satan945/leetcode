/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author abel created on 2017/8/11 下午5:15
 * @version $Id$
 */
public class Solution {

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
        int res[] = robSubDP(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSubDP(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = robSubDP(root.left);
        int[] right = robSubDP(root.right);
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

    /**
     * 139. Word Break
     * <p>
     * https://leetcode.com/problems/word-break
     * <p>
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be
     * segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not
     * contain duplicate words.
     * 
     * For example, given s = "leetcode", dict = ["leet", "code"].
     * 
     * Return true because "leetcode" can be segmented as "leet code".
     * 
     * UPDATE (2017/1/4): The wordDict parameter had been changed to a list of strings (instead of a set of strings).
     * Please reload the code definition to get the latest changes.
     * 
     * 
     * </p>
     * 
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        HashSet<String> dict = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreakTimeExceed(String s, List<String> wordDict) {
        return breakWordBrute(s, new HashSet<String>(wordDict), 0);
    }

    private boolean breakWordBrute(String string, HashSet<String> dict, int start) {
        if (start == string.length()) {
            return true;
        }
        for (int end = start + 1; end <= string.length(); end++) {
            if (dict.contains(string.substring(start, end)) && breakWordBrute(string, dict, end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 279. Perfect Squares
     * <p>
     * https://leetcode.com/problems/perfect-squares
     * <p>
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which
     * sum to n.
     * 
     * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
     * </p>
     * http://www.jianshu.com/p/2925f4d7511b
     * 
     * http://www.cnblogs.com/liujinhong/p/6022546.html
     * 
     * http://blog.csdn.net/jmspan/article/details/51148344
     * 
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] countArray = new int[n + 1];
        Arrays.fill(countArray, Integer.MAX_VALUE);
        countArray[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            countArray[i] = 1;
        }
        for (int i = 1; i <= n; i++) {

        }
        return countArray[n];
    }

    /**
     * 91. Decode Ways
     * <p>
     * https://leetcode.com/problems/decode-ways
     * <p>
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1 'B' -> 2 ... 'Z' -> 26
     *
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     *
     * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     *
     * The number of ways decoding "12" is 2.
     * </p>
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= len; i++) {
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[len];
    }

    /**
     * 120. Triangle
     * <p>
     * https://leetcode.com/problems/triangle
     * <p>
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the
     * row below.
     * 
     * For example, given the following triangle [ [2], [3,4], [6,5,7], [4,1,8,3] ] The minimum path sum from top to
     * bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * 
     * Note: Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in
     * the triangle.
     * </p>
     * todo
     * 
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return 0;
    }

    /**
     * 377. Combination Sum IV
     * <p>
     * https://leetcode.com/problems/combination-sum-iv
     * <p>
     * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that
     * add up to a positive integer target.
     * 
     * Example:
     * 
     * nums = [1, 2, 3] target = 4
     * 
     * The possible combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
     * 
     * Note that different sequences are counted as different combinations.
     * 
     * Therefore the output is 7. Follow up: What if negative numbers are allowed in the given array? How does it change
     * the problem? What limitation we need to add to the question to allow negative numbers?
     * 
     * Credits: Special thanks to @pbrother for adding this problem and creating all test cases.
     * </p>
     * 
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 357. Count Numbers with Unique Digits
     * <p>
     * https://leetcode.com/problems/count-numbers-with-unique-digits
     * <p>
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
     *
     * Example: Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding
     * [11,22,33,44,55,66,77,88,99])
     * </p>
     * https://discuss.leetcode.com/category/441/count-numbers-with-unique-digits
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) {
            return countNumbersWithUniqueDigits(10);
        }
        if (n == 0) {
            return 1;
        }
        int res = 10;
        int uniqueDigits = 9;
        int availableDigits = 9;
        for (int i = 2; i < n && availableDigits > 0; i++) {
            uniqueDigits = uniqueDigits * availableDigits;
            availableDigits--;
            res += uniqueDigits;
        }
        return res;
    }

    /**
     * 64. Minimum Path Sum
     * <p>
     * https://leetcode.com/problems/minimum-path-sum
     * 
     * <p>
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
     * the sum of all numbers along its path.
     * 
     * Note: You can only move either down or right at any point in time.
     * 
     * Example 1: [[1,3,1], [1,5,1], [4,2,1]] Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes
     * the sum.
     * </p>
     * 
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int up = i > 0 ? dp[i - 1][j] : Integer.MAX_VALUE;
                int left = j > 0 ? dp[i][j - 1] : Integer.MAX_VALUE;
                dp[i][j] = grid[i][j] + Math.min(left, up);
            }
        }
        return dp[m - 1][n - 1];
    }

    private int minSum = Integer.MAX_VALUE;

    public int minPathSumRecursive(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int sum = 0;
        calSum(grid, m, n, 0, 0, sum);
        return minSum;
    }

    private void calSum(int[][] grid, int m, int n, int i, int j, int sum) {
        if (i <= m - 1 && j <= n - 1) {
            sum += grid[i][j];
        }
        if (i == m - 1 && j == n - 1) {
            minSum = Math.min(minSum, sum);
        } else {
            if (i < m - 1) {
                calSum(grid, m, n, i + 1, j, sum);
            }
            if (j < n - 1) {
                calSum(grid, m, n, i, j + 1, sum);
            }
        }
    }

    /**
     * 62. Unique Paths
     * <p>
     * https://leetcode.com/problems/unique-paths
     * <p>
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * 
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     * corner of the grid (marked 'Finish' in the diagram below).
     * 
     * How many possible unique paths are there?
     * </p>
     * 
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 63. Unique Paths II
     * <p>
     * https://leetcode.com/problems/unique-paths-ii
     * <p>
     * Follow up for "Unique Paths":
     * 
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * 
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * 
     * For example, There is one obstacle in the middle of a 3x3 grid as illustrated below.
     * 
     * [ [0,0,0], [0,1,0], [0,0,0] ] The total number of unique paths is 2.
     * </p>
     * 
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];

    }

    /**
     * 322. Coin Change
     * 
     * <p>
     * https://leetcode.com/problems/coin-change
     * <p>
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute
     * the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
     * combination of the coins, return -1.
     * 
     * Example 1: coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
     * 
     * Example 2: coins = [2], amount = 3 return -1.
     * 
     * Note: You may assume that you have an infinite number of each kind of coin.
     * </p>
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }
        int[] counts = new int[amount + 1];
        Arrays.sort(coins);
        Arrays.fill(counts, Integer.MAX_VALUE);
        for (int coin : coins) {
            if (coin == amount) {
                return 1;
            }
            if (coin < amount) {
                counts[coin] = 1;
            }
        }
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin > 0) {
                    if (counts[i - coin] == Integer.MAX_VALUE) {
                        continue;
                    }
                    counts[i] = Math.min(counts[i], counts[i - coin] + 1);
                }
            }
        }
        return counts[amount] == Integer.MAX_VALUE ? -1 : counts[amount];
    }

    public int coinChangeRecursive(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        List<Integer> counts = new ArrayList<>();
        calCounts(coins, amount, 0, counts);
        counts.sort(Comparator.comparingInt(o -> o));
        return counts.isEmpty() ? -1 : counts.get(0);
    }

    private void calCounts(int[] coins, int amount, int count, List<Integer> counts) {
        if (amount == 0) {
            count++;
            counts.add(count);
            return;
        } else if (amount < 0) {
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            calCounts(coins, amount - coins[i], count + 1, counts);
        }
    }

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
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;

        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }

    /**
     * 343. Integer Break
     * <p>
     * https://leetcode.com/problems/integer-break
     * <p>
     * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of
     * those integers. Return the maximum product you can get.
     * 
     * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
     * 
     * Note: You may assume that n is not less than 2 and not larger than 58.
     * </p>
     * 
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] maxPro = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                maxPro[i] = Math.max(maxPro[i], Math.max(j, maxPro[j]) * Math.max(i - j, maxPro[i - j]));
            }
        }
        return maxPro[n];
    }

    /**
     * 714. Best Time to Buy and Sell Stock with Transaction Fee
     * <p>
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
     * <p>
     * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
     * and a non-negative integer fee representing a transaction fee.
     * 
     * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
     * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
     * 
     * Return the maximum profit you can make.
     * 
     * Example 1: Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 
     * Output: 8
     * 
     * Explanation: The maximum profit can be achieved by: Buying at prices[0] = 1 Selling at prices[3] = 8 Buying at
     * prices[4] = 4 Selling at prices[5] = 9 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8. Note:
     * 
     * 0 < prices.length <= 50000.
     * 
     * 0 < prices[i] < 50000.
     * 
     * 0 <= fee < 50000.
     * </p>
     * 
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        int[] hold = new int[len];
        int[] skip = new int[len];
        buy[0] = -prices[0];
        hold[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(skip[i - 1], sell[i - 1]) - prices[i];
            sell[i] = Math.max(hold[i - 1], buy[i - 1]) + prices[i] - fee;
            skip[i] = Math.max(sell[i - 1], skip[i - 1]);
            hold[i] = Math.max(hold[i - 1], buy[i - 1]);
        }
        return Math.max(skip[len - 1], (Math.max(sell[len - 1], (Math.max(buy[len - 1], hold[len - 1])))));
    }

    /**
     * 300. Longest Increasing Subsequence
     * <p>
     * https://leetcode.com/problems/longest-increasing-subsequence
     * 
     * <p>
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     * 
     * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3, 7, 101], therefore
     * the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the
     * length.
     * 
     * Your algorithm should run in O(n2) complexity.
     * 
     * Follow up: Could you improve it to O(n log n) time complexity?
     * </p>
     * 
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 0;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
            max = 0;
        }
        return res;
    }

    /**
     * 375. Guess Number Higher or Lower II
     * <p>
     * </p>
     * 
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return calMoney(dp, 1, n);
    }

    private int calMoney(int[][] dp, int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        int res = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int tmp = i + Math.max(calMoney(dp, start, i - 1), calMoney(dp, i + 1, end));
            res = Math.min(tmp, res);
        }
        dp[start][end] = res;
        return res;
    }

    /**
     * 221. Maximal Square
     * <p>
     * https://leetcode.com/problems/maximal-square
     * <p>
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its
     * area.
     * </p>
     * 
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j - 1]), dp[i - 1][j]) + 1;
                    result = Math.max(dp[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

    /**
     * 413. Arithmetic Slices
     * <p>
     * https://leetcode.com/problems/arithmetic-slices
     * <p>
     * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between
     * any two consecutive elements is the same.
     * 
     * For example, these are arithmetic sequence:
     * 
     * 1, 3, 5, 7, 9 7, 7, 7, 7 3, -1, -5, -9 The following sequence is not arithmetic.
     * 
     * 1, 1, 2, 5, 7
     * 
     * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q)
     * such that 0 <= P < Q < N.
     * 
     * A slice (P, Q) of array A is called arithmetic if the sequence: A[P], A[p + 1], ..., A[Q - 1], A[Q] is
     * arithmetic. In particular, this means that P + 1 < Q.
     * 
     * The function should return the number of arithmetic slices in the array A.
     * 
     * 
     * Example:
     * 
     * A = [1, 2, 3, 4]
     * 
     * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
     * </p>
     * 
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length <= 2) {
            return 0;
        }
        int[] dp = new int[A.length];
        int res = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i - 1] - A[i - 2] == A[i] - A[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }

    /**
     * 368. Largest Divisible Subset
     * <p>
     * https://leetcode.com/problems/largest-divisible-subset
     * <p>
     * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in
     * this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
     * 
     * If there are multiple solutions, return any subset is fine.
     * 
     * Example 1:
     * 
     * nums: [1,2,3]
     * 
     * Result: [1,2] (of course, [1,3] will also be ok) Example 2:
     * 
     * nums: [1,2,4,8]
     * 
     * Result: [1,2,4,8]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            maxIndex = dp[i] > dp[maxIndex] ? i : maxIndex;
        }

        int cur = dp[maxIndex];
        int tmp = nums[maxIndex];
        for (int i = maxIndex; i >= 0; i--) {
            if (tmp % nums[i] == 0 && cur == dp[i]) {
                res.add(nums[i]);
                tmp = nums[i];
                cur--;
            }
        }
        return res;
    }

    /**
     * 516. Longest Palindromic Subsequence
     * <p>
     * https://leetcode.com/problems/longest-palindromic-subsequence
     * 
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int n = s.length();
        int[][] dp = new int[n][n];// storage the length of longest palindromic subsequence from i to j
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 474. Ones and Zeroes
     * <p>
     * https://leetcode.com/problems/ones-and-zeroes
     * <p>
     * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to
     * pursue.
     *
     * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with
     * strings consisting of only 0s and 1s.
     *
     * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1
     * can be used at most once.
     *
     * Note: The given numbers of 0s and 1s will both not exceed 100 The size of given string array won't exceed 600.
     * </p>
     * greedy : https://discuss.leetcode.com/topic/81309/java-19ms-beats-100-no-dp-but-greedy
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if ((m == 0 && n == 0) || strs == null || strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = countZeroAndOne(str);
            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] countZeroAndOne(String str) {
        int[] count = new int[2];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - '0']++;
        }
        return count;
    }

    /**
     * 416. Partition Equal Subset Sum
     * <p>
     * https://leetcode.com/problems/partition-equal-subset-sum
     * <p>
     * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
     * such that the sum of elements in both subsets is equal.
     * 
     * Note: Each of the array element will not exceed 100. The array size will not exceed 200.
     * </p>
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum == 0) {
            return true;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum >>= 1;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; i >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }
        return dp[sum];
    }

    /**
     * 678. Valid Parenthesis String
     * <p>
     * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether
     * this string is valid. We define the validity of a string by these rules:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'. Any right parenthesis ')' must have a
     * corresponding left parenthesis '('. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string. An
     * empty string is also valid.
     *
     * Example 1: Input: "()" Output: True
     *
     * Example 2: Input: "(*)" Output: True
     *
     * Example 3: Input: "(*))" Output: True
     * </p>
     * todo
     * 
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        return false;
    }

    /**
     * 583. Delete Operation for Two Strings
     *
     * <p>
     * https://leetcode.com/problems/delete-operation-for-two-strings
     * <p>
     * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
     * where in each step you can delete one character in either string.
     * </p>
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        int val = dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }

    /**
     * 718. Maximum Length of Repeated Subarray
     * <p>
     * https://leetcode.com/problems/maximum-length-of-repeated-subarray
     * <p>
     * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
     * </p>
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= B.length; j++) {
                if (i == 0 || j == 0) {
                    dp[0][0] = 0;
                } else {
                    if (A[i - 1] == B[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(dp[i][j], max);
                    }
                }
            }
        }
        return max;
    }

    /**
     * 576. Out of Boundary Paths
     * <p>
     * https://leetcode.com/problems/out-of-boundary-paths
     * <p>
     * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to
     * adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most
     * move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large,
     * return it after mod 109 + 7.
     * </p>
     * 
     * @param m
     * @param n
     * @param N
     * @param i
     * @param j
     * @return
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        final int M = 1000000007;
        int[][] dp = new int[m][n];
        int[] moves = new int[] { 1, 0, -1, 0, 1 };
        dp[i][j] = 1;
        int count = 0;

        for (int step = 1; step <= N; step++) {
            int[][] tmp = new int[m][n];
            for (int y = 0; y < m; y++) {
                for (int x = 0; x < n; x++) {
                    for (int move = 0; move < moves.length - 1; move++) {
                        int nx = x + moves[i];
                        int ny = y + moves[i + 1];
                        if (nx >= n || nx < 0 || ny >= m || ny < 0) {
                            count = (count + dp[i][j]) % M;
                        } else {
                            tmp[ny][nx] = (tmp[ny][nx] + dp[y][x]) % M;
                        }
                    }
                }
            }
            dp = tmp;
        }

        return count;
    }

    /**
     * 334. Increasing Triplet Subsequence
     * <p>
     * https://leetcode.com/problems/increasing-triplet-subsequence/description/
     * <p>
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
     * 
     * Formally the function should: Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given 0 ≤ i
     * < j < k ≤ n-1 else return false. Your algorithm should run in O(n) time complexity and O(1) space complexity.
     * 
     * Examples: Given [1, 2, 3, 4, 5], return true.
     * 
     * Given [5, 4, 3, 2, 1], return false.
     * </p>
     * 
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int dp[] = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]) + 1;
                }
                if (dp[i] == 3) {
                    return true;
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
        int[] nums2 = new int[] { 1, 2, 4, 8 };
        int[] A = new int[] { 1, 2, 3, 2, 1 };
        int[] B = new int[] { 3, 2, 1, 4, 7 };
        // System.out.println(solution.largestDivisibleSubset(nums2));
        // System.out.println(solution.lengthOfLIS(nums));
        System.out.println(solution.findLength(A, B));
        // int[][] num = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        // dict.add("aaaa");
        // dict.add("aaa");
        // System.out.println(new Solution().wordBreak("aaaaaaa", dict));
        //
        // String a = "abcdefg";
        // System.out.println(a.substring(4, 6));
        // int[] num = new int[] { 2 };
        // System.out.println(solution.integerBreak(58));
        // System.out.println(solution.coinChange(num, 3));
        // System.out.println(solution.maxProfit(new int[] { 1, 3, 2, 8, 4, 9 }, 2));
    }
}
