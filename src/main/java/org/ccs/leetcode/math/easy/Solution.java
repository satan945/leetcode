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
        for (int[] op : ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }

    /**
     * 415. Add Strings
     * <p>
     * https://leetcode.com/problems/add-strings
     * <p>
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     *
     * Note:
     *
     * The length of both num1 and num2 is < 5100. Both num1 and num2 contains only digits 0-9. Both num1 and num2 does
     * not contain any leading zero. You must not use any built-in BigInteger library or convert the inputs to integer
     * directly.
     * </p>
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        return "";
    }

    /**
     * 172. Factorial Trailing Zeroes
     * <p>
     * https://leetcode.com/problems/factorial-trailing-zeroes
     * <p>
     * Given an integer n, return the number of trailing zeroes in n!.
     * 
     * Note: Your solution should be in logarithmic time complexity.
     * </p>
     * <p>
     * This question is pretty straightforward.
     * 
     * Because all trailing 0 is from factors 5 * 2.
     * 
     * But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5
     * factors. In the n! operation, factors 2 is always ample. So we just count how many 5 factors in all number from 1
     * to n.
     * </p>
     * 
     * <p>
     * 10 is the product of 2 and 5. In n!, we need to know how many 2 and 5, and the number of zeros is the minimum of
     * the number of 2 and the number of 5.
     * 
     * Since multiple of 2 is more than multiple of 5, the number of zeros is dominant by the number of 5.
     * 
     * Here we expand
     * 
     * 2147483647! =2 * 3 * ...* 5 ... *10 ... 15* ... * 25 ... * 50 ... * 125 ... * 250... =2 * 3 * ...* 5 ... *
     * (5^1*2)...(5^1*3)...*(5^2*1)...*(5^2*2)...*(5^3*1)...*(5^3*2)... (Equation 1) We just count the number of 5 in
     * Equation 1.
     * 
     * Multiple of 5 provides one 5, multiple of 25 provides two 5 and so on.
     * 
     * Note the duplication: multiple of 25 is also multiple of 5, so multiple of 25 only provides one extra 5.
     * 
     * Here is the basic solution:
     * 
     * return n/5 + n/25 + n/125 + n/625 + n/3125+...; You can easily rewrite it to a loop.
     * </p>
     * 
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    /**
     * 326. Power of Three
     * <p>
     * https://leetcode.com/problems/power-of-three
     * <p>
     * Given an integer, write a function to determine if it is a power of three.
     * </p>
     * 
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        double a = Math.log(n) / Math.log(3);
        return Math.abs(a - Math.rint(a)) <= 0.00000000000001;
    }

    public boolean isPowerOfThree2(int n) {
        return (n > 0 && 1162261467 % n == 0);

    }

    /**
     * 231. Power of Two
     * <p>
     * https://leetcode.com/problems/power-of-two
     * <p>
     * Given an integer, write a function to determine if it is a power of two.
     * 
     * </p>
     * https://leetcode.com/problems/power-of-two/discuss/
     * 
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    /**
     * 342. Power of Four
     * <p>
     * https://leetcode.com/problems/power-of-four
     * <p>
     * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
     * 
     * Example: Given num = 16, return true. Given num = 5, return false.
     * 
     * Follow up: Could you solve it without loops/recursion?
     * </p>
     * 
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        if (num <= 0) {
            return false;
        }
        String string = Integer.toBinaryString(num).substring(1);
        return string.length() % 2 == 0 && !string.contains("1");

    }

    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    /**
     * 319. Bulb Switcher
     * <p>
     * https://leetcode.com/problems/bulb-switcher
     * <p>
     * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
     * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith
     * round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on
     * after n rounds.
     * 
     * Example:
     * 
     * Given n = 3.
     * 
     * At first, the three bulbs are [off, off, off]. After first round, the three bulbs are [on, on, on]. After second
     * round, the three bulbs are [on, off, on]. After third round, the three bulbs are [on, off, off].
     * 
     * So you should return 1, because there is only one bulb is on.
     * </p>
     * 
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    /**
     * 507. Perfect Number
     * <p>
     * https://leetcode.com/problems/perfect-number
     * <p>
     * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except
     * itself.
     * 
     * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
     * Example: Input: 28 Output: True Explanation: 28 = 1 + 2 + 4 + 7 + 14 Note: The input number n will not exceed
     * 100,000,000. (1e8)
     * </p>
     * 
     * https://leetcode.com/problems/perfect-number/solution
     * 
     * @param num
     * @return
     */
    public boolean checkPerfectNumberSqrt(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        return sum - num == num;
    }

    public boolean checkPerfectNumberBrute(int num) {
        if (num < 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
            if (sum > num) {
                return false;
            }
        }
        return sum == num;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 9 };
        Solution solution = new Solution();
        // System.out.println(solution.plusOne(nums));
        // System.out.println(solution.isPalindrome(10001));
        // System.out.println(Math.sqrt(9));
        System.out.println(solution.isPowerOfThree(243));
        System.out.println(solution.isPowerOfTwo(9));

    }
}
