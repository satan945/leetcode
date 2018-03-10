/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

/**
 * 316. Remove Duplicate Letters
 * 
 * @author abel created on 2018/3/5 下午9:48
 * @version $Id$
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

}
