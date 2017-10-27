/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.heap.hard;

import org.ccs.leetcode.bean.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author abel created on 2017/10/26 下午6:56
 * @version $Id$
 */
public class Solution {

    /**
     * 23. Merge k Sorted Lists
     * <p>
     * https://leetcode.com/problems/merge-k-sorted-lists
     * <p>
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * 
     * </p>
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        ListNode head = new ListNode(-1);
        ListNode node = head;
        for (ListNode listNode : lists) {
            if (listNode != null) {
                priorityQueue.add(listNode);
            }
        }
        while (!priorityQueue.isEmpty()) {
            node.next = priorityQueue.poll();
            node = node.next;
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
        }
        return head.next;
    }
}
