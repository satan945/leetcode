/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

/**
 * 791. Custom Sort String
 * 
 * https://leetcode.com/problems/custom-sort-string
 *
 * 
 * @author abel created on 2018/3/2 下午3:57
 * @version $Id$
 */
public class CustomSortString {

    public String customSortString(String S, String T) {
        int[] tCount = new int[26];
        for (int i = 0; i < T.length(); i++) {
            tCount[T.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            while (tCount[ch - 'a'] > 0) {
                sb.append(ch);
                tCount[ch - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (tCount[i] > 0) {
                sb.append((char)('a' + i));
                tCount[i]--;
            }
        }
        return sb.toString();

    }
}
