/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 8. String to Integer (atoi)
 * 
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 * 
 * @author abel created on 2018/3/8 下午11:16
 * @version $Id$
 */
public class StringToInt {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        long res = 0L;
        boolean negtive = false;

        boolean firstToken = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' ' && !firstToken) {
                continue;
            }
            if (ch != ' ' && !firstToken) {
                firstToken = true;
                if (ch == '-') {
                    negtive = true;
                    continue;

                } else if (ch == '+') {
                    continue;
                }
            }

            if (Character.isDigit(ch)) {
                res = res * 10 + (ch - '0');
            } else {
                break;
            }
            if (!negtive && res >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (negtive && -res <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return negtive ? (int) -res : (int) res;
    }

    public static void main(String[] args) {
        String a = "9223372036854775809";
        Deque<Integer> stac = new ArrayDeque<>();
        StringToInt stringToInt = new StringToInt();
        List<Integer> list = new LinkedList<>();
        System.out.println(stringToInt.myAtoi(a));
    }
}
