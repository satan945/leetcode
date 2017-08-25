/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.string.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
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
        for (int i = digits.length - 1; i >= 1; i--) {
            int r = digits[i] - '0';
            for (int j = i - 1; j >= 0; j--) {
                int l = digits[j] - '0';
                if (l < r) {
                    char temp = digits[i];
                    digits[i] = digits[j];
                    digits[j] = temp;
                    long value = Long.parseLong(String.valueOf(digits));
                    if (value > Integer.MAX_VALUE) {
                        return -1;
                    }
                    return (int) value;
                }
            }
        }
        return -1;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        String a = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(solution.nextGreaterElement(12));
        String path = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(solution.lengthLongestPath(path));
    }

}
