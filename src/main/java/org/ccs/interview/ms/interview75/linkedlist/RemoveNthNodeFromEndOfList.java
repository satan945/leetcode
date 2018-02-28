/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.linkedlist;

import org.ccs.leetcode.bean.ListNode;

/**
 * 19. Remove Nth Node From End of List
 * <p>
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/25 下午10:31
 * @version $Id$
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode fast = fakeHead;
        while (fast != null && n >= 0) {
            fast = fast.next;
            n--;
        }
        ListNode pre = fakeHead;
        ListNode slow = head;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return fakeHead.next;
    }
}
