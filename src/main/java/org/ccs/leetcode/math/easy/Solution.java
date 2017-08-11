/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.math.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

    /**
     * 66. Plus One
     * <p>
     * https://leetcode.com/problems/plus-one
     *
     * <p>
     * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
     * 
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     * 
     * The digits are stored such that the most significant digit is at the head of the list.
     * </p>
     * 
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        boolean needCarrier = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == digits.length - 1) {
                digits[i] = digits[i] + 1;
                if (digits[i] >= 10) {
                    needCarrier = true;
                    digits[i] = digits[i] % 10;
                } else {
                    needCarrier = false;
                    break;
                }
            } else {
                if (needCarrier) {
                    digits[i] = digits[i] + 1;
                    if (digits[i] >= 10) {
                        digits[i] = digits[i] % 10;
                    } else {
                        needCarrier = false;
                        break;
                    }
                }
            }
        }

        if (needCarrier) {
            int[] newDigits = new int[digits.length + 1];
            for (int i = newDigits.length - 1; i > 0; i--) {
                newDigits[i] = digits[i - 1];
            }
            newDigits[0] = 1;
            return newDigits;
        } else {
            return digits;
        }
    }

    /**
     * 9. Palindrome Number
     * <p>
     * https://leetcode.com/problems/palindrome-number
     *
     * <p>
     * Determine whether an integer is a palindrome. Do this without extra space.
     * 
     * click to show spoilers.
     * 
     * Some hints: Could negative integers be palindromes? (ie, -1)
     * 
     * If you are thinking of converting the integer to string, note the restriction of using extra space.
     * 
     * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that
     * the reversed integer might overflow. How would you handle such case?
     * 
     * There is a more generic way of solving this problem.
     * </p>
     * 
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        long reverse = 0;
        long xLong = (long) x;
        while (xLong > 0) {
            reverse = reverse * 10 + xLong % 10;
            xLong /= 10;
        }
        if (reverse > Integer.MAX_VALUE) {
            return false;
        }
        return reverse == x;
    }

    /**
     * 67. Add Binary
     * <p>
     * https://leetcode.com/problems/add-binary
     * <p>
     * Given two binary strings, return their sum (also a binary string).
     * 
     * For example, a = "11" b = "1" Return "100".
     * 
     * 
     * </p>
     * 
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        int result;
        while (i >= 0 || j >= 0) {
            int aVal = i <= 0 ? 0 : a.charAt(i--) - '0';
            int bVal = j <= 0 ? 0 : b.charAt(j--) - '0';
            result = aVal ^ bVal ^ carry;
            carry = (aVal + bVal + carry) >= 2 ? 1 : 0;
            sb.append(result);
        }
        return sb.reverse().toString();
    }

    /**
     * 12. Integer to Roman
     * <p>
     * https://leetcode.com/problems/integer-to-roman
     * <p>
     * Given an integer, convert it to a roman numeral.
     * 
     * Input is guaranteed to be within the range from 1 to 3999.
     * </p>
     * 
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 13. Roman to Integer
     * <p>
     * https://leetcode.com/problems/roman-to-integer
     * <p>
     *
     * </p>
     * 
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("XC", 90);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, i + 1);
            result += map.get(sub);
        }
        return result;
    }

    /**
     * 202. Happy Number
     * <p>
     * https://leetcode.com/problems/happy-number
     * <p>
     * Write an algorithm to determine if a number is "happy".
     * 
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the
     * number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will
     * stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1
     * are happy numbers.
     * 
     * Example: 19 is a happy number
     * 
     * 12 + 92 = 82
     * 
     * 82 + 22 = 68
     * 
     * 62 + 82 = 100
     * 
     * 12 + 02 + 02 = 1
     * 
     * Credits: Special thanks to @mithmatt and @ts for adding this problem and creating all test cases.
     * </p>
     * 
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (set.add(n)) {
            int squareSum = 0;
            while (n > 0) {
                int tail = n % 10;
                squareSum += tail * tail;
                n /= 10;
            }
            if (squareSum == 1) {
                return true;
            } else {
                n = squareSum;
            }
        }
        return false;
    }

    /**
     * 263. Ugly Number
     * 
     * <p>
     * https://leetcode.com/problems/ugly-number
     * <p>
     * Write a program to check whether a given number is an ugly number.
     * 
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14
     * is not ugly since it includes another prime factor 7.
     * 
     * Note that 1 is typically treated as an ugly number.
     * 
     * Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     * 
     * https://discuss.leetcode.com/topic/21873/simple-java-solution-with-explanation
     * </p>
     * 
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num == 1) {
            return true;
        }
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
            // num=num>>1;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }

    /**
     * 598. Range Addition II
     * 
     * <p>
     * https://leetcode.com/problems/range-addition-ii
     * <p>
     * Given an m * n matrix M initialized with all 0's and several update operations.
     * 
     * Operations are represented by a 2D array, and each operation is represented by an array with two positive
     * integers a and b, which means M[i][j] should be added by one for all 0 <= i < a and 0 <= j < b.
     * 
     * You need to count and return the number of maximum integers in the matrix after performing all the operations.
     * 
     * Example 1:
     * 
     * Input: m = 3, n = 3 operations = [[2,2],[3,3]]
     * 
     * Output: 4 Explanation: Initially, M = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
     * 
     * After performing [2,2], M = [[1, 1, 0], [1, 1, 0], [0, 0, 0]]
     * 
     * After performing [3,3], M = [[2, 2, 1], [2, 2, 1], [1, 1, 1]]
     * 
     * So the maximum integer in M is 2, and there are four of it in M. So return 4.
     * 
     * Note:
     * 
     * The range of m and n is [1,40000].
     * 
     * The range of a is [1,m], and the range of b is [1,n].
     * 
     * The range of operations size won't exceed 10,000.
     * </p>
     * 
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op: ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 9 };
        Solution solution = new Solution();
        // System.out.println(solution.plusOne(nums));
        System.out.println(solution.isPalindrome(10001));
    }
}
