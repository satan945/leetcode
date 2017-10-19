/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.backtracking.medium;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Abel created on 2017/8/8 16:05
 * @version $Id$
 */
public class Solution {
    /**
     * 294. Flip Game II
     * <p>
     * https://leetcode.com/problems/flip-game-ii
     * <p>
     * You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
     * + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can
     * no longer make a move and therefore the other person will be the winner.
     *
     * Write a function to determine if the starting player can guarantee a win.
     *
     * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++"
     * to become "+--+".
     *
     * Follow up: Derive your algorithm's runtime complexity.
     * </p>
     * todo
     *
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        return true;
    }

    /**
     * 22. Generate Parentheses
     * <p>
     * https://leetcode.com/problems/generate-parentheses
     * <p>
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * 
     * For example, given n = 3, a solution set is:
     * 
     * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
     * </p>
     * 
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        doGenerate(res, "", 0, 0, n);
        return res;
    }

    /**
     * @param res
     * @param str
     * @param open
     * @param close
     * @param n
     */
    private void doGenerate(List<String> res, String str, int open, int close, int n) {
        if (open == n && close == n) {
            res.add(str);
            return;
        }
        if (open < n) {
            doGenerate(res, str + "(", open + 1, close, n);
        }
        if (close < open) {
            doGenerate(res, str + ")", open, close + 1, n);
        }
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * <p>
     * https://leetcode.com/problems/letter-combinations-of-a-phone-number
     * <p>
     * Given a digit string, return all possible letter combinations that the number could represent.
     * 
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * 
     * 
     * 
     * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]. Note: Although the above
     * answer is in lexicographical order, your answer could be in any order you want.
     * 
     * 
     * </p>
     * 
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        String[] BUTTONS = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        combine(res, "", 0, digits, BUTTONS);
        return res;

    }

    private void combine(List<String> solutions, String prefix, int offset, String digits, String[] buttons) {
        if (offset >= digits.length()) {
            solutions.add(prefix);
            return;
        }
        String letters = buttons[digits.charAt(offset) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            combine(solutions, prefix + letters.charAt(i), offset + 1, digits, buttons);
        }

    }

    /**
     * 78. Subsets
     * <p>
     * https://leetcode.com/problems/subsets *
     * <p>
     * Given a set of distinct integers, nums, return all possible subsets.
     * 
     * Note: The solution set must not contain duplicate subsets.
     * 
     * For example, If nums = [1,2,3], a solution is:
     * 
     * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> sub : res) {
                List<Integer> list = new ArrayList<>(sub);
                list.add(i);
                tmp.add(list);
            }
            res.addAll(tmp);
        }
        return res;
    }

    /**
     * 90. Subsets II
     * <p>
     * https://leetcode.com/problems/subsets-ii
     * <p>
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
     * 
     * Note: The solution set must not contain duplicate subsets.
     * 
     * For example, If nums = [1,2,2], a solution is:
     * 
     * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        genSubSetsWithDup(res, list, 0, nums);
        return res;
    }

    private void genSubSetsWithDup(List<List<Integer>> res, List<Integer> list, int pos, int[] nums) {
        if (pos <= nums.length) {
            res.add(list);
        }
        int i = pos;
        while (i < nums.length) {
            list.add(nums[i]);
            genSubSetsWithDup(res, new ArrayList<>(list), i + 1, nums);
            list.remove(list.size() - 1);
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }
        }
    }

    /**
     * 46. Permutations
     * <p>
     * https://leetcode.com/problems/permutations
     * <p>
     * Given a collection of distinct numbers, return all possible permutations.
     * 
     * For example, [1,2,3] have the following permutations: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     * </p>
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        genPermute(nums, res, new ArrayList<>());
        return res;
    }

    private void genPermute(int[] nums, List<List<Integer>> res, List<Integer> tempList) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) {
                continue;
            }
            tempList.add(nums[i]);
            genPermute(nums, res, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * using swap
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        genPermute2(nums, 0, nums.length - 1, res);
        return res;
    }

    private void genPermute2(int[] nums, int start, int end, List<List<Integer>> res) {
        if (start == end) {
            res.add(transferArrayToList(nums));
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(nums, i, start);
            genPermute2(nums, start + 1, end, res);
            swap(nums, i, start);
        }
    }

    private List<Integer> transferArrayToList(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            res.add(num);
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Permute String
     * 
     * @param str
     * @return
     */
    public List<String> permuteString(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        genStrPermute("", str, res);
        return res;
    }

    private void genStrPermute(String prefix, String rest, List<String> res) {
        if (rest.length() == 0) {
            res.add(prefix);
            return;
        }
        for (int i = 0; i < rest.length(); i++) {
            char ch = rest.charAt(i);
            String subLeft = rest.substring(0, i);
            String subRight = rest.substring(i + 1, rest.length());
            genStrPermute(prefix + ch, subLeft + subRight, res);
        }
    }

    /**
     * using swap
     * 
     * @param str
     * @return
     */
    public List<String> permuteString2(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] charArray = str.toCharArray();
        genStrPermute2(charArray, 0, charArray.length - 1, res);
        return res;
    }

    private void genStrPermute2(char[] charArray, int start, int end, List<String> res) {
        if (start == end) {
            res.add(String.valueOf(charArray));
            return;
        }
        for (int i = start; i <= end; i++) {
            swapChar(charArray, i, start);
            genStrPermute2(charArray, start + 1, end, res);
            swapChar(charArray, i, start);
        }
    }

    private void swapChar(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

    /**
     * 39. Combination Sum
     * <p>
     * https://leetcode.com/problems/combination-sum
     * <p>
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations
     * in C where the candidate numbers sums to T.
     * 
     * The same repeated number may be chosen from C unlimited number of times.
     * 
     * Note: All numbers (including target) will be positive integers. The solution set must not contain duplicate
     * combinations. For example, given candidate set [2, 3, 6, 7] and target 7, A solution set is: [ [7], [2, 2, 3] ]
     * </p>
     *
     * 
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        combineSums(res, candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void combineSums(List<List<Integer>> res, int[] candidates, int target, int start,
            ArrayList<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                if (list.size() > 0 && candidates[i] >= list.get(list.size() - 1) || list.size() == 0) {
                    list.add(candidates[i]);
                    combineSums(res, candidates, target - candidates[i], 0, list);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    /**
     * 40. Combination Sum II
     * <p>
     * https://leetcode.com/problems/combination-sum-ii
     * <p>
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     * 
     * Each number in C may only be used once in the combination.
     * 
     * Note: All numbers (including target) will be positive integers. The solution set must not contain duplicate
     * combinations. For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, A solution set is: [ [1, 7],
     * [1, 2, 5], [2, 6], [1, 1, 6] ]
     * </p>
     * https://leetcode.com/problems/combination-sum-ii/discuss/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        combineSums2(res, candidates, target, 0, new ArrayList<>());
        return res;
    }

    private void combineSums2(List<List<Integer>> res, int[] candidates, int target, int start,
            ArrayList<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                list.add(candidates[i]);
                combineSums2(res, candidates, target - candidates[i], i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     *
     * 216. Combination Sum III
     * <p>
     * https://leetcode.com/problems/combination-sum-iii
     * <p>
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
     * used and each combination should be a unique set of numbers.
     * 
     * 
     * Example 1:
     * 
     * Input: k = 3, n = 7
     * 
     * Output:
     * 
     * [[1,2,4]]
     * 
     * Example 2:
     * 
     * Input: k = 3, n = 9
     * 
     * Output:
     * 
     * [[1,2,6], [1,3,5], [2,3,4]] Credits: Special thanks to @mithmatt for adding this problem and creating all test
     * cases.
     * </p>
     * todo
     * 
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0) {
            return res;
        }
        combineSums3(res, n, k, 0, new ArrayList<>());
        return res;
    }

    private void combineSums3(List<List<Integer>> res, int n, int k, int start, ArrayList<Integer> list) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start + 1; i <= 9; i++) {
            if (n - i >= 0) {
                list.add(i);
                combineSums3(res, n - i, k - 1, i, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 77. Combinations
     * <p>
     * https://leetcode.com/problems/combinations
     * <p>
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * 
     * For example, If n = 4 and k = 2, a solution is:
     * 
     * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
     * </p>
     * 
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0 || n == 0) {
            return res;
        }
        combineKinN(n, 1, k, res, new ArrayList<>());
        return res;
    }

    private void combineKinN(int n, int start, int k, List<List<Integer>> res, ArrayList<Integer> list) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < n; i++) {
            list.add(i);
            combineKinN(n, i + 1, k - 1, res, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 254. Factor Combinations
     * <p>
     * https://leetcode.com/problems/factor-combinations
     * <p>
     * Numbers can be regarded as product of its factors. For example,
     * 
     * 8 = 2 x 2 x 2; = 2 x 4. Write a function that takes an integer n and return all possible combinations of its
     * factors.
     * 
     * Note: You may assume that n is always positive. Factors should be greater than 1 and less than n. Examples:
     * input: 1 output: [] input: 37 output: [] input: 12 output: [ [2, 6], [2, 2, 3], [3, 4] ] input: 32 output: [ [2,
     * 16], [2, 2, 8], [2, 2, 2, 4], [2, 2, 2, 2, 2], [2, 4, 4], [4, 8] ]
     * </p>
     *
     * 
     * @param n
     * @return
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 1) {
            return res;
        }
        calFactors(n, 2, res, new ArrayList<>());
        return res;
    }

    private void calFactors(int n, int start, List<List<Integer>> res, ArrayList<Integer> list) {
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<>(list));
                return;
            }
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                calFactors(n / i, i, res, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 267. Palindrome Permutation II
     * <p>
     * https://leetcode.com/problems/palindrome-permutation-ii
     * <p>
     * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no
     * palindromic permutation could be form.
     *
     * For example:
     *
     * Given s = "aabb", return ["abba", "baab"].
     *
     * Given s = "abc", return [].
     * </p>
     *
     * @param s
     * @return
     */
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        int oddCount = 0;
        String mid = "";

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            oddCount += map.get(s.charAt(i)) % 2 == 0 ? -1 : 1;
        }
        if (oddCount > 1) {
            return res;
        }
        List<Character> keyList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                mid += entry.getKey();
            }
            for (int i = 0; i < entry.getValue() / 2; i++) {
                keyList.add(entry.getKey());
            }
        }
        genPalindromeStrs(keyList, res, new boolean[keyList.size()], new StringBuilder(), mid);
        return res;
    }

    private void genPalindromeStrs(List<Character> keyList, List<String> res, boolean[] used, StringBuilder sb,
            String mid) {
        if (sb.length() == keyList.size()) {
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }
        for (int i = 0; i < keyList.size(); i++) {
            if (i > 0 && keyList.get(i) == keyList.get(i - 1) && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                sb.append(keyList.get(i));
                genPalindromeStrs(keyList, res, used, sb, mid);
                sb.deleteCharAt(sb.length() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 47. Permutations II
     * <p>
     * https://leetcode.com/problems/permutations-ii
     * <p>
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     *
     * For example, [1,1,2] have the following unique permutations: [ [1,1,2], [1,2,1], [2,1,1] ]
     * </p>
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        genPermuteUnique(nums, new boolean[nums.length], res, new ArrayList<>());
        return res;
    }

    private void genPermuteUnique(int[] nums, boolean[] used, List<List<Integer>> res, ArrayList<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                list.add(nums[i]);
                genPermuteUnique(nums, used, res, list);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 357. Count Numbers with Unique Digits
     * <p>
     * https://leetcode.com/problems/count-numbers-with-unique-digits
     * <p>
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
     *
     * Example: Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding
     * [11,22,33,44,55,66,77,88,99])
     * </p>
     * https://discuss.leetcode.com/category/441/count-numbers-with-unique-digits
     *
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n > 10) {
            return countNumbersWithUniqueDigits(10);
        }
        int count = 1; // x == 0
        long max = (long) Math.pow(10, n);

        boolean[] used = new boolean[10];

        for (int i = 1; i < 10; i++) {
            used[i] = true;
            count += search(i, max, used);
            used[i] = false;
        }

        return count;
    }

    private int search(long prev, long max, boolean[] used) {
        int count = 0;
        if (prev < max) {
            count += 1;
        } else {
            return count;
        }

        for (int i = 0; i < 10; i++) {
            if (!used[i]) {
                used[i] = true;
                long cur = 10 * prev + i;
                count += search(cur, max, used);
                used[i] = false;
            }
        }

        return count;
    }

    /**
     * 89. Gray Code
     * <p>
     * https://leetcode.com/problems/gray-code
     * <p>
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * 
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray
     * code. A gray code sequence must begin with 0.
     * 
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     * 
     * 00 - 0 01 - 1 11 - 3 10 - 2 Note: For a given n, a gray code sequence is not uniquely defined.
     * 
     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
     * 
     * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
     * 
     * ¬
     * </p>
     * 
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ i >> 1);
        }
        return res;
    }

    /**
     * 526. Beautiful Arrangement
     * <p>
     * https://leetcode.com/problems/beautiful-arrangement
     * <p>
     * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by
     * these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
     * 
     * The number at the ith position is divisible by i. i is divisible by the number at the ith position. Now given N,
     * how many beautiful arrangements can you construct?
     * 
     * Example 1: Input: 2 Output: 2 Explanation:
     * 
     * The first beautiful arrangement is [1, 2]:
     * 
     * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
     * 
     * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
     * 
     * The second beautiful arrangement is [2, 1]:
     * 
     * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
     * 
     * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
     * </p>
     * 
     * @param N
     * @return todo
     */
    public int countArrangement(int N) {
        return 0;
    }

    /**
     * 79. Word Search
     * <p>
     * https://leetcode.com/problems/word-search
     * <p>
     * Given a 2D board and a word, find if the word exists in the grid.
     * 
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * 
     * For example, Given board =
     * 
     * [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ] word = "ABCCED", -> returns true, word = "SEE", ->
     * returns true, word = "ABCB", -> returns false.
     * </p>
     * 
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] key = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (existWord(board, y, x, key, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existWord(char[][] board, int y, int x, char[] key, int pos) {
        if (pos == key.length) {
            return true;
        }
        if (y < 0 || x < 0 || y > board.length - 1 || x > board[y].length - 1) {
            return false;
        }
        if (board[y][x] != key[pos]) {
            return false;
        }
        board[y][x] ^= 256;
        boolean isExist = existWord(board, y + 1, x, key, pos + 1)//
                || existWord(board, y - 1, x, key, pos + 1)//
                || existWord(board, y, x + 1, key, pos + 1)//
                || existWord(board, y, x - 1, key, pos + 1);
        board[y][x] ^= 256;
        return isExist;
    }

    /**
     * 131. Palindrome Partitioning
     * <p>
     * https://leetcode.com/problems/palindrome-partitioning
     * <p>
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * 
     * Return all possible palindrome partitioning of s.
     * 
     * For example, given s = "aab", Return
     * 
     * [ ["aa","b"], ["a","a","b"] ]
     * </p>
     * https://discuss.leetcode.com/topic/37756/java-dp-dfs-solution
     * 
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0 || s == null) {
            return res;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        palindromePartition(res, s, dp, new ArrayList<>(), 0);
        return res;

    }

    private void palindromePartition(List<List<String>> res, String s, boolean[][] dp, ArrayList<String> list,
            int pos) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = pos; i < s.length(); i++) {
            if (dp[pos][i]) {
                list.add(s.substring(pos, i + 1));
                palindromePartition(res, s, dp, list, i + 1);
                list.remove(list.size() - 1);
            }
        }

    }

    /**
     * 60. Permutation Sequence
     * <p>
     * https://leetcode.com/problems/permutation-sequence
     * <p>
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * 
     * By listing and labeling all of the permutations in order, We get the following sequence (ie, for n = 3):
     * 
     * "123" "132" "213" "231" "312" "321" Given n and k, return the kth permutation sequence.
     * 
     * Note: Given n will be between 1 and 9 inclusive.
     * </p>
     * 
     * @param n
     * @param k
     * @return http://blog.csdn.net/ChilseaSai/article/details/49129663
     */

    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int factorial = 1;
        // 求阶乘
        for (int i = 2; i <= n - 1; i++) {
            factorial *= i;
        }
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        k--;
        int round = n - 1;
        while (round >= 0) {
            int num = list.get(k / factorial);
            sb.append(num);
            list.remove(k / factorial);
            if (round > 0) {
                k = k % factorial;
                factorial /= round;
            }
            round--;
        }
        return sb.toString();
    }

    /**
     * 120. Triangle
     * <p>
     * https://leetcode.com/problems/triangle
     * 
     * @param triangle
     * @return
     */

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }
        int[] cache = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                cache[j] = Math.min(cache[j], cache[j + 1]) + triangle.get(i).get(j);
            }
        }
        return cache[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> layer1 = new ArrayList<>();
        layer1.add(-1);
        List<Integer> layer2 = new ArrayList<>();
        layer2.add(2);
        layer2.add(3);
        List<Integer> layer3 = new ArrayList<>();
        layer3.add(1);
        layer3.add(-1);
        layer3.add(-3);
        lists.add(layer1);
        lists.add(layer2);
        lists.add(layer3);
        System.out.println(solution.minimumTotal(lists));
    }

}
