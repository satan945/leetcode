/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.linkedlist;

import org.ccs.leetcode.bean.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/25 下午10:44
 * @version $Id$
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        ListNode cur = fakeHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode head : lists) {
            if (head != null) {
                queue.offer(head);
            }
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            node = node.next;
            if (node != null) {
                queue.offer(node);
            }
            cur = cur.next;
        }
        return fakeHead.next;

    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode[] nodes = new ListNode[] { node };
        MergeKSortedLists solution = new MergeKSortedLists();
        ListNode root = solution.mergeKLists(nodes);
        System.out.println(root);
    }
}
