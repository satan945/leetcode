/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.dc.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abel created on 2017/11/2 下午5:04
 * @version $Id$
 */
public class Solution {

    /**
     * 241. Different Ways to Add Parentheses
     * <p>
     * https://leetcode.com/problems/different-ways-to-add-parentheses
     * <p>
     * Given a string of numbers and operators, return all possible results from computing all the different possible
     * ways to group numbers and operators. The valid operators are +, - and *.
     * 
     * 
     * Example 1 Input: "2-1-1".
     * 
     * ((2-1)-1) = 0 (2-(1-1)) = 2 Output: [0, 2]
     * 
     * 
     * Example 2 Input: "2*3-4*5"
     * 
     * (2*(3-(4*5))) = -34 ((2*3)-(4*5)) = -14 ((2*(3-4))*5) = -10 (2*((3-4)*5)) = -10 (((2*3)-4)*5) = 10 Output: [-34,
     * -14, -10, -10, 10]
     * </p>
     * 
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '0' || ch == '*') {
                String leftPart = input.substring(0, i);
                String rightPart = input.substring(i + 1);
                List<Integer> left = diffWaysToCompute(leftPart);
                List<Integer> right = diffWaysToCompute(rightPart);
                for (Integer p1 : left) {
                    for (Integer p2 : right) {
                        int c = 0;
                        switch (ch) {
                        case '+':
                            c = p1 + p2;
                            break;
                        case '-':
                            c = p1 - p2;
                            break;
                        case '*':
                            c = p1 * p2;
                            break;
                        default:
                            break;
                        }
                        res.add(c);
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
}
