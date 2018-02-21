/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.binarysearch.medium;

/**
 * 162. Find Peak Element
 * <p>
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * </p>
 * 
 * @author abel created on 2018/2/14 下午10:25
 * @version $Id$
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }
}
