/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
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
     * https://leetcode.com/problems/add-digits/#/description
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
     * https://leetcode.com/problems/maximum-product-of-three-numbers/#/description
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
