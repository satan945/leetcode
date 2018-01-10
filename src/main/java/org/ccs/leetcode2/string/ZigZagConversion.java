/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

/**
 * 6. ZigZag Conversion
 * 
 * @author abel created on 2018/1/9 下午5:18
 * @version $Id$
 */
public class ZigZagConversion {

    public String convert(String s, int numRows) {
        if (s == null || numRows == 0 || s.length() < numRows) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            builders[i] = new StringBuilder();
        }
        char[] letters = s.toCharArray();
        int j = 0;
        while (j < letters.length) {
            for (int i = 0; i < numRows && j < s.length(); i++) {
                builders[i].append(letters[j++]);
            }
            for (int i = numRows - 2; i > 0 && j < s.length(); i--) {
                builders[i].append(letters[j++]);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < builders.length; i++) {
            res.append(builders[i].toString());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion solution = new ZigZagConversion();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
    }
}
