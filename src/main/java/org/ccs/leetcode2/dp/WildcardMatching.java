/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.dp;

/**
 * 44. Wildcard Matching
 * <p>
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * 
 * </p>
 * 
 * @author abel created on 2018/1/30 下午5:23
 * @version $Id$
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }
        if (p.equals("?*")) {
            return true;
        }
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[0][0] = true;
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            
        }
        return match[s.length()][p.length()];
    }
}
