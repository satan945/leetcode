/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.linkedlist.easy;

import org.ccs.leetcode.bean.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Abel created on 2017/6/29 18:01
 * @version $Id$
 */
public class Solution {
    /**
     * 237 Delete Node in a Linked List
     * <p>
     * https://leetcode.com/problems/delete-node-in-a-linked-list
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        if (node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 234. Palindrome Linked List
     * <p>
     * https://leetcode.com/problems/palindrome-linked-list
     * <p>
     * Given a singly linked list, determine if it is a palindrome.
     * 
     * Follow up: Could you do it in O(n) time and O(1) space?
     * </p>
     * 
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode node = head;
        List<ListNode> arrayList = new ArrayList<>();
        while (node != null) {
            arrayList.add(node);
            node = node.next;
        }
        for (int i = 0, j = arrayList.size() - 1; i < j; ++i, --j) {
            if (arrayList.get(i).val != arrayList.get(j).val) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * 141. Linked List Cycle
     * <p>
     * https://leetcode.com/problems/linked-list-cycle
     * 
     * <p>
     * Given a linked list, determine if it has a cycle in it.
     * 
     * Follow up: Can you solve it without using extra space?
     * </p>
     * 
     * @param head
     * @return
     */
    public boolean hasCycleWithHashSet(ListNode head) {
        ListNode node = head;
        HashSet<ListNode> set = new HashSet<>();
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 206. Reverse Linked List
     * <p>
     * https://leetcode.com/problems/reverse-linked-list
     * <p>
     * Reverse a singly linked list.
     * </p>
     * 
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = head;
        ListNode record;
        while (next != null) {
            record = next.next;
            next.next = pre;
            pre = next;
            next = record;
        }
        return pre;
    }

    /**
     * 160. Intersection of Two Linked Lists
     * <p>
     * https://leetcode.com/problems/intersection-of-two-linked-lists
     * <p>
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     * 
     * 
     * For example, the following two linked lists:
     * 
     * A: a1 → a2
     * 
     * ↘
     * 
     * c1 → c2 → c3
     * 
     * ↗
     * 
     * B: b1 → b2 → b3
     * 
     * begin to intersect at node c1.
     * 
     * 
     * Notes:
     * 
     * If the two linked lists have no intersection at all, return null. The linked lists must retain their original
     * structure after the function returns. You may assume there are no cycles anywhere in the entire linked structure.
     * Your code should preferably run in O(n) time and use only O(1) memory.
     * </p>
     * https://leetcode.com/problems/intersection-of-two-linked-lists/solution
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode node = headA;
        while (node != null) {
            set.add(node);
            node = node.next;
        }
        node = headB;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

}
