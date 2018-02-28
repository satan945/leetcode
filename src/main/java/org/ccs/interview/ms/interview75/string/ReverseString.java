/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.string;

/**
 * 344. Reverse String
 * 
 * https://leetcode.com/problems/reverse-string/description/
 * 
 * @author abel created on 2018/2/25 下午11:23
 * @version $Id$
 */
public class ReverseString {

    public String reverseString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
        return String.valueOf(chars);
    }
}
