/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.linkedlist;

import org.ccs.leetcode.bean.ListNode;

/**
 * 206. Reverse Linked List
 *
 * <p>
 * Reverse a singly linked list.
 * 
 * </p>
 * 
 * @author abel created on 2018/1/12 下午4:47
 * @version $Id$
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = head;
        ListNode cur = null;
        while (next != null) {
            cur = next.next;
            next.next = pre;
            pre = next;
            next = cur;
        }
        return pre;
    }

}
