/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.linklist.easy;

import org.ccs.leetcode.bean.ListNode;

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
}
