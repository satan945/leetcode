/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

import org.ccs.utils.CommonUtils;

/**
 * <p>
 * given an integer array
 * 
 * move all negative numbers to the left [-1,3,0,-2,5] - > [-1,-2,0,3,5]
 * </p>
 * 
 * @author abel created on 2018/2/1 下午3:53
 * @version $Id$
 */
public class MoveInteger {

    public void moveIntegers(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }

        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] > 0 && nums[j] < 0) {
                CommonUtils.swapIntegerArray(nums, i, j);
                i++;
                j--;
            } else if (nums[j] > 0) {
                j--;
            } else if (nums[i] < 0) {
                i++;
            } else {
                i++;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 0,0,0,0 };
        MoveInteger solution = new MoveInteger();
        solution.moveIntegers(nums);
        System.out.println(nums);
    }
}
