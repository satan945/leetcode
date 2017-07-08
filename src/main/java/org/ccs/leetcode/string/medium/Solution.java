/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.medium;

import java.math.BigDecimal;
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
        if (str == null || "".equals(str)) {
            return 0;
        }
        int i = 0;
        while (str.charAt(i) == '0' || str.charAt(i) == ' ') {
            i++;
        }
        if (i > 0) {
            str = str.substring(i);
        }
        if (!checkLegal(str)) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    private boolean checkLegal(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
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
        int i = 0;
        int step = s.length();
        String maxStr = "";
        while (step >= 2) {
            while (i + step <= s.length()) {
                String sub = s.substring(i, i + step);
                if (isPalindrome(sub)) {
                    maxStr = sub;
                    break;
                }
                i++;
            }
            if (!maxStr.equals("")) {
                return maxStr;
            }
            i = 0;
            step--;
        }
        return s.substring(0,1);
    }

    private boolean isPalindrome(String sub) {
        return new StringBuilder(sub).reverse().toString().equals(sub);
    }

    public static void main(String[] args) {
        String a = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(new Solution().longestPalindrome(a));
    }



}
