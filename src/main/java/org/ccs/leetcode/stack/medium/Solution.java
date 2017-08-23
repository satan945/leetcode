/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.stack.medium;

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




    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[] { 1, 2, 1 };
        System.out.println(solution.nextGreaterElements(array));
    }
}
