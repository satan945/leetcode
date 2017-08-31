/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.sort.medium;

import org.ccs.leetcode.bean.ListNode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Abel created on 2017/8/28 17:38
 * @version $Id$
 */
public class Solution {

    /**
     * 179. Largest Number
     * <p>
     * https://leetcode.com/problems/largest-number
     * <p>
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * 
     * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * 
     * Note: The result may be very large, so you need to return a string instead of an integer.
     * 
     * Credits: Special thanks to @ts for adding this problem and creating all test cases.
     * </p>
     * 
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String[] stringNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            stringNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(stringNums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (stringNums[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringNums.length; i++) {
            sb.append(stringNums[i]);
        }
        return sb.toString();
    }

    /**
     * 147. Insertion Sort List
     * <p>
     * https://leetcode.com/problems/insertion-sort-list
     * <p>
     * Sort a linked list using insertion sort.
     * </p>
     * 
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        ListNode cur = head;

        return head;
    }

    public String largestNumberJava8(int[] num) {
        String[] array = Arrays.stream(num).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(array, (String s1, String s2) -> (s2 + s1).compareTo(s1 + s2));
        return Arrays.stream(array).reduce((x, y) -> x.equals("0") ? y : x + y).get();
    }
}
