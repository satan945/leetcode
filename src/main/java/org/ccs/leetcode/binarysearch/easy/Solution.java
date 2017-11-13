/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.binarysearch.easy;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Arrays;

/**
 * @author Abel created on 2017/9/20 17:59
 * @version $Id$
 */
public class Solution {

    /**
     * 475. Heaters
     * <p>
     * https://leetcode.com/problems/heaters
     * <p>
     * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm
     * all the houses.
     * 
     * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so
     * that all houses could be covered by those heaters.
     * 
     * So, your input will be the positions of houses and heaters seperately, and your expected output will be the
     * minimum radius standard of heaters.
     * 
     * Note: Numbers of houses and heaters you are given are non-negative and will not exceed 25000. Positions of houses
     * and heaters you are given are non-negative and will not exceed 10^9. As long as a house is in the heaters' warm
     * radius range, it can be warmed. All the heaters follow your radius standard and the warm radius will the same.
     * 
     * Example 1:
     * 
     * Input: [1,2,3],[2] Output: 1
     * 
     * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the
     * houses can be warmed.
     * 
     * Example 2:
     * 
     * Input: [1,2,3,4],[1,4] Output: 1
     * 
     * 
     * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the
     * houses can be warmed.
     * 
     * </p>
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = -(index + 1);
            }
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
            result = Math.max(result, Math.min(dist1, dist2));
        }
        return result;
    }

    /**
     * 270. Closest Binary Search Tree Value
     * <p>
     * https://leetcode.com/problems/closest-binary-search-tree-value
     * <p>
     * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
     * 
     * Note: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is
     * closest to the target.
     * </p>
     * 
     * @param root
     * @param target
     * @return
     */
    public int closestValue(TreeNode root, double target) {
        int ret = root.val;
        double lastSub = Double.MAX_VALUE;
        while (root != null) {
            double curSub = Math.abs(target - root.val);
            if (curSub > lastSub) {
                break;
            }
            if (curSub < Math.abs(target - ret)) {
                ret = root.val;
            }
            root = target > root.val ? root.right : root.left;

        }
        return ret;
    }

    public static void main(String[] args) {
        int[] houses = { 1, 2, 3, 5, 15 };
        int[] heaters = { 2, 30 };
        System.out.println(new Solution().findRadius(houses, heaters));
    }
}
