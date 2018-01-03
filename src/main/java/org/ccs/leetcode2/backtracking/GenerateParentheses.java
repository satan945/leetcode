/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * </p>
 * 
 * @author abel created on 2018/1/2 上午10:07
 * @version $Id$
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        helper(res, "", 0, 0, n);
        return res;
    }

    private void helper(List<String> res, String exist, int left, int right, int total) {
        if (left == total && right == total) {
            res.add(exist);
            return;
        }
        if (left < total) {
            helper(res, exist + "(", left + 1, right, total);
        }
        if (right < left) {
            helper(res, exist + ")", left, right + 1, total);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();
        System.out.println(solution.generateParenthesis(3));
    }
}
