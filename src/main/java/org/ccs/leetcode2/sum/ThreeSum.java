/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * <p>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in
 * the array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 * </p>
 * 
 * @author Abel created on 2018/1/24 15:59
 * @version $Id$
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1, high = nums.length - 1, target = -nums[i];
            while (low < high) {
                if (nums[low] + nums[high] > target) {
                    high--;
                } else if (nums[low] + nums[high] < target) {
                    low++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    high--;
                    low++;
                    while (low < high && nums[high] == nums[high + 1]) {
                        high--;
                    }
                    while (low < high && nums[low] == nums[low - 1]) {
                        low++;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        System.out.println(solution.threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
        System.out.println(solution.threeSum(new int[] { 0, 0, 0 }));
        System.out.println(solution.threeSum(new int[] { 0, 0, 0, 0 }));
    }
}
