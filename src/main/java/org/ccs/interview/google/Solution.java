/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

/**
 * @author abel created on 2017/12/29 下午6:30
 * @version $Id$
 */
public class Solution {

    /**
     * Repeated String Match
     * <p>
     * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of
     * it. If no such solution, return -1.
     * 
     * For example, with A = "abcd" and B = "cdabcdab".
     * 
     * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring
     * of A repeated two times ("abcdabcd").
     * 
     * Note: The length of A and B will be between 1 and 10000.
     * </p>
     * 
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch(String A, String B) {
        int count = 1;
        StringBuilder sbA = new StringBuilder(A);
        while (sbA.length() < B.length()) {
            sbA.append(A);
            count++;
        }
        if (sbA.indexOf(B) >= 0) {
            return count;
        }
        if (sbA.append(A).indexOf(B) > 0) {
            return count + 1;
        }
        return -1;

    }

    public static void main(String[] args) {
        String a = "a";
        String b = "aa";
        System.out.println(new Solution().repeatedStringMatch(a,b));
    }
}
