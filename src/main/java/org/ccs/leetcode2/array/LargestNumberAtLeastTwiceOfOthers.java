/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 747. Largest Number At Least Twice of Others
 * <p>
 * In a given integer array nums, there is always exactly one largest element.
 * 
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 * 
 * If it is, return the index of the largest element, otherwise return -1.
 * </p>
 * 
 * @author abel created on 2018/2/1 下午4:57
 * @version $Id$
 */
public class LargestNumberAtLeastTwiceOfOthers {

    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int first = -1;
        int firstPos = -1;
        int second = -1;
        int secondPos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > first) {
                second = first;
                secondPos = firstPos;
                first = nums[i];
                firstPos = i;
            } else if (nums[i] > second) {
                second = nums[i];
                secondPos = i;
            }
        }
        if (secondPos == -1) {
            return firstPos;
        }
        if (second == 0) {
            return firstPos;
        }
        if (first / second >= 2) {
            return firstPos;
        }
        return -1;
    }

    public static void main(String[] args) {
        LargestNumberAtLeastTwiceOfOthers solution = new LargestNumberAtLeastTwiceOfOthers();
        System.out.println(solution.dominantIndex(new int[] { 0, 0, 3, 2 }));
    }

}
