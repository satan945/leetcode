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
        return null;
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


    public static void main(String[] args) {

        String a = "1+2+3x";
        String[] b = a.split("(?=\\+)|(?=-)");
        System.out.println(b);
        System.out.println(new Solution().complexNumberMultiply("1+1i", "1+1i"));
    }
}
