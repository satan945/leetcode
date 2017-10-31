/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.twopointers.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author abel created on 2017/8/22 下午5:11
 * @version $Id$
 */
public class Solution {

    /**
     * 167. Two Sum II - Input array is sorted
     * <p>
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted
     * <p>
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a
     * specific target number.
     * 
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1
     * must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
     * 
     * You may assume that each input would have exactly one solution and you may not use the same element twice.
     * 
     * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
     * </p>
     * 
     * @param numbers
     * @param target
     * @return
     */

    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[] { -1, -1 };
        int low = 0, high = numbers.length - 1;
        while (low <= high) {
            if (target == numbers[low] + numbers[high]) {
                res[0] = low + 1;
                res[1] = high + 1;
                return res;
            }
            if (target > (numbers[low] + numbers[high])) {
                low++;
            }
            if (target < (numbers[low] + numbers[high])) {
                high--;
            }
        }
        return res;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i < numbers.length - 1; i++) {
            int rest = target - numbers[i];
            int index = binarySearch(numbers, i + 1, numbers.length - 1, rest);
            if (index != -1) {
                res[0] = i + 1;
                res[1] = index + 1;
                return res;
            }
        }
        return res;
    }

    private int binarySearch(int[] numbers, int l, int r, int rest) {
        while (l <= r) {
            int m = (l + r) / 2;
            if (numbers[m] == rest) {
                return m;
            }
            if (rest < numbers[m]) {
                r = m - 1;
            }
            if (rest > numbers[m]) {
                l = m + 1;
            }

        }
        return -1;
    }

    /**
     * 350. Intersection of Two Arrays II
     * <p>
     * https://leetcode.com/problems/intersection-of-two-arrays-ii
     * <p>
     * Given two arrays, write a function to compute their intersection.
     * 
     * Example:
     * 
     * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
     * 
     * Note:
     * 
     * Each element in the result should appear as many times as it shows in both arrays. The result can be in any
     * order.
     * 
     * Follow up:
     * 
     * What if the given array is already sorted? How would you optimize your algorithm? What if nums1's size is small
     * compared to nums2's size? Which algorithm is better? What if elements of nums2 are stored on disk, and the memory
     * is limited such that you cannot load all elements into the memory at once?
     * </p>
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> resultList = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            if (nums1[i] == nums2[j]) {
                resultList.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i]<nums2[j]){
                i++;
            }else {
                j++;
            }
        }
        int[] res = new int[resultList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resultList.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 0, 0, 3, 4 };
        Solution solution = new Solution();
        System.out.println(solution.twoSum(array, 0));
    }
}
