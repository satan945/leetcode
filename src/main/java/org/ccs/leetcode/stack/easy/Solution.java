/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.stack.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Abel created on 2017/8/2 13:40
 * @version $Id$
 */
public class Solution {
    /**
     * 496. Next Greater Element I
     * <p>
     * https://leetcode.com/problems/next-greater-element-i/description/
     * <p>
     * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find
     * all the next greater numbers for nums1's elements in the corresponding places of nums2.
     * 
     * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not
     * exist, output -1 for this number.
     * 
     * Example 1:
     * 
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 
     * Output: [-1,3,-1]
     * 
     * Explanation:
     * 
     * For number 4 in the first array, you cannot find the next greater number for it in the second array, so output
     * -1. For number 1 in the first array, the next greater number for it in the second array is 3. For number 2 in the
     * first array, there is no next greater number for it in the second array, so output -1.
     * 
     * Example 2: Input: nums1 = [2,4], nums2 = [1,2,3,4].
     * 
     * Output: [3,-1]
     * 
     * Explanation:
     * 
     * For number 2 in the first array, the next greater number for it in the second array is 3. For number 4 in the
     * first array, there is no next greater number for it in the second array, so output -1. Note: All elements in
     * nums1 and nums2 are unique. The length of both nums1 and nums2 would not exceed 1000.
     * </p>
     * 
     * @param findNums
     * @param nums
     * @return
     */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] res = new int[findNums.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> tempStack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!tempStack.isEmpty() && nums[i] > tempStack.peek()) {
                map.put(tempStack.pop(), nums[i]);
            }
            tempStack.push(nums[i]);
        }
        while (!tempStack.isEmpty()) {
            map.put(tempStack.pop(), -1);
        }
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }

    /**
     * 20. Valid Parentheses
     *
     * <p>
     * https://leetcode.com/problems/valid-parentheses
     * <p>
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
     * valid.
     * 
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     * </p>
     * 
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char val = s.charAt(i);
            if (val == '(' || val == '{' || val == '[') {
                stack.push(val);
            } else if (val == ')' || val == ']' || val == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.peek();
                if (valid(top, val)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean valid(char top, char val) {
        return (top == '(' && val == ')') || (top == '[' && val == ']') || (top == '{' && val == '}');
    }

    /**
     * 682. Baseball Game
     * <p>
     * https://leetcode.com/problems/baseball-game
     * <p>
     * You're now a baseball game point recorder.
     * 
     * Given a list of strings, each string can be one of the 4 following types:
     * 
     * Integer (one round's score): Directly represents the number of points you get in this round. "+" (one round's
     * score): Represents that the points you get in this round are the sum of the last two valid round's points. "D"
     * (one round's score): Represents that the points you get in this round are the doubled data of the last valid
     * round's points. "C" (an operation, which isn't a round's score): Represents the last valid round's points you get
     * were invalid and should be removed. Each round's operation is permanent and could have an impact on the round
     * before and the round after.
     * 
     * You need to return the sum of the points you could get in all the rounds.
     * 
     * Example 1:
     * 
     * Input: ["5","2","C","D","+"]
     * 
     * Output: 30
     * 
     * Explanation: Round 1: You could get 5 points. The sum is: 5. Round 2: You could get 2 points. The sum is: 7.
     * Operation 1: The round 2's data was invalid. The sum is: 5. Round 3: You could get 10 points (the round 2's data
     * has been removed). The sum is: 15. Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
     * 
     * Example 2:
     * 
     * Input: ["5","-2","4","C","D","9","+","+"]
     * 
     * Output: 27
     * 
     * Explanation: Round 1: You could get 5 points. The sum is: 5. Round 2: You could get -2 points. The sum is: 3.
     * Round 3: You could get 4 points. The sum is: 7. Operation 1: The round 3's data is invalid. The sum is: 3. Round
     * 4: You could get -4 points (the round 3's data has been removed). The sum is: -1. Round 5: You could get 9
     * points. The sum is: 8. Round 6: You could get -4 + 9 = 5 points. The sum is 13. Round 7: You could get 9 + 5 = 14
     * points. The sum is 27. Note: The size of the input list will be between 1 and 1000. Every integer represented in
     * the list will be between -30000 and 30000.
     * </p>
     * 
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        Stack<String> stack = new Stack<>();
        //todo
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("[]()"));
    }
}
