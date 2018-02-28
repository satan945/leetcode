/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.array;

/**
 * 169. Majority Element
 * 
 * <p>
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2
 * ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * </p>
 * 
 * @author abel created on 2018/2/23 下午4:44
 * @version $Id$
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int num = nums[0];
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                cnt++;
            } else if (cnt == 0) {
                num = nums[i];
            } else {
                cnt--;
            }
        }
        return num;
    }
}
