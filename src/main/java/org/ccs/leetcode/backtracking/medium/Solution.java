/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateParenthesis(4);
    }

}
