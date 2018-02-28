/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms;

/**
 * 242. Valid Anagram
 * 
 * https://leetcode.com/problems/valid-anagram/description/
 * 
 * @author abel created on 2018/2/25 下午11:13
 * @version $Id$
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(map[i]!=0){
                return false;
            }
        }
        return true;

    }
}
