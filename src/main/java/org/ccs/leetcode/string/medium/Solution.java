/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Abel created on 2017/7/7 11:39
 * @version $Id$
 */
public class Solution {

    /**
     * 8. String to Integer (atoi) Not AC
     * <p>
     * https://leetcode.com/problems/string-to-integer-atoi
     * 
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        // 1. Empty string
        if (str.length() == 0)
            return 0;

        // 2. Remove Spaces
        while (str.charAt(index) == ' ' && index < str.length())
            index++;

        // 3. Handle signs
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // 4. Convert number and avoid overflow
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9)
                break;

            // check if total will be overflow after 10 times and add digit
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index++;
        }
        return total * sign;
    }

    /**
     * 187. Repeated DNA Sequences
     * <p>
     * https://leetcode.com/problems/repeated-dna-sequences
     * <p>
     * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When
     * studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
     * 
     * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA
     * molecule.
     * 
     * For example,
     * 
     * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
     * 
     * Return: ["AAAAACCCCC", "CCCCCAAAAA"].
     * </p>
     * 
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null) {
            return new ArrayList<String>();
        }

        int length = s.length();
        if (length <= 10) {
            return new ArrayList<String>();
        }
        int end = length - 9;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int cursor = 0; cursor < end; cursor++) {
            String scanning = s.substring(cursor, cursor + 10);
            Integer count = map.get(scanning);
            if (count == null || count == 0) {
                map.put(scanning, 1);
            } else {
                map.put(scanning, ++count);
            }
        }
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * 71 Simplify Path
     * <p>
     * https://leetcode.com/problems/simplify-path
     *
     * @param path
     * @return
     */

    public String simplifyPath(String path) {
        String newPath = path.replace("//", "/");
        while (newPath.contains("//")) {
            newPath = newPath.replace("//", "/");
        }
        String[] pathList = newPath.split("/");
        Stack<String> stack = new Stack<String>();
        for (int i = 1; i < pathList.length; i++) {
            if (".".equals(pathList[i])) {

            } else if ("..".equals(pathList[i])) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(pathList[i]);
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            if (!"".equals(result)) {
                result = stack.pop() + "/" + result;
            } else {
                result = stack.pop() + result;
            }
        }
        return "/" + result;
    }

    /**
     * 583. Delete Operation for Two Strings
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        return 0;
    }

    /**
     * 5. Longest Palindromic Substring
     * <p>
     *
     * https://leetcode.com/problems/longest-palindromic-substring
     * 
     * <p>
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is
     * 1000.
     * 
     * Example:
     * 
     * Input: "babad" Output: "bab"
     * 
     * Note: "aba" is also a valid answer. Example:
     * 
     * Input: "cbbd" Output: "bb"
     * </p>
     * 
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        StringBuilder longestBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            expand(s, longestBuilder, i, i);// odd
            expand(s, longestBuilder, i, i + 1);// even
        }
        return longestBuilder.toString();
    }

    private void expand(String s, StringBuilder stringBuilder, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                if (j - i + 1 > stringBuilder.length()) {
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(s.substring(i, j + 1));
                }
                i--;
                j++;
            } else {
                break;
            }
        }
    }

    /**
     * 12. Integer to Roman
     * <p>
     * https://leetcode.com/problems/integer-to-roman
     * <p>
     * Given an integer, convert it to a roman numeral.
     * 
     * Input is guaranteed to be within the range from 1 to 3999.
     * </p>
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        return "";
    }

    public static void main(String[] args) {
        String a = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        String b = "a";
        System.out.println(new Solution().longestPalindrome(b));
    }

}
