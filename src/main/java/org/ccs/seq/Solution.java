/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.seq;

import org.ccs.leetcode.bean.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abel created on 2017/9/5 16:25
 * @version $Id$
 */
public class Solution {
    /**
     * 1 TwoSum
     * 
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rest = target - nums[i];
            if (map.keySet().contains(rest)) {
                res[0] = i;
                res[1] = map.get(rest);
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * 2. Add Two Numbers
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int carry = 0;
        ListNode fakeHead = new ListNode(0);
        ListNode cur = fakeHead;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode node = new ListNode(0);
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            node.val = sum % 10;
            carry = sum / 10;
            cur.next = node;
            cur = node;
            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }
        return fakeHead.next;
    }
}
