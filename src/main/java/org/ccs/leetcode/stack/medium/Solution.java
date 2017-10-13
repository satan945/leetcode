/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.stack.medium;

import org.ccs.leetcode.bean.NestedInteger;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author abel created on 2017/8/22 下午4:31
 * @version $Id$
 */
public class Solution {

    /**
     * 503. Next Greater Element II
     * <p>
     * https://leetcode.com/problems/next-greater-element-ii
     * <p>
     * Given a circular array (the next element of the last element is the first element of the array), print the Next
     * Greater Number for every element. The Next Greater Number of a number x is the first greater number to its
     * traversing-order next in the array, which means you could search circularly to find its next greater number. If
     * it doesn't exist, output -1 for this number.
     * 
     * Example 1: Input: [1,2,1] Output: [2,-1,2] Explanation: The first 1's next greater number is 2; The number 2
     * can't find next greater number; The second 1's next greater number needs to search circularly, which is also 2.
     * Note: The length of given array won't exceed 10000.
     * </p>
     * 
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int[] copy = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
            copy[i + nums.length] = nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < copy.length; i++) {
            while (!stack.isEmpty() && copy[i] > copy[stack.peek()]) {
                map.put(stack.pop(), copy[i]);
            }
            stack.push(i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer num = map.get(i);
            res[i] = num == null ? -1 : num;
        }
        return res;
    }

    /**
     * 385. Mini Parser
     * <p>
     * https://leetcode.com/problems/mini-parser
     * <p>
     * Given a nested list of integers represented as a string, implement a parser to deserialize it.
     * 
     * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     * 
     * Note: You may assume that the string is well-formed:
     * 
     * String is non-empty. String does not contain white spaces. String contains only digits 0-9, [, - ,, ]. Example 1:
     * 
     * Given s = "324",
     * 
     * You should return a NestedInteger object which contains a single integer 324. Example 2:
     * 
     * Given s = "[123,[456,[789]]]",
     * 
     * Return a NestedInteger object containing a nested list with 2 elements:
     * 
     * 1. An integer containing value 123. 2. A nested list containing two elements: i. An integer containing value 456.
     * ii. A nested list with one element: a. An integer containing value 789.
     * </p>
     * todo
     * 
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        return null;
    }

    /**
     * 150. Evaluate Reverse Polish Notation
     * <p>
     * https://leetcode.com/problems/evaluate-reverse-polish-notation
     * <p>
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * 
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * 
     * Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     * </p>
     * 
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> opStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        for (String token : tokens) {
            if (isOpertor(token)) {
                int num2 = numStack.pop();
                int num1 = numStack.pop();
                int val = cal(num1, num2, token);
                numStack.push(val);
            } else {
                numStack.push(Integer.parseInt(token));
            }
        }
        return numStack.pop();
    }

    private int cal(int num1, int num2, String token) {
        if (token.equals("+")) {
            return num1 + num2;
        } else if (token.equals("-")) {
            return num1 - num2;
        } else if (token.equals("*")) {
            return num1 * num2;
        } else if (token.equals("/")) {
            return num1 / num2;
        } else {
            return 0;
        }
    }

    private boolean isOpertor(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] { 1, 2, 1 };
        System.out.println(solution.nextGreaterElements(array));
    }
}
