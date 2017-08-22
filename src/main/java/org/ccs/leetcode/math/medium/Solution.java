/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.math.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Abel created on 2017/7/7 11:55
 * @version $Id$
 */
public class Solution {

    /**
     * 537. Complex Number Multiplication
     * <p>
     * https://leetcode.com/problems/complex-number-multiplication
     * <p>
     * Given two strings representing two complex numbers.
     * 
     * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
     * 
     * Example 1: Input: "1+1i", "1+1i" Output: "0+2i" Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you
     * need convert it to the form of 0+2i. Example 2: Input: "1+-1i", "1+-1i" Output: "0+-2i" Explanation: (1 - i) * (1
     * - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i. Note:
     * 
     * The input strings will not have extra blank. The input strings will be given in the form of a+bi, where the
     * integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
     * </p>
     * 
     * @param a
     * @param b
     * @return
     */
    public String complexNumberMultiply(String a, String b) {
        int indexA = a.indexOf("+");
        int indexB = b.indexOf("+");
        String a1 = a.substring(0, indexA);
        String a2 = a.substring(indexA + 1, a.length() - 1);
        String b1 = b.substring(0, indexB);
        String b2 = b.substring(indexB + 1, b.length() - 1);
        int a1Integer = Integer.parseInt(a1);
        int b1Integer = Integer.parseInt(b1);
        int a2Integer = Integer.parseInt(a2);
        int b2Integer = Integer.parseInt(b2);
        if (!check(a1Integer) || !check(b1Integer) || !check(a2Integer) || !check(b2Integer)) {
            return null;
        }

        int a1b1 = a1Integer * b1Integer;
        int a1b2 = a1Integer * b2Integer;
        int b1a2 = b1Integer * a2Integer;
        int a2b2 = a2Integer * b2Integer;

        System.out.println(a1b1 + "::" + a2b2);
        System.out.println(a1b1 - a2b2);
        int pre = a1b1 - a2b2;
        int last = a1b2 + b1a2;
        return pre + "+" + last + "i";

    }

    private boolean check(int integer) {
        if (integer > 100 || integer < -100) {
            return false;
        }
        return true;
    }

    /**
     * 43. Multiply Strings
     * 
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos)
            if (!(sb.length() == 0 && p == 0))
                sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * 29. Divide Two Integers
     *
     * <p>
     * Divide two integers without using multiplication, division and mod operator.
     * 
     * If it is overflow, return MAX_INT.
     * </p>
     * 
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        int sign = 1;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            sign = -1;
        }
        long absDividend = Math.abs(dividend);
        long absDivisor = Math.abs(divisor);
        if (absDividend == 0 || absDividend < absDivisor) {
            return 0;
        }
        long lans = longDivide(absDividend, absDivisor);
        int ans;
        if (lans >= Integer.MAX_VALUE) {
            ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }

        return ans;
    }

    private long longDivide(long absDividend, long absDivisor) {
        if (absDividend < absDivisor) {
            return 0;
        }
        long sum = absDivisor;
        long multiple = 1;
        while ((sum + sum) <= absDividend) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + longDivide(absDividend - sum, absDivisor);
    }

    /**
     * 640. Solve the Equation
     * <p>
     * https://leetcode.com/problems/solve-the-equation
     * <p>
     *
     * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only
     * '+', '-' operation, the variable x and its coefficient.
     * 
     * If there is no solution for the equation, return "No solution".
     * 
     * If there are infinite solutions for the equation, return "Infinite solutions".
     * 
     * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
     * 
     * Example 1: Input: "x+5-3+x=6+x-2" Output: "x=2"
     * 
     * Example 2: Input: "x=x" Output: "Infinite solutions"
     * 
     * Example 3: Input: "2x=x" Output: "x=0"
     * 
     * Example 4: Input: "2x+3x-6x=x+2" Output: "x=-1"
     * 
     * Example 5: Input: "x=x+2" Output: "No solution"
     * </p>
     * 
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        if (equation == null || equation.isEmpty()) {
            return null;
        }
        String[] equationArray = equation.split("=");
        if (equation.length() == 1) {
            return null;
        }
        String left = equationArray[0];
        String right = equationArray[1];

        return null;
    }

    /**
     * 264. Ugly Number II
     * <p>
     * https://leetcode.com/problems/ugly-number-ii
     * <p>
     * Write a program to find the n-th ugly number.
     * 
     * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9,
     * 10, 12 is the sequence of the first 10 ugly numbers.
     * 
     * Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
     * 
     * Credits: Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     * </p>
     * 
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int twoCount = 0, threeCount = 0, fiveCount = 0;
        List<Integer> uglyNumberList = new ArrayList<>();
        uglyNumberList.add(1);
        for (int i = 1; i < n; i++) {
            int nextUgly = Math.min(uglyNumberList.get(twoCount) * 2,
                    Math.min(uglyNumberList.get(threeCount) * 3, uglyNumberList.get(fiveCount) * 5));
            uglyNumberList.add(nextUgly);
            if (uglyNumberList.get(twoCount) * 2 == nextUgly) {
                twoCount++;
            }
            if (uglyNumberList.get(threeCount) * 3 == nextUgly) {
                threeCount++;
            }
            if (uglyNumberList.get(fiveCount) * 5 == nextUgly) {
                fiveCount++;
            }
        }
        return uglyNumberList.get(n - 1);
    }

    /**
     * 319. Bulb Switcher
     * <p>
     * https://leetcode.com/problems/bulb-switcher/description/
     *
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
     * https://discuss.leetcode.com/topic/39558/share-my-o-1-solution-with-explanation
     * 
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {

        String a = "1+2+3x";
        String[] b = a.split("(?=\\+)|(?=-)");
        System.out.println(b);
        System.out.println(new Solution().complexNumberMultiply("1+1i", "1+1i"));
    }
}
