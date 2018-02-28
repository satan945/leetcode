/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.string;

/**
 * 541. Reverse String II
 * 
 * https://leetcode.com/problems/reverse-string-ii
 * 
 * <p>
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the
 * start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but
 * greater than or equal to k characters, then reverse the first k characters and left the other as original.
 * </p>
 * 
 * @author abel created on 2018/2/25 下午11:26
 * @version $Id$
 */
public class ReverseStringII {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i += k * 2) {
            System.out.println(i);
            reverse(chars, i, i + k-1);
        }
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int start, int end) {
        if (end > chars.length - 1) {
            end = chars.length - 1;
        }
        int i = start, j = end;
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        ReverseStringII reverseStringII = new ReverseStringII();
        System.out.println(reverseStringII.reverseStr("abcdefg",2));
    }
}
