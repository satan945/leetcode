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
        for(int i=0;i<findNums.length;i++){
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

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("[]()"));
    }
}
