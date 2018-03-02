/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.binarysearch;

/**
 * 4. Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * </p>
 * 
 * @author abel created on 2018/2/21 下午5:12
 * @version $Id$
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int k = (len1 + len2 + 1) / 2;
        int l = 0, r = len1;
        int m1, m2;
        while (l < r) {
            m1 = l + (r - l) / 2;
            m2 = k - m1;
            if (nums1[m1] < nums2[m2 - 1]) {
                l = m1 + 1;
            } else {
                r = m1;
            }
        }
        m1 = l;
        m2 = k - l;
        int leftMax = Math.max(m1 <= 0 ? Integer.MIN_VALUE : nums1[m1 - 1],
                m2 <= 0 ? Integer.MIN_VALUE : nums2[m2 - 1]);
        if ((len1 + len2) % 2 != 0) {
            return (double) leftMax;
        }
        int rightMin = Math.min(m1 >= len1 ? Integer.MAX_VALUE : nums1[m1], m2 >= len2 ? Integer.MAX_VALUE : nums2[m2]);
        return (double) (leftMax + rightMin) / 2;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 > len2) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        int k = (len1 + len2 + 1) / 2;
        int l = 0, r = nums1.length;
        while (l <= r) {
            int mid1 = l + (r - l) / 2;
            int mid2 = k - mid1;
            int leftMax1 = mid1 <= 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int rightMin1 = mid1 >= len1 ? Integer.MAX_VALUE : nums1[mid1];
            int leftMax2 = mid2 <= 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int rightMin2 = mid2 >= len2 ? Integer.MAX_VALUE : nums2[mid2];
            if (Math.max(leftMax1, leftMax2) <= Math.min(rightMin1, rightMin2)) {
                if ((len1 + len2) % 2 == 0) {
                    return (double) (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2;
                } else {
                    return (double) (Math.max(leftMax1, leftMax2));
                }
            } else if (leftMax1 > rightMin2) {
                r = mid1 -1;
            } else {
                l = mid1 + 1;
            }
        }
        return 0.0;
    }

}
