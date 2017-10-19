/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bst.medium;

/**
 * @author abel created on 2017/10/5 下午8:39
 * @version $Id$
 */
public class Solution {
    /**
     * 220. Contains Duplicate III
     * <p>
     * https://leetcode.com/problems/contains-duplicate-iii
     * <p>
     * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
     * absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at
     * most k.
     * </p>
     * 
     * https://leetcode.com/problems/contains-duplicate-iii/solution/
     * 
     * 
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length <= 1) {
            return false;
        }
        for (int i = 0; i <= nums.length - 2; i++) {
            for (int j = i + 1; j <= i + k && j <= nums.length - 1; j++) {
                long sub = (long)nums[j] - (long)nums[i];
                long sub1 = sub < 0 ? -sub : sub;
                long t1 = (long) t;
                if (sub1 <= t1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 2147483647,-2147483647 };
        System.out.println(new Solution().containsNearbyAlmostDuplicate(nums, 1, 2147483647));
    }

}
