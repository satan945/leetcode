/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.stack.medium;

import org.ccs.leetcode.bean.NestedInteger;

import java.util.HashMap;
import java.util.List;
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

    /**
     * 402. Remove K Digits
     * <p>
     * https://leetcode.com/problems/remove-k-digits
     * <p>
     * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number
     * is the smallest possible.
     * 
     * Note: The length of num is less than 10002 and will be ≥ k. The given num does not contain any leading zero.
     * Example 1:
     * 
     * Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the three digits 4, 3, and 2 to form the new
     * number 1219 which is the smallest. Example 2:
     * 
     * Input: num = "10200", k = 1 Output: "200" Explanation: Remove the leading 1 and the number is 200. Note that the
     * output must not contain leading zeroes. Example 3:
     * 
     * Input: num = "10", k = 2 Output: "0" Explanation: Remove all the digits from the number and it is left with
     * nothing which is 0.
     * </p>
     * 
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || num.length() <= k) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        for (int i = 1; i < num.length(); i++) {
            while (!stack.isEmpty() && num.charAt(i) < stack.peek() && k > 0) {
                k--;
                stack.pop();
            }
            stack.push(num.charAt(i));
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        if (i == sb.length()) {
            return "0";
        } else {
            sb.delete(0, i);
        }
        return sb.toString();
    }

    /**
     * 636. Exclusive Time of Functions
     * <P>
     * https://leetcode.com/problems/exclusive-time-of-functions
     * <p>
     * Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the
     * exclusive time of these functions.
     * 
     * Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.
     * 
     * A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0
     * starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.
     * 
     * Exclusive time of a function is defined as the time spent within this function, the time spent by calling other
     * functions should not be considered as this function's exclusive time. You should return the exclusive time of
     * each function sorted by their function id.
     * 
     * Example 1: Input: n = 2 logs = ["0:start:0", "1:start:2", "1:end:5", "0:end:6"] Output:[3, 4] Explanation:
     * Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. Now function 0 calls
     * function 1, function 1 starts at time 2, executes 4 units of time and end at time 5. Function 0 is running again
     * at time 6, and also end at the time 6, thus executes 1 unit of time. So function 0 totally execute 2 + 1 = 3
     * units of time, and function 1 totally execute 4 units of time. Note: Input logs will be sorted by timestamp, NOT
     * log id. Your output should be sorted by function id, which means the 0th element of your output corresponds to
     * the exclusive time of function 0. Two functions won't start or end at the same time. Functions could be called
     * recursively, and will always end. 1 <= n <= 100
     * </p>
     * 
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        return new int[0];
    }

    /**
     * 456. 132 Pattern
     * <p>
     * https://leetcode.com/problems/132-pattern
     * <p>
     * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and
     * ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132
     * pattern in the list.
     * 
     * Note: n will be less than 15,000.
     * 
     * Example 1: Input: [1, 2, 3, 4]
     * 
     * Output: False
     * 
     * Explanation: There is no 132 pattern in the sequence. Example 2: Input: [3, 1, 4, 2]
     * 
     * Output: True
     * 
     * Explanation: There is a 132 pattern in the sequence: [1, 4, 2]. Example 3: Input: [-1, 3, 2, 0]
     * 
     * Output: True
     * 
     * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
     * </p>
     * https://discuss.leetcode.com/topic/68193/java-o-n-solution-using-stack-in-detail-explanation
     * 
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        Stack<Pair> stack = new Stack();
        for (int n : nums) {
            if (stack.isEmpty() || n < stack.peek().min)
                stack.push(new Pair(n, n));
            else if (n > stack.peek().min) {
                Pair last = stack.pop();
                if (n < last.max)
                    return true;
                else {
                    last.max = n;
                    while (!stack.isEmpty() && n >= stack.peek().max)
                        stack.pop();
                    // At this time, n < stack.peek().max (if stack not empty)
                    if (!stack.isEmpty() && stack.peek().min < n)
                        return true;
                    stack.push(last);
                }

            }
        }
        return false;
    }

    class Pair {
        int min, max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] { 1, 0, 1, -4, -3 };
        // System.out.println(solution.nextGreaterElements(array));
        // System.out.println(solution.removeKdigits("112", 1));
        System.out.println(solution.find132pattern(array));
    }
}
