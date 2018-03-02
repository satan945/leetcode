/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.slidingwindow;

/**
 * 76. Minimum Window Substring
 * 
 * https://leetcode.com/problems/minimum-window-substring
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 * 
 * For example, S = "ADOBECODEBANC" T = "ABC" Minimum window is "BANC".
 * 
 * Note: If there is no such window in S that covers all characters in T, return the empty string "".
 * 
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * </p>
 * 
 * @author abel created on 2018/2/22 下午2:12
 * @version $Id$
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || s.length() < t.length() || s.length() == 0) {
            return "";
        }
        int[] tCnt = new int[256];
        String res = "";

        for (int i = 0; i < t.length(); i++) {
            tCnt[t.charAt(i)]++;
        }
        int left = findNext(s, 0, tCnt);
        if (left == s.length()) {
            return "";
        }
        int[] sCnt = new int[256];
        int right = left;
        int match = 0;
        while (right < s.length()) {
            char rChar = s.charAt(right);
            if (sCnt[rChar] < tCnt[rChar]) {
                match++;
            }
            sCnt[rChar]++;
            while (left < s.length() && match == t.length()) {
                int curLen = right - left + 1;
                if (res.isEmpty() || res.length() > curLen) {
                    res = s.substring(left, left + curLen);
                    if (curLen == t.length()) {
                        return res;
                    }
                }
                char lChar = s.charAt(left);
                if (sCnt[lChar] <= tCnt[lChar]) {
                    match--;
                }
                sCnt[lChar]--;
                left = findNext(s, left + 1, tCnt);
            }
            right = findNext(s, right + 1, tCnt);
        }
        return res;

    }

    private int findNext(String s, int pos, int[] tCnt) {
        while (pos < s.length()) {
            char ch = s.charAt(pos);
            if (tCnt[ch] > 0) {
                return pos;
            }
            pos++;
        }
        return pos;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution= new MinimumWindowSubstring();
        solution.minWindow("a","a");
    }
}
