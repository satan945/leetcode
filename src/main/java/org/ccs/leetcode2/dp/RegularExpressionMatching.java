/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

/**
 * 10. Regular Expression Matching
 * <p>
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character. '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * 
 * isMatch("aa","a") → false
 * 
 * isMatch("aa","aa") → true
 * 
 * isMatch("aaa","aa") → false
 * 
 * isMatch("aa", "a*") → true
 * 
 * isMatch("aa", ".*") → true
 * 
 * isMatch("ab", ".*") → true
 * 
 * isMatch("aab", "c*a*b") → true
 * </p>
 * 
 * @author abel created on 2018/1/22 下午6:52
 * @version $Id$
 */
public class RegularExpressionMatching {
    /**
     * <p>
     * 1, If p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1];
     * 
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     * 
     * 3, If p.charAt(j) == '*':
     * 
     * here are two sub conditions:
     * 
     * 1 if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
     * 
     * 2 if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.': dp[i][j] = dp[i-1][j] //in this case, a* counts as
     * multiple a or dp[i][j] = dp[i][j-1] in this case, a* counts as single a or dp[i][j] = dp[i][j-2] // in this case,
     * a* counts as empty
     * </p>
     * 
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {

        }
        return false;
    }
}
