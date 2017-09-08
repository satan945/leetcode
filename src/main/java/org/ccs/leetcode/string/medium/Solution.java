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
import java.util.LinkedList;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        // String a =
        // "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        // System.out.println(solution.nextGreaterElement(12));
        // String path = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        // System.out.println(solution.lengthLongestPath(path));
        String a = "01";
        String b = "1";
        System.out.println(solution.compareVersion(a, b));

        Set<String> sbu = solution.getSubSeq("abc");
        System.out.println(sbu);
        System.out.println(solution.getHint("1807", "7810"));
    }

}
