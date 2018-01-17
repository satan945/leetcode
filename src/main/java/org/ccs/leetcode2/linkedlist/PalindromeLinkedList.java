/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.linkedlist;

import org.ccs.leetcode.bean.ListNode;

/**
 * @author abel created on 2018/1/12 下午4:46
 * @version $Id$
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode linkNode1 = head;
        ListNode linkNode2 = reverseLinkedList.reverseList(fast);
        return true;

    }

}
