/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.array.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
        List<Integer> result = new ArrayList<>();
        Set<Integer> allElements = new HashSet<>();
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
     * 
     * @param nums
     * @return
     * 
     *         https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] nums2 = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums2);
        int start = nums.length - 1, end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums2[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end > start ? (end - start) + 1 : 0;
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

    /**
     * 643. Maximum Average Subarray I
     * <p>
     * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum
     * average value. And you need to output the maximum average value.
     *
     * Example 1: Input: [1,12,-5,-6,50,3], k = 4 Output: 12.75 Explanation: Maximum average is (12-5-6+50)/4 = 51/4 =
     * 12.75 Note: 1 <= k <= n <= 30,000. Elements of the given array will be in the range [-10,000, 10,000].
     * <p>
     *
     * </p>
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        double res = sum[k - 1] * 1.00 / k;
        for (int i = k; i < sum.length; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.00 / k);
        }
        return res;
    }

    public double findMaxAverageSlideWindow(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double res = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i];
            sum = sum - nums[i - k];
            res = Math.max(res, sum);
        }
        return res / k;
    }

    /**
     * 27. Remove Element
     * <p>
     * https://leetcode.com/problems/remove-element
     * <p>
     * Given an array and a value, remove all instances of that value in place and return the new length.
     *
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     *
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     *
     * Example: Given input array nums = [3,2,2,3], val = 3
     *
     * Your function should return length = 2, with the first two elements of nums being 2.
     * </p>
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /**
     * 88. Merge Sorted Array
     *
     * <p>
     * https://leetcode.com/problems/merge-sorted-array
     * <p>
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     *
     * Note: You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
     * elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
     * </p>
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0 && i >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    /**
     * 35. Search Insert Position
     * <p>
     * https://leetcode.com/problems/search-insert-position
     * <p>
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where
     * it would be if it were inserted in order.
     * 
     * You may assume no duplicates in the array.
     * 
     * Here are few examples.
     * 
     * [1,3,5,6], 5 → 2
     * 
     * [1,3,5,6], 2 → 1
     * 
     * [1,3,5,6], 7 → 4
     * 
     * [1,3,5,6], 0 → 0
     * </p>
     * 
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 217. Contains Duplicate
     * <p>
     * https://leetcode.com/problems/contains-duplicate
     * <p>
     * Given an array of integers, find if the array contains any duplicates. Your function should return true if any
     * value appears at least twice in the array, and it should return false if every element is distinct.
     * </p>
     * Array HashTable
     * 
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 219. Contains Duplicate II
     * <p>
     * https://leetcode.com/problems/contains-duplicate-ii
     * <p>
     * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
     * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
     * </p>
     * 
     * @param nums
     * @param k
     * @return Array HashTable
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer existIndex = map.get(nums[i]);
            if (existIndex == null) {
                map.put(nums[i], i);
            } else {
                if (i - existIndex <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * 665. Non-decreasing Array
     * <p>
     * https://leetcode.com/problems/non-decreasing-array
     * <p>
     * Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1
     * element.
     * 
     * We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
     * 
     * Example 1:
     * 
     * Input: [4,2,3]
     * 
     * Output: True
     * 
     * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
     * 
     * Example 2: Input: [4,2,1]
     * 
     * Output: False
     * 
     * Explanation: You can't get a non-decreasing array by modify at most one element.
     * </p>
     * 
     * @param nums
     * @return todo
     */
    public boolean checkPossibility(int[] nums) {
        return false;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = new int[] { 1 };
        int[] nums2 = new int[] { 4, 2, 3 };
        // System.out.println(solution.intersection(nums1, nums2));
        // System.out.println(solution.arrayPairSum(nums2));
        // System.out.println(solution.findMaxAverageSlideWindow(nums2, 4));
        // System.out.println(solution.searchInsert(new int[] { 1 }, 0));
        System.out.println(solution.checkPossibility(nums2));
    }

}
