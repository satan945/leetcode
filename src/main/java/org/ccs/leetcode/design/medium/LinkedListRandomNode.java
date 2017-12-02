/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.design.medium;

import org.ccs.leetcode.bean.ListNode;

import java.util.Random;

/**
 * @author abel created on 2017/11/22 下午8:08
 * @version $Id$
 */
public class LinkedListRandomNode {
    private ListNode head;
    private Random random;

    /**
     * @param head The linked list's head. Note that the head is guaranteed to be not null, so it contains at least one
     *            node.
     */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = head;
        int r = node.val;
        for (int i = 0; node.next != null; i++) {
            node = node.next;
            if (random.nextInt(i + 1) == i) {
                r = node.val;
            }
        }
        return r;
    }
}
