/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.linkedlist;

import org.ccs.leetcode.bean.ListNode;

/**
 * 19. Remove Nth Node From End of List
 * 
 * @author Abel created on 2018/1/26 0:02
 * @version $Id$
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode fast = fakeHead;
        while (n > 0 && fast != null) {
            n--;
            fast = fast.next;
        }
        if (fast == null) {
            return head;
        }
        ListNode slow = fakeHead;
        ListNode pre = null;
        while (fast != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = slow.next;
        return fakeHead.next;
    }
}
