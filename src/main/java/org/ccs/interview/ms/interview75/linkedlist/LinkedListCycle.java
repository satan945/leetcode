/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.linkedlist;

import org.ccs.leetcode.bean.ListNode;

/**
 * 141. Linked List Cycle
 * 
 * https://leetcode.com/problems/linked-list-cycle/description/
 * <p>
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up: Can you solve it without using extra space?
 * 
 * 
 * </p>
 * 
 * @author abel created on 2018/2/25 下午2:51
 * @version $Id$
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
