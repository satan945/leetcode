/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

import java.util.Arrays;
import java.util.List;

/**
 * 800. Similar RGB Color
 *
 * 
 * @author abel created on 2018/3/18 下午12:57
 * @version $Id$
 */
public class SimilarRGBColor {

    private int sub = Integer.MAX_VALUE;

    public String similarRGB(String color) {
        String res = "";
        List<Integer> hexList = Arrays.asList(0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB,
                0xCC, 0xDD, 0xEE, 0xFF);
        int decimalVal1 = getDecimal(color.substring(1, 3));
        int decimalVal2 = getDecimal(color.substring(3, 5));
        int decimalVal3 = getDecimal(color.substring(5, 7));

        for (int i = 0; i < hexList.size(); i++) {
            int val1 = hexList.get(i);
            for (int j = 0; j < hexList.size(); j++) {
                int val2 = hexList.get(j);

                for (int k = 0; k < hexList.size(); k++) {
                    int val3 = hexList.get(k);

                    int val = (decimalVal1 - val1) * (decimalVal1 - val1)//
                            + (decimalVal2 - val2) * (decimalVal2 - val2)//
                            + (decimalVal3 - val3) * (decimalVal3 - val3);//

                    if (val < sub) {
                        sub = val;
                        res = "#" + getHex(val1) + getHex(val2) + getHex(val3);
                    }
                }
            }
        }
        return res;
    }

    private String getHex(int dec) {
        String res = "";
        while (dec > 0) {
            int val = dec % 16;
            if (val >= 10) {
                res += (char) ('a' + (val - 10));
            } else {
                res += val;
            }
            dec /= 16;
        }
        if (dec == 0) {
            return "00";
        }
        return res;
    }

    private int getDecimal(String hex) {
        int res = 0;
        int pow = 0;
        for (int i = hex.length() - 1; i >= 0; i--) {
            int fac = (int) Math.pow(16, pow);
            int val = getVal(hex.charAt(i));
            res += val * fac;
            pow++;
        }
        return res;
    }

    private int getVal(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        }
        switch (ch) {
        case 'A':
        case 'a':
            return 10;
        case 'B':
        case 'b':
            return 11;
        case 'C':
        case 'c':
            return 12;
        case 'D':
        case 'd':
            return 13;
        case 'E':
        case 'e':
            return 14;
        case 'F':
        case 'f':
            return 15;
        default:
            break;

        }
        return 0;
    }

    public static void main(String[] args) {
        String rgb = "#09f166";
        SimilarRGBColor solution = new SimilarRGBColor();

    }
}
