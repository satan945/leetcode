/*
 * Copyright (c) 2017 Liqiang Fang All Rights Reserved.
 */
package org.ccs.leetcode.linkedlist.medium;

import org.ccs.leetcode.bean.ListNode;
import org.ccs.leetcode.bean.RandomListNode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

    /**
     * 24. Swap Nodes in Pairs
     * <p>
     * https://leetcode.com/problems/swap-nodes-in-pairs
     * <p>
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can
     * be changed.
     *
     * </p>
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        node.next = swapPairs(head.next.next);
        node.next = head;
        return node;
    }

    /**
     * 138. Copy List with Random Pointer
     * <p>
     * https://leetcode.com/problems/copy-list-with-random-pointer
     * <p>
     * A linked list is given such that each node contains an additional random pointer which could point to any node in
     * the list or null.
     * 
     * Return a deep copy of the list.
     * </p>
     * 
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode newNode = newHead;
        RandomListNode node = head.next;
        HashMap<RandomListNode, RandomListNode> mapping = new HashMap<>();
        mapping.put(head, newHead);
        newHead.random = head.random;
        while (node != null) {
            newNode.next = new RandomListNode(node.label);
            mapping.put(node, newNode.next);
            newNode.next.random = node.random;
            newNode = newNode.next;
            node = node.next;
        }

        newNode = newHead;
        while (newNode != null) {
            newNode.random = mapping.get(newNode.random);
            newNode = newNode.next;
        }
        return newHead;
    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null)
            return null;

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

    /**
     * 148. Sort List
     * <p>
     * https://leetcode.com/problems/sort-list
     * <p>
     * Sort a linked list in O(n log n) time using constant space complexity.
     * </p>
     *
     * Sort
     * 
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode fast = head;
        ListNode slow = head;

        // cut
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        // merge
        ListNode p1 = sortList(head);
        ListNode p2 = sortList(slow);

        return mergeSortList(p1, p2);
    }

    private ListNode mergeSortList(ListNode p1, ListNode p2) {
        ListNode fakeHead = new ListNode(0);
        ListNode cur = fakeHead;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        if (p1 != null) {
            cur.next = p1;
        }
        if (p2 != null) {
            cur.next = p2;
        }
        return fakeHead.next;
    }

    /**
     * 328. Odd Even Linked List
     * <p>
     * https://leetcode.com/problems/odd-even-linked-list
     * <p>
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
     * talking about the node number and not the value in the nodes.
     * 
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
     * 
     * Example: Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
     * 
     * Note: The relative order inside both the even and odd groups should remain as it was in the input. The first node
     * is considered odd, the second node even and so on ...
     * </p>
     * 
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        ListNode evenHead = new ListNode(0);
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oddNode = head;
        ListNode evenNode = evenHead;
        ListNode prev = null;
        while (oddNode != null && oddNode.next != null) {
            prev = oddNode;
            evenNode.next = oddNode.next;
            evenNode = evenNode.next;
            oddNode.next = oddNode.next.next;
            oddNode = oddNode.next;
        }
        evenNode.next = null;
        if (oddNode != null) {
            prev = oddNode;
        }
        prev.next = evenHead.next;

        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        solution.oddEvenList(l1);
        // solution.reverseBetween(l1, 1, 2);

        RandomListNode head = new RandomListNode(-1);
        RandomListNode n1 = new RandomListNode(-1);
        head.next = n1;
        System.out.println(solution.copyRandomList(head));

        // solution.addTwoNumbers(l1, l2);
        // solution.removeNthFromEnd(l1, 2);
    }
}
