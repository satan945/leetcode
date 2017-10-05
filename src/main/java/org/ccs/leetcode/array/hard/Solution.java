/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.array.hard;

/**
 * @author abel created on 2017/8/29 下午2:03
 * @version $Id$
 */
public class Solution {

    /**
     * 4. Median of Two Sorted Arrays
     * <p>
     * https://leetcode.com/problems/median-of-two-sorted-arrays
     * <p>
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * 
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * 
     * Example 1: nums1 = [1, 3] nums2 = [2]
     * 
     * The median is 2.0 Example 2: nums1 = [1, 2] nums2 = [3, 4]
     * 
     * The median is (2 + 3)/2 = 2.5
     * 
     * </p>
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int iMin = 0, iMax = m, midLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = midLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = iMin + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = iMax - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2;
            }
        }
        return 0.0;

    }

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * <p>
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
     * <p>
     * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates are allowed?
     * 
     * Would this affect the run-time complexity? How and why? Suppose an array sorted in ascending order is rotated at
     * some pivot unknown to you beforehand.
     * 
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * 
     * Find the minimum element.
     * 
     * The array may contain duplicates.
     * 
     * 
     * </p>
     * 
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        return 0;
    }
}
