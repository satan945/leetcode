/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
     * todo
     * 
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        return 0;
    }

    /**
     * 375. Guess Number Higher or Lower II
     * <p>
     * https://leetcode.com/problems/guess-number-higher-or-lower-ii
     * <p>
     * We are playing the Guess Game. The game is as follows:
     * 
     * I pick a number from 1 to n. You have to guess which number I picked.
     * 
     * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
     * 
     * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess
     * the number I picked.
     * 
     * Example:
     * 
     * n = 10, I pick 8.
     * 
     * First round: You guess 5, I tell you that it's higher. You pay $5. Second round: You guess 7, I tell you that
     * it's higher. You pay $7. Third round: You guess 9, I tell you that it's lower. You pay $9.
     * 
     * Game over. 8 is the number I picked.
     * 
     * You end up paying $5 + $7 + $9 = $21. Given a particular n ≥ 1, find out how much money you need to have to
     * guarantee a win.
     * </p>
     * todo
     * 
     * @param n
     * @return
     */
    public int getMoneyAmount(int n) {
        return 0;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> dict = new ArrayList<>();
        // dict.add("aaaa");
        // dict.add("aaa");
        // System.out.println(new Solution().wordBreak("aaaaaaa", dict));
        //
        // String a = "abcdefg";
        // System.out.println(a.substring(4, 6));
        System.out.println(solution.numSquares(100));
    }
}
