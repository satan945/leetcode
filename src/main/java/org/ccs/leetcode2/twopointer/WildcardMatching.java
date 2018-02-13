/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.twopointer;

/**
 * 44. Wildcard Matching
 * 
 * @author Abel created on 2018/1/30 18:33
 * @version $Id$
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0, star = -1, match = 0;
        while (sIdx < s.length()) {
            if (pIdx < p.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?')) {
                sIdx++;
                pIdx++;
            } else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                star = pIdx;
                pIdx++;
                match = sIdx;
            } else if (star != -1) {
                pIdx = star + 1;
                match++;
                sIdx = match;
            } else {
                return false;
            }
        }
        while (pIdx < p.length() && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        return pIdx == p.length();
    }

    public static void main(String[] args) {
        String s = "abc";
        String p = "a?*bc";
        System.out.println(new WildcardMatching().isMatch(s,p));
    }
}
