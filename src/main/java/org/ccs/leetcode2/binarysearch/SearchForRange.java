/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.binarysearch;

/**
 * 34. Search for a Range
 * 
 * @author abel created on 2018/1/10 下午3:39
 * @version $Id$
 */
public class SearchForRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] { -1, -1 };
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            }
        }
        if (l < 0 || l > nums.length - 1 || nums[l] != target) {
            return new int[] { -1, -1 };
        }
        int first = l;
        r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return new int[] { first, r };
    }

    public static void main(String[] args) {

        int[] nums = new int[] { 5, 7, 7, 8, 8, 10 };
        int target = 8;
        int[] nums2 = new int[] { 2, 2 };
        int target2 = 3;
        SearchForRange solution = new SearchForRange();
        System.out.println(solution.searchRange(nums2, target2));
    }
}
