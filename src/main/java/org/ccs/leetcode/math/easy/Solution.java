/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.math.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Abel created on 2017/7/7 11:51
 * @version $Id$
 */
public class Solution {
    /**
     * 258. Add Digits
     * <p>
     *
     * https://leetcode.com/problems/add-digits
     * <p>
     * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
     * 
     * For example:
     * 
     * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
     * 
     * Follow up: Could you do it without any loop/recursion in O(1) runtime?
     * 
     * Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     * 
     * Hint 1 A naive implementation of the above process is trivial. Could you come up with other methods?
     *
     * Hint 2 What are all the possible results?
     *
     * Hint 3 How do they occur, periodically or randomly?
     *
     * Hint 4 You may find this Wikipedia article useful. https://en.wikipedia.org/wiki/Digital_root
     * </p>
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        } else if (num % 9 == 0) {
            return 9;

        } else {
            return num % 9;
        }
    }

    /**
     * 283 Move Zeroes
     * <p>
     * https://leetcode.com/problems/move-zeroes
     * <p>
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
     * the non-zero elements.
     *
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     *
     * Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.
     * Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     * </p>
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        List<Integer> result = new ArrayList<Integer>();
        int count = 0;

        for (int pos = 0; pos < length; pos++) {
            if (nums[pos] != 0) {
                result.add(nums[pos]);
            } else {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            result.add(0);
        }
        for (int j = 0; j < length; j++) {
            nums[j] = result.get(j);
        }
    }

    /**
     * 283 Move Zeroes
     * <p>
     * https://leetcode.com/problems/move-zeroes
     * <p>
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
     * the non-zero elements.
     * 
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     * 
     * Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.
     * Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     * </p>
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int count = 0; // count of zeros
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 0)
                count++;
            else if (count != 0)
                nums[i - count] = nums[i];
        }
        for (int i = length - count; i < length; i++)
            nums[i] = 0;
    }

    /**
     * 292 Nim Game
     * <P>
     * https://leetcode.com/problems/nim-game
     * <p>
     * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of
     * you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the
     * first turn to remove the stones.
     * 
     * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you
     * can win the game given the number of stones in the heap.
     * 
     * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you
     * remove, the last stone will always be removed by your friend.
     * 
     * Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     * </p>
     * 
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return !(n % 4 == 0);
    }

    /**
     * 7 Reverse Integer
     * <p>
     * https://leetcode.com/problems/reverse-integer
     * <p>
     * Reverse digits of an integer.
     * 
     * Example1: x = 123, return 321 Example2: x = -123, return -321
     * 
     * click to show spoilers.
     * 
     * Note: The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer
     * overflows.
     * </p>
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        String value = String.valueOf(x);
        long resultLong;
        StringBuilder stringBuilder;
        if (x < 0) {
            stringBuilder = new StringBuilder(value.substring(1));
            resultLong = Long.parseLong("-" + stringBuilder.reverse().toString());
        } else {
            stringBuilder = new StringBuilder(value);
            resultLong = Long.parseLong(stringBuilder.reverse().toString());
        }
        if (resultLong > Integer.MAX_VALUE || resultLong < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) resultLong;
    }

    /**
     * 628. Maximum Product of Three Numbers
     * <p>
     * Given an integer array, find three numbers whose product is maximum and output the maximum product.
     * <p>
     * https://leetcode.com/problems/maximum-product-of-three-numbers
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length;
        int pro1 = nums[0] * nums[1];
        int pro2 = nums[l - 2] * nums[l - 3];
        if (pro1 < 0) {
            return nums[l - 1] * nums[l - 2] * nums[l - 3];
        } else {
            return pro1 > pro2 ? pro1 * nums[l - 1] : pro2 * nums[l - 1];
        }

    }
}
