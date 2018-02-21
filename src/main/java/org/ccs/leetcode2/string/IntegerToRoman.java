/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

/**
 * 12. Integer to Roman
 * 
 * https://leetcode.com/problems/integer-to-roman
 * <p>
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * </p>
 * 
 * @author abel created on 2018/2/14 下午10:33
 * @version $Id$
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        int[] vals = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] romans = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vals.length; i++) {
            while (num >= vals[i]) {
                sb.append(romans[i]);
                num -= vals[i];
            }
        }
        return sb.toString();
    }
}
