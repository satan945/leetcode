/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String str = "1";
        if (n == 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.delete(0, sb.length());
            char[] seq = str.toCharArray();
            int count = 1;
            for (int j = 0; j < seq.length; j++) {
                if (j > 0) {
                    if (seq[j] == seq[j - 1]) {
                        count++;
                    } else {
                        sb.append(count).append(seq[j - 1]);
                        count = 1;
                    }
                }
                if (j == seq.length - 1) {
                    sb.append(count).append(seq[j]);
                }
            }
            str = sb.toString();
        }
        return str;
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

    /**
     * 657. Judge Route Circle
     * <p>
     * https://leetcode.com/problems/judge-route-circle
     * <p>
     * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a
     * circle, which means it moves back to the original place.
     * 
     * The move sequence is represented by a string. And each move is represent by a character. The valid robot moves
     * are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot
     * makes a circle.
     * </p>
     * 
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        if (null == moves || moves.length() == 0) {
            return true;
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < moves.length(); i++) {
            char letter = moves.charAt(i);
            switch (letter) {
            case 'L':
                x++;
                break;
            case 'R':
                x--;
                break;
            case 'U':
                y++;
                break;
            case 'D':
                y--;
                break;
            }

        }
        return x == 0 && y == 0;
    }

    /**
     * 520. Detect Capital
     * 
     * <p>
     * https://leetcode.com/problems/detect-capital
     * <p>
     * Given a word, you need to judge whether the usage of capitals in it is right or not.
     * 
     * We define the usage of capitals in a word to be right when one of the following cases holds:
     * 
     * All letters in this word are capitals, like "USA". All letters in this word are not capitals, like "leetcode".
     * Only the first letter in this word is capital if it has more than one letter, like "Google". Otherwise, we define
     * that this word doesn't use capitals in a right way.
     * 
     * Example 1:
     * 
     * Input: "USA" Output: True
     * 
     * Example 2:
     * 
     * Input: "FlaG" Output: False
     * 
     * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
     * </p>
     * 
     * @param word
     * @return
     */
    public boolean detectCapitalUse(String word) {
        if (null == word || word.length() == 0) {
            return false;
        }
        char firstChar = word.charAt(0);
        boolean firstUpper = (firstChar < 'Z' && firstChar > 'A');
        int upperCount = firstUpper ? 1 : 0;
        int lowerCount = !firstUpper ? 1 : 0;

        for (int i = 1; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch <= 'Z' && ch >= 'A') {
                upperCount++;
            }
            if (ch <= 'z' && ch >= 'a') {
                lowerCount++;
            }
        }
        if (firstUpper && upperCount == 1) {
            return true;
        }
        if (lowerCount == word.length()) {
            return true;
        }
        if (upperCount == word.length()) {
            return true;
        }
        return false;
    }

    /**
     * 383. Ransom Note
     * <p>
     * https://leetcode.com/problems/ransom-note
     * <p>
     * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a
     * function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will
     * return false.
     * 
     * Each letter in the magazine string can only be used once in your ransom note.
     * 
     * Note: You may assume that both strings contain only lowercase letters.
     * 
     * canConstruct("a", "b") -> false
     * 
     * canConstruct("aa", "ab") -> false
     * 
     * canConstruct("aa", "aab") -> true
     * </p>
     * 
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counts = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            counts[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            counts[ransomNote.charAt(i) - 'a']--;
            if (counts[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;

    }

    /**
     * 434. Number of Segments in a String
     * <p>
     * https://leetcode.com/problems/number-of-segments-in-a-string
     * <p>
     * 
     * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space
     * characters.
     * 
     * Please note that the string does not contain any non-printable characters.
     * 
     * Example:
     * 
     * Input: "Hello, my name is John"
     * 
     * Output: 5
     * </p>
     * 
     * @param s
     * @return
     */
    public int countSegments(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                count++;
            }
        }
        return count;
    }

    /**
     * 459. Repeated Substring Pattern
     * <p>
     * https://leetcode.com/problems/repeated-substring-pattern
     * <p>
     * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies
     * of the substring together. You may assume the given string consists of lowercase English letters only and its
     * length will not exceed 10000.
     * 
     * Example 1:
     * 
     * Input: "abab"
     * 
     * Output: True
     * 
     * Explanation: It's the substring "ab" twice.
     * 
     * Example 2:
     * 
     * Input: "aba"
     * 
     * Output: False
     * 
     * Example 3:
     * 
     * Input: "abcabcabcabc"
     * 
     * Output: True
     * 
     * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() == 0) {
            return false;
        }
        int length = s.length() / 2;
        StringBuilder sb = new StringBuilder();
        while (length > 1) {
            sb.delete(0, s.length());
            String sub = s.substring(0, length);
            for (int i = 0; i + length <= s.length(); i += length) {
                sb.append(sub);
            }
            if (sb.toString().equals(s)) {
                return true;
            }
            length--;
        }
        return false;
    }

    /**
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        if (s.length() <= 1) {
            return s;
        }
        List<Character> temp = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                temp.add(s.charAt(i));
            }
        }
        if (temp.size() == 0) {
            return s;
        }
        int j = temp.size() - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (temp.contains(s.charAt(i))) {
                sb.append(temp.get(j--));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private boolean isVowel(char c) {
        switch (c) {
        case 'A':
        case 'E':
        case 'I':
        case 'O':
        case 'U':
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
            return true;
        }
        return false;
    }

    public String reverseVowels2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && !isVowel(chars[start])) {
                start++;
            }
            while (start < end && !isVowel(chars[end])) {
                end--;
            }
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }

    /**
     * 521. Longest Uncommon Subsequence I
     * <p>
     * https://leetcode.com/problems/longest-uncommon-subsequence-i
     * <p>
     * Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings. The
     * longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence
     * should not be any subsequence of the other strings.
     *
     * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing
     * the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a
     * subsequence of any string.
     *
     * The input will be two strings, and the output needs to be the length of the longest uncommon subsequence. If the
     * longest uncommon subsequence doesn't exist, return -1.
     *
     * Example 1:
     *
     * Input: "aba", "cdc"
     *
     * Output: 3
     *
     * Explanation:
     *
     * The longest uncommon subsequence is "aba" (or "cdc"), because "aba" is a subsequence of "aba", but not a
     * subsequence of any other strings in the group of two strings. Note:
     *
     * Both strings' lengths will not exceed 100. Only letters from a ~ z will appear in input strings.
     * </p>
     *
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : a.length() > b.length() ? a.length() : b.length();
    }

    /**
     * 415. Add Strings
     * <p>
     * https://leetcode.com/problems/add-strings
     * <p>
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     * 
     * Note:
     * 
     * The length of both num1 and num2 is < 5100.
     * 
     * Both num1 and num2 contains only digits 0-9.
     * 
     * Both num1 and num2 does not contain any leading zero.
     * 
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     * 
     * </p>
     * 
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }
        if (num2 == null || num2.length() == 0) {
            return num1;
        }
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (l1 >= 0 || l2 >= 0 || carry != 0) {
            int c1 = l1 >= 0 ? num1.charAt(l1) - '0' : 0;
            int c2 = l2 >= 0 ? num2.charAt(l2) - '0' : 0;
            int sum = c1 + c2 + carry;
            res.insert(0, sum % 10);
            carry = sum / 10;
            l1 = l1 < 0 ? -1 : --l1;
            l2 = l2 < 0 ? -1 : --l2;
        }
        return res.toString();
    }

    /**
     * 408. Valid Word Abbreviation
     * <p>
     * https://leetcode.com/problems/valid-word-abbreviation
     * <p>
     * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given
     * abbreviation.
     * 
     * A string such as "word" contains only the following valid abbreviations:
     * 
     * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3",
     * "4"] Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is
     * not a valid abbreviation of "word".
     * 
     * Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
     * 
     * Example 1:
     * 
     * Given s = "internationalization", abbr = "i12iz4n":
     * 
     * Return true.
     * 
     * Example 2: Given s = "apple", abbr = "a2e":
     * 
     * Return false.
     * 
     * </p>
     * 
     * @param word
     * @param abbr
     * @return
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            int count = 0;
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
            } else {
                if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                    return false;
                }
                while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    count = count * 10 + (abbr.charAt(j) - '0');
                    j++;
                }
                i += count;
            }
        }
        return (i == word.length()) && (j == abbr.length());
    }

    /**
     * 551. Student Attendance Record I
     * <p>
     * https://leetcode.com/problems/student-attendance-record-i
     * <p>
     * You are given a string representing an attendance record for a student. The record only contains the following
     * three characters:
     * 
     * 'A' : Absent.
     * 
     * 'L' : Late.
     * 
     * 'P' : Present.
     * 
     * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two
     * continuous 'L' (late). You need to return whether the student could be rewarded according to his attendance
     * record.
     * 
     * Example 1:
     * 
     * Input: "PPALLP"
     * 
     * Output: True
     * 
     * Example 2:
     * 
     * Input: "PPALLL"
     * 
     * Output: False
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {
        int aCount = 0;
        int lCount = 0;
        int continuousLCount = 0;
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (aCount > 1 || lCount > 2) {
                return false;
            }
            if (array[i] == 'A') {
                aCount++;
            }
            if (array[i] == 'L') {
                lCount++;
            }
            if (array[i] != 'L' || i == array.length - 1) {
                continuousLCount = Math.max(continuousLCount, lCount);
                lCount = 0;
            }
        }
        return aCount <= 1 && continuousLCount <= 2;
    }

    /**
     * 125. Valid Palindrome
     * <p>
     * https://leetcode.com/problems/valid-palindrome
     * <p>
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * 
     * For example, "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a palindrome.
     * 
     * Note: Have you consider that the string might be empty? This is a good question to ask during an interview.
     * 
     * For the purpose of this problem, we define empty string as valid palindrome.
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        String lowerS = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        char[] array = lowerS.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if ((array[i] >= 'a' && array[i] <= 'z') || (array[i] >= '0' && array[i] <= '9')) {
                sb.append(array[i]);
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    /**
     * 680. Valid Palindrome II
     * <p>
     * https://leetcode.com/problems/valid-palindrome-ii
     * <p>
     * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     * 
     * Example 1: Input: "aba" Output: True Example 2: Input: "abca" Output: True Explanation: You could delete the
     * character 'c'. Note: The string will only contain lowercase characters a-z. The maximum length of the string is
     * 50000.
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        boolean countTail = validTail(s);
        boolean countHead = validHead(s);
        return countTail || countHead;
    }

    private boolean validHead(String s) {
        int head = 0, tail = s.length() - 1;
        int count = 0;
        while (head < tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                if (++count > 1) {
                    return false;
                }
                if (s.charAt(head + 1) == s.charAt(tail)) {
                    head++;
                } else if (s.charAt(head) == s.charAt(tail - 1)) {
                    tail--;
                } else {
                    return false;
                }
            }
            head++;
            tail--;
        }
        return count <= 1;
    }

    private boolean validTail(String s) {
        int head = 0, tail = s.length() - 1;
        int count = 0;
        while (head < tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                if (++count > 1) {
                    return false;
                }
                if (s.charAt(head) == s.charAt(tail - 1)) {
                    tail--;
                } else if (s.charAt(head + 1) == s.charAt(tail)) {
                    head++;
                } else {
                    return false;
                }
            }
            head++;
            tail--;
        }
        return count <= 1;
    }

    public boolean validPalindrome2(String s) {
        int l = -1, r = s.length();
        while (++l < --r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindromic(s, l, r + 1) || isPalindromic(s, l - 1, r);
            }
        }
        return true;
    }

    public boolean isPalindromic(String s, int l, int r) {
        while (++l < --r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 506. Relative Ranks
     * <p>
     * https://leetcode.com/problems/relative-ranks
     * <p>
     * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will
     * be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
     * 
     * Example 1: Input: [5, 4, 3, 2, 1] Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"] Explanation:
     * The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze
     * Medal". For the left two athletes, you just need to output their relative ranks according to their scores. Note:
     * N is a positive integer and won't exceed 10,000. All the scores of athletes are guaranteed to be unique.
     * </p>
     * 
     * @param nums
     * @return
     */

    public String[] findRelativeRanks(int[] nums) {
        String GOLD = "Gold Medal";
        String SILVER = "Silver Medal";
        String BRONZE = "Bronze Medal";
        String[] res = new String[nums.length];
        int[] pos = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pos[i] = nums[i];
        }
        Arrays.sort(pos);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pos.length; i++) {
            map.put(pos[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int val = map.get(nums[i]);
            String sVal;
            if (val == pos.length - 1) {
                sVal = GOLD;
            } else if (val == pos.length - 2) {
                sVal = SILVER;
            } else if (val == pos.length - 3) {
                sVal = BRONZE;
            } else {
                sVal = pos.length - val + "";
            }
            res[i] = sVal;
        }
        return res;
    }

    public static void main(String[] args) {
        String a = "123456  123123";
        String b = "123123123";
        String c = "cupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupucu";
        Solution solution = new Solution();
        // System.out.println(solution.reverseString(a));
        // System.out.println(solution.lengthOfLastWord(a));
        String move = "UD";
        // System.out.println(solution.judgeCircle(move));
        // System.out.println(solution.countAndSay(5));
        // System.out.println(solution.countSegments(a));
        // System.out.println(solution.repeatedSubstringPattern(b));
        // System.out.println(solution.validWordAbbreviation("a", "01"));
        // solution.checkRecord("PPALLL");
        System.out.println(c);
        System.out.println(solution.validPalindrome(c));
    }

}
