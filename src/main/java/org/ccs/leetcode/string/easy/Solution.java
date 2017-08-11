/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Abel created on 2017/7/7 11:53
 * @version $Id$
 */
public class Solution {

    /**
     * 389 Find the Difference
     * <p>
     * https://leetcode.com/problems/find-the-difference
     * <p>
     * Given two strings s and t which consist of only lowercase letters.
     * 
     * String t is generated by random shuffling string s and then add one more letter at a random position.
     * 
     * Find the letter that was added in t.
     * 
     * Example:
     * 
     * Input: s = "abcd" t = "abcde"
     * 
     * Output: e
     * 
     * Explanation: 'e' is the letter that was added.
     * </p>
     * 
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(tChars);
        Arrays.sort(sChars);
        for (int i = 0; i < tChars.length; i++) {
            if (i == sChars.length) {
                return tChars[i];
            }
            if (tChars[i] != sChars[i]) {
                return tChars[i];
            }
        }
        return ' ';
    }

    public char findTheDifference2(String s, String t) {
        // Initialize variables to store sum of ASCII codes for
        // each string
        int charCodeS = 0, charCodeT = 0;
        // Iterate through both strings and char codes
        for (int i = 0; i < s.length(); ++i)
            charCodeS += (int) s.charAt(i);
        for (int i = 0; i < t.length(); ++i)
            charCodeT += (int) t.charAt(i);
        // Return the difference between 2 strings as char
        return (char) (charCodeT - charCodeS);
    }

    /**
     * 58. Length of Last Word
     * <p>
     * https://leetcode.com/problems/length-of-last-word
     * <p>
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last
     * word in the string.
     * 
     * If the last word does not exist, return 0.
     * 
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * 
     * For example, Given s = "Hello World", return 5.
     * </p>
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] strings = s.split(" ");
        return strings[strings.length - 1].length();
    }

    /**
     * 557. Reverse Words in a String III
     * <p>
     * https://leetcode.com/problems/reverse-words-in-a-string-iii
     *
     * <p>
     * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving
     * whitespace and initial word order.
     * 
     * Example 1: Input: "Let's take LeetCode contest"
     * 
     * Output: "s'teL ekat edoCteeL tsetnoc"
     * 
     * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
     * </p>
     * 
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] array = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && !" ".equals(array[i])) {
                result.append(new StringBuilder(array[i]).reverse().toString());
            }
            if (i != array.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * 344 Reverse String
     * <p>
     * https://leetcode.com/problems/reverse-string
     *
     * @param s
     * @return
     */
    public String reverseString(String s) {
        int length = s.length();
        char[] a = s.toCharArray();
        char[] b = new char[length];
        int j = 0;
        for (int i = length - 1; i >= 0; i--) {
            b[j] = a[i];
            j++;
        }
        return new String(b);
    }

    /**
     * 541. Reverse String II
     * <p>
     * https://leetcode.com/problems/reverse-string-ii
     * <p>
     * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from
     * the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k
     * but greater than or equal to k characters, then reverse the first k characters and left the other as original.
     * 
     * Example:
     * 
     * Input: s = "abcdefg", k = 2 Output: "bacdfeg"
     * 
     * Restrictions: The string consists of lower English letters only. Length of the given string and k will in the
     * range [1, 10000]
     * </p>
     * 
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            int j = Math.min(i + k - 1, s.length() - 1);
            swapCharArray(array, i, j);
            i += 2 * k;
        }
        return String.valueOf(array);
    }

    private void swapCharArray(char[] array, int i, int j) {
        while (i < j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 293. Flip Game
     * <p>
     * https://leetcode.com/problems/flip-game
     * <p>
     * You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
     * + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can
     * no longer make a move and therefore the other person will be the winner.
     *
     * Write a function to compute all possible states of the string after one valid move.
     *
     * For example, given s = "++++", after one move, it may become one of the following states:
     *
     * [ "--++", "+--+", "++--" ] If there is no valid move, return an empty list [].
     * </p>
     *
     * @param s
     * @return
     */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();
        char[] array = s.toCharArray();
        for (int i = 0; i + 1 < array.length; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                array[i] = '-';
                array[i + 1] = '-';
                result.add(String.valueOf(array));
                array[i] = '+';
                array[i + 1] = '+';
            }
        }
        return result;
    }

    /**
     * 387. First Unique Character in a String
     * <p>
     * https://leetcode.com/problems/first-unique-character-in-a-string
     * <p>
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return
     * -1.
     * 
     * Examples:
     * 
     * s = "leetcode" return 0.
     * 
     * s = "loveleetcode", return 2. Note: You may assume the string contain only lowercase letters.
     * </p>
     * 
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            Integer count = hashMap.get(array[i]);
            if (count == null) {
                hashMap.put(array[i], 1);
            } else {
                ++count;
                hashMap.put(array[i], count);
            }
        }
        for (int i = 0; i < array.length; i++) {
            Integer count = hashMap.get(array[i]);
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * <p>
     * The count-and-say sequence is the sequence of integers with the first five terms as following: 1. 1
     *
     * 2. 11
     *
     * 3. 21
     *
     * 4. 1211
     *
     * 5. 111221
     *
     * 1 is read off as "one 1" or 11.
     * 
     * 11 is read off as "two 1s" or 21.
     * 
     * 21 is read off as "one 2, then one 1" or 1211.
     * 
     * Given an integer n, generate the nth term of the count-and-say sequence.
     * 
     * Note: Each term of the sequence of integers will be represented as a string.
     * 
     * Example 1:
     * 
     * Input: 1 Output: "1" Example 2:
     * 
     * Input: 4 Output: "1211"
     * </p>
     * 
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        return "";
    }

    /**
     * 28. Implement strStr()
     * <p>
     * https://leetcode.com/problems/implement-strstr
     *
     * <p>
     * Implement strStr().
     * 
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * </p>
     * 
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0;; i++) {
            for (int j = 0;; j++) {
                if (j == needle.length()) {
                    return i;
                }
                if (i + j > haystack.length()) {
                    return -1;
                }
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
        }
    }

    /**
     * 14. Longest Common Prefix
     * 
     * <p>
     * https://leetcode.com/problems/longest-common-prefix
     * <p>
     * Write a function to find the longest common prefix string amongst an array of strings.
     * </p>
     * https://leetcode.com/problems/longest-common-prefix/solution/
     * 
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0;; i++) {
            if (strs[0].length() == i) {
                return res.toString();
            }
            char compare = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i) {
                    return res.toString();
                }
                if (strs[j].charAt(i) != compare) {
                    return res.toString();
                }
            }
            res.append(compare);
        }
    }

    public static void main(String[] args) {
        String a = "123456  123123";
        Solution solution = new Solution();
        System.out.println(solution.reverseString(a));
        System.out.println(solution.lengthOfLastWord(a));
    }
}
