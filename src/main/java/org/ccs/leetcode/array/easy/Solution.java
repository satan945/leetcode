/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Abel created on 2017/7/7 11:46
 * @version $Id$
 */
public class Solution {
    /**
     * 349. Intersection of Two Arrays
     * <p>
     * https://leetcode.com/problems/intersection-of-two-arrays
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        int max1 = 0;
        int max2 = 0;
        List<Integer> result = new ArrayList<Integer>();
        Set<Integer> allElements = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] > max1) {
                max1 = nums1[i];
            }
            allElements.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] > max2) {
                max2 = nums2[i];
            }
            allElements.add(nums2[i]);
        }

        int max = max1 > max2 ? max1 : max2;
        int[] mark1 = new int[max + 1];
        int[] mark2 = new int[max + 1];
        for (int i = 0; i < nums1.length; i++) {
            mark1[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            mark2[nums2[i]]++;
        }
        for (Integer i : allElements) {
            if (mark1[i] > 0 && mark2[i] > 0) {
                result.add(i);
            }
        }
        int[] retArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            retArray[i] = result.get(i);
        }
        return retArray;
    }

    /**
     * 
     * @param args
     */
    /**
     * 581. Shortest Unsorted Continuous Subarray
     * <p>
     * https://leetcode.com/problems/shortest-unsorted-continuous-subarray
     * <p>
     * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending
     * order, then the whole array will be sorted in ascending order, too.
     * 
     * You need to find the shortest such subarray and output its length.
     * 
     * Example 1: Input: [2, 6, 4, 8, 10, 9, 15]
     * 
     * Output: 5
     * 
     * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending
     * order.
     * 
     * Note: Then length of the input array is in range [1, 10,000].
     * 
     * The input array may contain duplicates, so ascending order here means <=.
     * </p>
     * todo
     * 
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        return 1;
    }

    /**
     * 561. Array Partition I
     *
     * <p>
     * https://leetcode.com/problems/array-partition-i/
     * <p>
     * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2,
     * b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
     * 
     * Example 1: Input: [1,4,3,2]
     * 
     * Output: 4
     * 
     * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
     * 
     * Note: n is a positive integer, which is in the range of [1, 10000]. All the integers in the array will be in the
     * range of [-10000, 10000].
     * </p>
     * 
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i += 2) {
            sum += nums[i];
        }
        return sum;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[] { 1 };
        int[] nums2 = new int[] { -1, -2, 1, 4, 2, 3 };
        // System.out.println(solution.intersection(nums1, nums2));
        System.out.println(solution.arrayPairSum(nums2));
    }
}
