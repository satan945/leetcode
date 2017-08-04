/*
 * Copyright (c) 2017 Liqiang Fang All Rights Reserved.
 */
package org.ccs.leetcode.linkedlist.medium;

import org.ccs.leetcode.bean.ListNode;

import java.math.BigDecimal;

/**
 * @author Abel created on 2017/6/29 18:02
 * @version $Id$
 */
public class Solution {

    /**
     * 2. Add Two Numbers
     * <p>
     * https://leetcode.com/problems/add-two-numbersval
     * <p>
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
     * order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * 
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * 
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
     * </p>
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal num1 = new BigDecimal(0);
        BigDecimal num2 = new BigDecimal(0);
        BigDecimal multi = new BigDecimal(1);
        ListNode l1Cursor = l1;
        while (l1Cursor != null) {
            num1 = num1.add(new BigDecimal(l1Cursor.val).multiply(multi));
            multi = multi.multiply(BigDecimal.TEN);
            l1Cursor = l1Cursor.next;
        }
        multi = new BigDecimal(1);
        ListNode l2Cursor = l2;
        while (l2Cursor != null) {
            num2 = num2.add(new BigDecimal(l2Cursor.val).multiply(multi));
            multi = multi.multiply(BigDecimal.TEN);
            l2Cursor = l2Cursor.next;
        }
        BigDecimal sum = num1.add(num2);
        if (sum.equals(BigDecimal.ZERO)) {
            return new ListNode(0);
        }
        String sumStr = new StringBuilder(sum.toString()).reverse().toString();
        ListNode fakeHead = new ListNode(0);
        ListNode curNode = fakeHead;
        for (int i = 0; i < sumStr.length(); i++) {
            ListNode node = new ListNode(Integer.parseInt(sumStr.substring(i, i + 1)));
            curNode.next = node;
            curNode = node;
        }
        return fakeHead.next;
    }

    public ListNode addTwoNumbersWithoutBigDecimal(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);

        return null;
    }

    /**
     * 445. Add Two Numbers II
     * <p>
     * https://leetcode.com/problems/add-two-numbers-ii
     * <p>
     * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
     * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * 
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * 
     * Follow up: What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
     * 
     * Example:
     * 
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7
     * </p>
     * 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        return null;
    }

    /**
     * 19. Remove Nth Node From End of List
     * <p>
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list
     * <p>
     * Given a linked list, remove the nth node from the end of list and return its head.
     * 
     * For example,
     * 
     * Given linked list: 1->2->3->4->5, and n = 2.
     * 
     * After removing the second node from the end, the linked list becomes 1->2->3->5. Note: Given n will always be
     * valid. Try to do this in one pass.
     * </p>
     * 
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode slow = fakeHead;
        ListNode fast = fakeHead;
        while (n > 0) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
            n--;
        }

        if (fast.next == null) {
            slow.next = slow.next.next;
        } else {
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
        }
        return fakeHead.next;
    }

    /**
     * 142. Linked List Cycle II
     * <p>
     * https://leetcode.com/problems/linked-list-cycle-ii/description/
     * <p>
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     *
     * Note: Do not modify the linked list.
     *
     * Follow up: Can you solve it without using extra space?
     * </p>
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        return null;

    }

    /**
     * 92. Reverse Linked List II
     * <p>
     * https://leetcode.com/problems/reverse-linked-list-ii
     * <p>
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.
     * 
     * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
     * 
     * return 1->4->3->2->5->NULL.
     * 
     * Note: Given m, n satisfy the following condition: 1 ? m ? n ? length of list.
     * </p>
     * 
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int sub = n - m;
        if (sub == 0 || head == null) {
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        ListNode start = pre.next;
        ListNode then = start.next;
        for (int i = 0; i < sub; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return fakeHead.next;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        solution.reverseBetween(l1, 1, 2);

        // solution.addTwoNumbers(l1, l2);
        // solution.removeNthFromEnd(l1, 2);
    }
}
