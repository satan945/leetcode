/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.amazon;

import org.ccs.leetcode.bean.ListNode;

/**
 * 234. Palindrome Linked List
 * 
 * https://leetcode.com/problems/palindrome-linked-list
 * 
 * @author abel created on 2018/3/9 下午9:25
 * @version $Id$
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rHead = reverse(head, slow);
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (rHead.val != slow.val) {
                return false;
            }
            slow = slow.next;
            rHead = rHead.next;
        }
        return true;

    }

    private ListNode reverse(ListNode head, ListNode end) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null && cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        // ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        // node3.next = node4;
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        System.out.println(palindromeLinkedList.isPalindrome(node1));
    }
}
