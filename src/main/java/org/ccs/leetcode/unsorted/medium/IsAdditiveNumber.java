/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.unsorted.medium;

/**
 * 306. Additive Number
 * <p>
 * https://leetcode.com/problems/additive-number
 * <p>
 * Additive number is a string whose digits can form additive sequence.
 * 
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent
 * number in the sequence must be the sum of the preceding two.
 * 
 * For example: "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8 "199100199" is also an additive number, the additive sequence is: 1, 99,
 * 100, 199. 1 + 99 = 100, 99 + 100 = 199 Note: Numbers in the additive sequence cannot have leading zeros, so sequence
 * 1, 2, 03 or 1, 02, 3 is invalid.
 * 
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * 
 * Follow up: How would you handle overflow for very large input integers?
 * </p>
 * 
 * @author abel created on 2017/10/23 下午10:42
 * @version $Id$
 */
public class IsAdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2) {
            return false;
        }
        int len = num.length();
        for (int i = 1; i <= len / 2; i++) {
            if (num.charAt(0) == '0' && i >= 2) {
                break;
            }
            for (int j = i + 1; len - j >= j - i; j++) {
                if (num.charAt(i) == '0' && j - i >= 2) {
                    break;
                }
                long num1 = Long.parseLong(num.substring(0, i));
                long num2 = Long.parseLong(num.substring(i, j));
                String subStr = num.substring(j);
                if (isAdditiveNumber(subStr, num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdditiveNumber(String str, long num1, long num2) {
        if (str.equals("")) {
            return true;
        }
        long sum = num1 + num2;
        String sumStr = String.valueOf(sum);
        if (!str.startsWith(sumStr)) {
            return false;
        }
        return isAdditiveNumber(str.substring(sumStr.length()), num2, sum);
    }

    public static void main(String[] args) {
        System.out.println(new IsAdditiveNumber().isAdditiveNumber("123"));
    }

}
