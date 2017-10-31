/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dp.hard;

/**
 * @author abel created on 2017/8/14 下午3:17
 * @version $Id$
 */
public class Solution {

    /**
     * 10. Regular Expression Matching
     * <p>
     * https://leetcode.com/problems/regular-expression-matching
     * <p>
     *
     * Implement regular expression matching with support for '.' and '*'.
     * 
     * '.' Matches any single character. '*' Matches zero or more of the preceding element.
     * 
     * The matching should cover the entire input string (not partial).
     * 
     * The function prototype should be: bool isMatch(const char *s, const char *p)
     * 
     * Some examples:
     * 
     * isMatch("aa","a") ? false isMatch("aa","aa") ? true isMatch("aaa","aa") ? false isMatch("aa", "a*") ? true
     * isMatch("aa", ".*") ? true isMatch("ab", ".*") ? true isMatch("aab", "c*a*b") ? true
     * </p>
     * 
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }
        if (s.equals(p)) {
            return true;
        }
        if (".*".equals(p)) {
            return true;
        }

        return true;
    }

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
        return 0.0;
    }
}
