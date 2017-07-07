/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.array.easy;

import java.util.ArrayList;
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
     * https://leetcode.com/problems/intersection-of-two-arrays/#/description
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[] { 1 };
        int[] nums2 = new int[] { 1, 2 };
        System.out.println(solution.intersection(nums1, nums2));
    }
}
