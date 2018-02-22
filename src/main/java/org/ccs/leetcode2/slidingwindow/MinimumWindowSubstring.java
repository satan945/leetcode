/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.twopointer;

import java.util.HashMap;
import java.util.Map;

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
        Map<Character, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            cntMap.put(t.charAt(i), cntMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        
    }
}
