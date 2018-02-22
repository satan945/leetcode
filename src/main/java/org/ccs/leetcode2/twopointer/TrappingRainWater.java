/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.twopointer;

/**
 * 42. Trapping Rain Water
 * 
 * https://leetcode.com/problems/trapping-rain-water/description/
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 * 
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * </p>
 * 
 * @author abel created on 2018/2/21 下午3:29
 * @version $Id$
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        return 0;
    }

    public int trap2(int[] height) {
        int len = height.length;
        int[] leftMaxHeight = new int[height.length];
        int[] rightMaxHeight = new int[height.length];
        int leftMax = 0, rightMax = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            leftMaxHeight[i] = leftMax;
            leftMax = Math.max(leftMax, height[i]);
        }

        for (int i = len - 1; i >= 0; i--) {
            rightMaxHeight[i] = rightMax;
            rightMax = Math.max(rightMax, height[i]);
        }

        for (int i = 0; i < len; i++) {
            int minHeight = Math.min(leftMaxHeight[i], rightMaxHeight[i]);
            int num = minHeight - height[i];
            sum += num > 0 ? num : 0;
        }

        return sum;
    }
}
