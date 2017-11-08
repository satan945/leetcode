/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    /**
     * 647. Palindromic Substrings
     * 
     * @param s
     * @return
     */
    public int countSubstringsSlow(String s) {
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isPalindromic(sub)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindromic(String sub) {
        return new StringBuilder(sub).reverse().toString().equals(sub);
    }

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += findPalindromicNum(s, i, i);
            count += findPalindromicNum(s, i, i + 1);
        }
        return count;
    }

    private int findPalindromicNum(String s, int from, int to) {
        int count = 0;
        while (from >= 0 && to < s.length() && s.charAt(from) == s.charAt(to)) {
            count++;
            from--;
            to++;
        }
        return count;
    }

    /**
     * 151. Reverse Words in a String
     * <p>
     * https://leetcode.com/problems/reverse-words-in-a-string
     * <p>
     * Given an input string, reverse the string word by word.
     * 
     * For example, Given s = "the sky is blue", return "blue is sky the".
     * </p>
     * 
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        if (s.length() <= 1) {
            return s;
        }
        String[] strs = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strs.length - 1; i > 0; i--) {
            String append = strs[i];
            if (!"".equals(append)) {
                stringBuilder.append(append).append(" ");
            }
        }
        stringBuilder.append(strs[0]);
        return stringBuilder.toString();
    }

    public String reverseWordsWith2Pointers(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] charArray = s.toCharArray();
        // step1. reverse all
        reverseAll(charArray, 0, charArray.length - 1);

        // step2. reverse each word
        reverseEach(charArray, charArray.length);

        // step3. clear spaces
        return clearSpaces(charArray);
    }

    private String clearSpaces(char[] charArray) {
        int length = charArray.length;
        int i = 0, j = 0;
        while (j < length) {
            while (j < length && charArray[j] == ' ') {
                j++;
            }
            while (j < length && charArray[j] != ' ') {
                charArray[i] = charArray[j];
                i++;
                j++;
            }
            while (j < length && charArray[j] == ' ') {
                j++;
            }
            if (j < length) {
                charArray[i] = ' ';
                i++;
            }

        }
        return String.valueOf(charArray, 0, i);

    }

    private void reverseEach(char[] charArray, int length) {
        int i = 0;
        int j = 0;
        while (i < length) {
            while (i < j || i < length && charArray[i] == ' ') {
                i++;
            }
            while (j < i || j < length && charArray[j] != ' ') {
                j++;
            }
            reverseAll(charArray, i, j - 1);
        }
    }

    private void reverseAll(char[] charArray, int start, int end) {
        while (start < end) {
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 186. Reverse Words in a String II
     * <p>
     * <p>
     * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
     * 
     * The input string does not contain leading or trailing spaces and the words are always separated by a single
     * space.
     * 
     * For example, Given s = "the sky is blue", return "blue is sky the".
     * 
     * Could you do it in-place without allocating extra space?
     * 
     * Related problem: Rotate Array
     * </p>
     * 
     * @param s
     */
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }

        reverseAll(s, 0, s.length - 1);

        reverseEach(s, s.length);

        clearSpaces(s);

    }

    /**
     * 49. Group Anagrams
     * <p>
     * https://leetcode.com/problems/group-anagrams/description/
     * <p>
     * Given an array of strings, group anagrams together.
     *
     * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], Return:
     *
     * [ ["ate", "eat","tea"], ["nat","tan"], ["bat"] ]
     * 
     * Note: All inputs will be in lower-case.
     * 
     * </p>
     * String HashTable
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            List<String> list;
            String key = String.valueOf(array);
            if (map.containsKey(key)) {
                list = map.get(key);
            } else {
                list = new ArrayList<>();
                map.put(key, list);
                result.add(list);
            }
            list.add(str);
        }
        return result;
    }

    public List<List<String>> groupAnagramsPrime(String[] strs) {
        int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
                103 };// 最多10609个z

        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }

    /**
     * 556. Next Greater Element III
     *
     * <p>
     * https://leetcode.com/problems/next-greater-element-iii
     * <p>
     * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits
     * existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to
     * return -1.
     * 
     * Example 1:
     * 
     * Input: 12 Output: 21
     * 
     * Example 2:
     * 
     * Input: 21 Output: -1
     * </p>
     * 
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        if (digits.length <= 1) {
            return -1;
        }
        int i = digits.length - 2;
        while (i >= 0 && digits[i + 1] <= digits[i]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = digits.length - 1;
        while (j >= 0 && digits[j] <= digits[i]) {
            j--;
        }
        swap(digits, i, j);
        reverse(digits, i + 1);
        try {
            return Integer.parseInt(String.valueOf(digits));
        } catch (Exception e) {
            return -1;
        }
    }

    private void reverse(char[] digits, int i) {
        int j = digits.length - 1;
        while (i < j) {
            swap(digits, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] digits, int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }

    /**
     * 388. Longest Absolute File Path
     * <p>
     * https://leetcode.com/problems/longest-absolute-file-path
     * <p>
     * 
     * Suppose we abstract our file system by a string in the following manner:
     * 
     * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
     * 
     * dir subdir1 subdir2 file.ext The directory dir contains an empty sub-directory subdir1 and a sub-directory
     * subdir2 containing a file file.ext.
     * 
     * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
     * represents:
     * 
     * dir subdir1 file1.ext subsubdir1 subdir2 subsubdir2 file2.ext The directory dir contains two sub-directories
     * subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
     * subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
     * 
     * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
     * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and
     * its length is 32 (not including the double quotes).
     * 
     * Given a string representing the file system in the above format, return the length of the longest absolute path
     * to file in the abstracted file system. If there is no file in the system, return 0.
     * 
     * Note: The name of a file contains at least a . and an extension. The name of a directory or sub-directory will
     * not contain a .. Time complexity required: O(n) where n is the size of the input string.
     * 
     * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path
     * aaaaaaaaaaaaaaaaaaaaa/sth.png.
     * </p>
     * 
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0 || !input.contains(".")) {
            return 0;
        }
        String[] paths = input.split("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        int max = 0;
        for (String path : paths) {
            int lev = path.lastIndexOf("\t") + 1; // number of "\t"
            while (lev + 1 < stack.size()) {
                stack.pop(); // find parent
            }
            int len = stack.peek() + path.length() - lev + 1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if (path.contains(".")) {
                max = Math.max(max, len - 1);
            }
        }
        return max;
    }

    /**
     * 6. ZigZag Conversion
     * <p>
     * https://leetcode.com/problems/zigzag-conversion
     * <p>
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
     * display this pattern in a fixed font for better legibility)
     * 
     * P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR" Write the code that will take a string
     * and make this conversion given a number of rows:
     * 
     * string convert(string text, int nRows); convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     * </p>
     * 
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder();
        }
        int i = 0;
        while (i < s.length()) {
            for (int index = 0; index < numRows && i < s.length(); index++) {
                stringBuilders[index].append(s.charAt(i));
                i++;
            }
            for (int index = numRows - 2; i < s.length() && index >= 1; index--) {
                stringBuilders[index].append(s.charAt(i));
                i++;
            }
        }
        StringBuilder resBuilder = new StringBuilder();
        for (StringBuilder stringBuilder : stringBuilders) {
            resBuilder.append(stringBuilder.toString());
        }
        return resBuilder.toString();
    }

    /**
     * 299. Bulls and Cows
     * <p>
     * https://leetcode.com/problems/bulls-and-cows
     * <p>
     * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend
     * to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many
     * digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many
     * digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive
     * guesses and hints to eventually derive the secret number.
     * 
     * For example:
     * 
     * Secret number: "1807"
     * 
     * Friend's guess: "7810"
     * 
     * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.) Write a function to return a hint according to
     * the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above
     * example, your function should return "1A3B".
     * 
     * Please note that both secret number and friend's guess may contain duplicate digits, for example:
     * 
     * Secret number: "1123"
     * 
     * Friend's guess: "0111"
     * 
     * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return
     * "1A1B". You may assume that the secret number and your friend's guess only contain digits, and their lengths are
     * always equal.
     * 
     * Credits: Special thanks to @jeantimex for adding this problem and creating all test cases.
     * </p>
     * https://en.wikipedia.org/wiki/Bulls_and_Cows
     * 
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int countA = 0, countB = 0;
        boolean[] skip = new boolean[secret.length()];
        Map<Character, Integer> letterMap = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            char sKey = secret.charAt(i);
            char gKey = guess.charAt(i);
            if (sKey == gKey) {
                skip[i] = true;
                countA++;
            } else {
                Integer count = letterMap.get(sKey);
                if (count == null) {
                    letterMap.put(sKey, 1);
                } else {
                    letterMap.put(sKey, ++count);
                }
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (!skip[i]) {
                char gKey = guess.charAt(i);
                if (letterMap.containsKey(gKey)) {
                    int count = letterMap.get(gKey);
                    if (count > 0) {
                        countB++;
                        letterMap.put(gKey, --count);
                    }
                }
            }
        }
        return countA + "A" + countB + "B";
    }

    /**
     * 165. Compare Version Numbers
     * <p>
     * https://leetcode.com/problems/compare-version-numbers
     * <p>
     * Compare two version numbers version1 and version2. If version1 > version2 return 1, if version1 < version2 return
     * -1, otherwise return 0.
     * 
     * You may assume that the version strings are non-empty and contain only digits and the . character. The .
     * character does not represent a decimal point and is used to separate number sequences. For instance, 2.5 is not
     * "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level
     * revision.
     * 
     * Here is an example of version numbers ordering:
     * 
     * 0.1 < 1.1 < 1.2 < 13.37
     * </p>
     * 
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /**
     * 522. Longest Uncommon Subsequence II
     * <p>
     * https://leetcode.com/problems/longest-uncommon-subsequence-ii
     * <p>
     * Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon
     * subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any
     * subsequence of the other strings.
     * 
     * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing
     * the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a
     * subsequence of any string.
     * 
     * The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence.
     * If the longest uncommon subsequence doesn't exist, return -1.
     * 
     * Example 1:
     *
     * Input: "aba", "cdc", "eae"
     *
     * Output: 3
     *
     * Note:
     * 
     * All the given strings' lengths will not exceed 10. The length of the given list will be in the range of [2, 50].
     * </p>
     * 
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        int res = -1;
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String str : strs) {
            for (String sub : getSubSeq(str)) {
                frequencyMap.put(sub, frequencyMap.getOrDefault(sub, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 1) {
                res = Math.max(entry.getKey().length(), res);
            }
        }
        return res;
    }

    private Set<String> getSubSeq(String str) {
        Set<String> subSet = new HashSet<>();
        if (str.length() == 0) {
            subSet.add("");
            return subSet;
        }
        Set<String> set = getSubSeq(str.substring(1));
        subSet.addAll(set);
        for (String sub : set) {
            subSet.add(str.charAt(0) + sub);
        }
        return subSet;
    }

    /**
     * 539. Minimum Time Difference
     * <p>
     * https://leetcode.com/problems/minimum-time-difference
     * <p>
     * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between
     * any two time points in the list.
     * 
     * Example 1:
     * 
     * Input: ["23:59","00:00"]
     * 
     * Output: 1
     * 
     * Note: The number of time points in the given list is at least 2 and won't exceed 20000. The input time is legal
     * and ranges from 00:00 to 23:59.
     * </p>
     * 
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        List<Integer> points = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        for (String timePoint : timePoints) {
            int hour = Integer.parseInt(timePoint.substring(0, timePoint.indexOf(":")));
            int minute = Integer.parseInt(timePoint.substring(timePoint.indexOf(":") + 1));
            points.add(hour * 60 + minute);
        }
        points.sort((o1, o2) -> o1 - o2);
        for (int i = 0; i < points.size() - 1; i++) {
            minDiff = Math.min(minDiff, points.get(i + 1) - points.get(i));
        }
        int corner = points.get(0) + 1440 - points.get(points.size() - 1);
        return Math.min(corner, minDiff);
    }

    /**
     * 468. Validate IP Address
     * <p>
     * https://leetcode.com/problems/validate-ip-address
     * <p>
     * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
     * 
     * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each
     * ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
     * 
     * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
     * 
     * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The
     * groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid
     * one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the
     * address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros
     * and using upper cases).
     * 
     * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive
     * colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
     * 
     * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address
     * 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
     * 
     * Note: You may assume there is no extra space or special characters in the input string.
     * 
     * Example 1: Input: "172.16.254.1"
     * 
     * Output: "IPv4"
     * 
     * Explanation: This is a valid IPv4 address, return "IPv4". Example 2: Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
     * 
     * Output: "IPv6"
     * 
     * Explanation: This is a valid IPv6 address, return "IPv6". Example 3: Input: "256.256.256.256"
     * 
     * Output: "Neither"
     * 
     * Explanation: This is neither a IPv4 address nor a IPv6 address.
     * </p>
     * 
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {
        String IPV4 = "IPV4";
        String IPV6 = "IPV4";
        String NEITHER = "Neither";
        if (IP == null || IP.length() == 0) {
            return NEITHER;
        }
        if (IP.contains(".") && !IP.endsWith(".")) {
            if (isIPv4Address(IP)) {
                return IPV4;
            }
        }
        if (IP.contains(":") && !IP.endsWith(":")) {
            if (isIPv6Address(IP)) {
                return IPV6;
            }
        }
        return NEITHER;
    }

    private boolean isIPv6Address(String ip) {
        String[] ipSections = ip.split(":");

        if (ipSections.length != 8) {
            return false;
        }
        for (String section : ipSections) {
            if (section == null || section.length() == 0 || section.length() > 4 || section.equals("")) {
                return false;
            }
            if (!section.matches("[0-9a-fA-F]+")) {
                return false;
            }
        }
        return true;
    }

    private boolean isIPv4Address(String ip) {
        String[] ipSections = ip.split("\\.");
        if (ipSections.length != 4) {
            return false;
        }
        for (String section : ipSections) {
            if (!section.matches("[0-9]+") || (section.startsWith("0") && section.length() > 1)) {
                return false;
            }
            int val;
            try {
                val = Integer.parseInt(section);
            } catch (NumberFormatException e) {
                return false;
            }
            if (val < 0 || val > 255) {
                return false;
            }
        }
        return true;
    }

    /**
     * 93. Restore IP Addresses
     * <p>
     * https://leetcode.com/problems/restore-ip-addresses
     * <p>
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * 
     * For example: Given "25525511135",
     * 
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     * </p>
     * 
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if (isValidIPSection(s1) && isValidIPSection(s2) && isValidIPSection(s3) && isValidIPSection(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }

    private boolean isValidIPSection(String ipSection) {
        if (ipSection.length() == 0 || ipSection.length() > 3 || (ipSection.charAt(0) == '0' && ipSection.length() > 1)
                || Integer.parseInt(ipSection) > 255) {
            return false;
        }
        return true;
    }

    /**
     * 681. Next Closest Time
     * <p>
     * https://leetcode.com/problems/next-closest-time
     * <p>
     * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There
     * is no limit on how many times a digit can be reused.
     * 
     * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34",
     * "12:9" are all invalid.
     * 
     * Example 1:
     * 
     * Input: "19:34" Output: "19:39" Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39,
     * which occurs 5 minutes later. It is not 19:33, because this occurs 23 hours and 59 minutes later. Example 2:
     * 
     * Input: "23:59" Output: "22:22" Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It
     * may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
     * </p>
     * todo
     * 
     * @param time
     * @return
     */
    public String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        // add every digit to set
        // add one min every time and check every digits is in the set
        return "";
    }

    /**
     * 91. Decode Ways
     * <p>
     * https://leetcode.com/problems/decode-ways
     * <p>
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 
     * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits, determine the total number of ways to
     * decode it.
     * 
     * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     * 
     * The number of ways decoding "12" is 2.
     * </p>
     * todo
     * 
     * @param s
     * @return
     */
    public int numDecodings(String s) {

        return 0;
    }

    /**
     * 227. Basic Calculator II
     * <p>
     * https://leetcode.com/problems/basic-calculator-ii
     * 
     * <p>
     * Implement a basic calculator to evaluate a simple expression string.
     * 
     * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer
     * division should truncate toward zero.
     * 
     * You may assume that the given expression is always valid.
     * 
     * Some examples: "3+2*2" = 7 " 3/2 " = 1 " 3+5 / 2 " = 5 Note: Do not use the eval built-in library function.
     * </p>
     * 
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        char oper = '+';
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (oper == '-') {
                    stack.push(-num);
                }
                if (oper == '+') {
                    stack.push(num);
                }
                if (oper == '*') {
                    stack.push(stack.pop() * num);
                }
                if (oper == '/') {
                    stack.push(stack.pop() / num);
                }
                oper = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // String a =
        // "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        // System.out.println(solution.nextGreaterElement(12));
        // String path = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        // System.out.println(solution.lengthLongestPath(path));
        // String a = "01";
        // String b = "1";
        // System.out.println(solution.compareVersion(a, b));
        //
        // Set<String> sbu = solution.getSubSeq("abc");
        // System.out.println(sbu);
        // System.out.println(solution.getHint("1807", "7810"));
        // String timePoint = "23:59";
        // int hour = Integer.parseInt(timePoint.substring(0, timePoint.indexOf(":")));
        // int minute = Integer.parseInt(timePoint.substring(timePoint.indexOf(":") + 1));
        // System.out.println(hour + ":" + minute);
        String addr = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        String a = "1-1+1";
        System.out.println(solution.calculate(a));
        System.out.println(solution.isIPv6Address(addr));
    }

}
