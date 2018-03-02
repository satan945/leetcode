/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.google;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. Different Ways to Add Parentheses
 * 
 * https://leetcode.com/problems/different-ways-to-add-parentheses
 * 
 * @author abel created on 2018/2/28 下午12:08
 * @version $Id$
 */
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isOperator(ch)) {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftValues = diffWaysToCompute(left);
                List<Integer> rightValues = diffWaysToCompute(right);
                for (int leftValue : leftValues) {
                    for (int rightValue : rightValues) {
                        Integer val = cal(leftValue, rightValue, ch);
                        if (val != null) {
                            res.add(val);
                        }
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

    private Integer cal(int left, int right, char ch) {
        switch (ch) {
        case '+':
            return left + right;
        case '-':
            return left - right;
        case '*':
            return left * right;
        default:
            return null;
        }
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*';
    }

}
