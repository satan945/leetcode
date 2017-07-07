/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.string.easy;

import java.util.Arrays;

/**
 * @author Abel created on 2017/7/7 11:53
 * @version $Id$
 */
public class Solution {
    /**
     * 344 Reverse String
     * <p>
     * https://leetcode.com/problems/reverse-string
     *
     * @param s
     * @return
     */
    public String reverseString(String s) {
        int length = s.length();
        char[] a = s.toCharArray();
        char[] b = new char[length];
        int j = 0;
        for (int i = length - 1; i >= 0; i--) {
            b[j] = a[i];
            j++;
        }
        return new String(b);
    }

    /**
     * 389 Find the Difference
     * <p>
     * https://leetcode.com/problems/find-the-difference
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(tChars);
        Arrays.sort(sChars);
        for (int i = 0; i < tChars.length; i++) {
            if (i == sChars.length) {
                return tChars[i];
            }
            if (tChars[i] != sChars[i]) {
                return tChars[i];
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        String a = "123456  123123";
        Solution solution = new Solution();
        System.out.println(solution.reverseString(a));
        ;
    }
}
