/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

import java.util.Arrays;

/**
 * 1，有两个String， 只能判断它们是不是只有一组swap, e.g. conSerVe, conVerSe, 只要交换两个位置的S和V就行了, 这个只要pass一遍就行，然后问了复杂度O(n).

 * @author abel created on 2018/3/4 下午9:40
 * @version $Id$
 */
public class OneSwap {

    public boolean isOneSwap(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        int count = 0;
        char[] sChars = new char[2];
        char[] tChars = new char[2];
        int sCur = 0, tCur = 0;
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sChar != tChar) {
                count++;
                sChars[sCur++] = sChar;
                tChars[tCur++] = tChar;
            }
            if (count > 2) {
                return false;
            }
        }
        if (count != 2) {
            return false;
        }
        Arrays.sort(tChars);
        Arrays.sort(sChars);
        return String.valueOf(tChars).equals(String.valueOf(sChars));
    }

    public static void main(String[] args) {
        String s = "conSerVe";
        String t = "conVerSe";
        System.out.println(new OneSwap().isOneSwap(s, t));
    }
}
