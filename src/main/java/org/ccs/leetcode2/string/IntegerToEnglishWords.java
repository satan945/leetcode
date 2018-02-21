/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.string;

/**
 * 273. Integer to English Words
 * <p>
 * https://leetcode.com/problems/integer-to-english-words/description/
 * 
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 -
 * 1.
 * </p>
 * 
 * @author abel created on 2018/2/14 下午10:44
 * @version $Id$
 */
public class IntegerToEnglishWords {
    private final String[] LESS_THAN_20 = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen" };
    private final String[] TENS = new String[] { "", "TEN", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety" };
    private final String[] THOUSANDS = new String[] { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        int i = 0;
        String res = "";
        while (num > 0) {
            if (num % 1000 > 0) {
                res = helper(num % 1000) + THOUSANDS[i] + " " + res;
            }
            num /= 1000;
            i++;
        }
        return res.trim();

    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }

    public static void main(String[] args) {
        IntegerToEnglishWords solution = new IntegerToEnglishWords();
        System.out.println(solution.numberToWords(32903123));
    }

    public String numberToWords2(int num) {
        if (num == 0)
            return "Zero";
        String[] lessThan20Words = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
        String[] tyWords = { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
        String[] dexWords = { "Billion", "Million", "Thousand", "Hundred" };
        int[] radix = { 1000000000, 1000000, 1000, 100 };
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = 0; i < radix.length; ++i) {
            count = num / radix[i];
            if (count == 0)
                continue;
            sb.append(numberToWords(count) + " ");
            sb.append(dexWords[i] + " ");
            num %= radix[i];
        }
        if (num < 20) {
            sb.append(lessThan20Words[num]);
        } else {
            sb.append(tyWords[num / 10 - 2] + " ");
            sb.append(lessThan20Words[num % 10]);
        }
        return sb.toString().trim();

    }
}
