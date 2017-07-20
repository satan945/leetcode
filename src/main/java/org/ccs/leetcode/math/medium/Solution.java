/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.math.medium;

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

    public static void main(String[] args) {
        System.out.println(new Solution().complexNumberMultiply("1+1i", "1+1i"));
    }
}
