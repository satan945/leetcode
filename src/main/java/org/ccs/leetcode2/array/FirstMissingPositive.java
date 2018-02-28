/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 41. First Missing Positive
 *
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * <p>
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * </p>
 * 
 * @author abel created on 2018/2/25 下午4:45
 * @version $Id$
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int len = nums.length;
        int i = 0;
        while (i < len) {
            int target = nums[i];
            if (target <= 0 || target == i + 1 || target > len) {
                i++;
            } else if (nums[target - 1] != target) {
                swap(nums, target - 1, i);
            } else {
                i++;
            }
        }
        for (i = 0; i < len && nums[i] == i + 1; i++) {
        }
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        FirstMissingPositive solution = new FirstMissingPositive();
        System.out.println(solution.firstMissingPositive(new int[] { 3, 4, -1, 1 }));
    }
}
