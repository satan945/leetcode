/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.bitmanipulation.easy;

/**
 * @author Abel created on 2017/8/10 17:27
 * @version $Id$
 */
public class Solution {

    /**
     * 191. Number of 1 Bits
     * <p>
     * https://leetcode.com/problems/number-of-1-bits
     * <p>
     * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the
     * Hamming weight).
     * 
     * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function
     * should return 3.
     * 
     * Credits: Special thanks to @ts for adding this problem and creating all test cases.
     * </p>
     * https://leetcode.com/problems/number-of-1-bits/solution/
     * 
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }

    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    /**
     * 169. Majority Element
     * <p>
     * https://leetcode.com/problems/majority-element
     * <p>
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊
     * n/2 ⌋ times.
     * 
     * You may assume that the array is non-empty and the majority element always exist in the array.
     * 
     * Credits: Special thanks to @ts for adding this problem and creating all test cases.
     * </p>
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 1;
        for (int i=1;i<nums.length;i++) {
            if (count == 0) {
                result = nums[i];
                count++;
            } else if (nums[i] == result) {
                count++;
            } else {
                count--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 6, 5, 5 };
        System.out.println(new Solution().majorityElement(a));
    }
}
