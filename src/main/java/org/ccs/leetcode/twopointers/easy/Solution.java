/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.twopointers.easy;

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

    public static void main(String[] args) {
        int[] array = new int[] { 0, 0, 3, 4 };
        Solution solution = new Solution();
        System.out.println(solution.twoSum(array, 0));
    }
}
