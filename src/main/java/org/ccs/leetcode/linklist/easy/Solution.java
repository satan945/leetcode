/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.linklist.easy;

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
}
