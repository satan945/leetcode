/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.linkedlist;

import org.ccs.leetcode.bean.ListNode;

import java.util.Stack;

/**
 * 445. Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
 * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Follow up: What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * </p>
 * 
 * @author abel created on 2018/2/20 下午4:19
 * @version $Id$
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = new ListNode(1);
        int sum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            ListNode node = new ListNode(sum % 10);
            node.next = head.next;
            head.next = node;
            sum /= 10;
        }
        if (sum > 0) {
            return head;
        }
        return head.next;
    }


    public ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
        ListNode n1 = reverse(l1);
        ListNode n2 = reverse(l2);
        int carry = 0;
        ListNode temp = n1;
        ListNode pre = n1;
        while(n1!= null || n2 != null || carry != 0){
            int v1 = n1 == null? 0: n1.val;
            int v2 = n2 == null? 0: n2.val;
            if(n1 == null){
                n1 = new ListNode((v1+v2+carry) % 10);
                pre.next = n1;
            }else{
                n1.val = (v1+v2+carry) % 10;
            }
            carry = (v1+v2+carry)/10;
            pre = n1;
            n1 = n1 == null? null : n1.next;
            n2 = n2 == null? null : n2.next;
        }
        return reverse(temp);
    }
    public ListNode reverse(ListNode head){
        ListNode newHead = null;
        while(head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}
