/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     *
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        return true;
    }

    /**
     * 39. Combination Sum
     *
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
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        return null;
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
     * todo
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        return null;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        // solution.generateParenthesis(4);
        // solution.subsets(new int[] { 1, 2, 3 });
        System.out.println(solution.permute2(new int[] { 1, 2, 3 }));
        System.out.println(solution.permuteString2("abc"));
    }

}
